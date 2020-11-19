package com.titanic.data.analisys.app;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.titanic.data.analisys.app.entities.Passenger;
import com.titanic.data.analisys.app.entities.factory.PassengerFactory;
import com.titanic.data.analisys.app.simulators.QueueSimulator;
import com.titanic.data.analisys.app.statistics.PassengersSorter;
import com.titanic.data.analisys.app.statistics.PassengersGroup;
import com.titanic.data.analisys.app.statistics.PassengersGroups;
import com.titanic.data.analisys.app.statistics.PropertyBean;
import com.titanic.data.analisys.app.statistics.utils.PrinterFormats;
import com.titanic.data.analisys.app.utils.CsvUtils;

import reactor.core.publisher.Flux;

@Component
public class Runner implements CommandLineRunner {
	
	
	/**
	 * File of data
	 */
	@Value("classpath:titanic_passengers.csv")
	private Resource resourceFile;

	@Override
	public void run(String... args) throws Exception {
		
		 /**
		  * Queue simulator	
		  */
		 QueueSimulator simulator = new QueueSimulator(resourceFile.getFile());
		 
		 Flux<PassengersGroup> classification =
				 Flux.create(sink -> simulator.emitTo(sink))
				 	.map(o -> String.valueOf(o))
				 	.map(CsvUtils::cleanCommasBetweenDoubleQuotes)
				 	.map(s -> s.split(","))
				 	.filter(split -> split.length == 12 && split[0].matches("[0-9]*"))
				 	.map(PassengerFactory::build)
				 	.log()
				 	.collect(Collectors.groupingBy(p -> new PassengersSorter(p.getGender(), p.getPclass())))
				 	.log()
				 	.flatMapMany(clasification -> {
				 		List<PassengersGroup> counts = new ArrayList<>();
				 		for (Entry<PassengersSorter, List<Passenger>> entry : clasification.entrySet()) {
				 			counts.add(new PassengersGroup(entry.getKey(), entry.getValue()));
				 		}
				 		return Flux.fromIterable(counts);
				 	})
				 	.doOnNext(group -> group.addStatisticValue(passengers -> {
				 		LongSummaryStatistics survivors = 
			 					passengers
			 						.stream()
			 						.filter(p -> p.getSurvived() == 1)
			 						.collect(Collectors.summarizingLong(Passenger::getSurvived));
				 		
				 		return new PropertyBean("relativyFrequency", Double.valueOf(survivors.getSum()) / passengers.size());
				 	}));	 	
		 
		 
		 List<PassengersGroup> passengersCounts = new ArrayList<>();
		 classification.subscribe(
				 passengersCounts::add,
				 error -> error.printStackTrace(),
				 () -> {
					 PassengersGroups groups = new PassengersGroups(passengersCounts);		 
					 
					 groups.toDo("relativyFrequency", PrinterFormats.SYSTEM_OUT_TABLE);					 
				 });
		 
	}

}
