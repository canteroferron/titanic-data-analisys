package com.titanic.data.analisys.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.titanic.data.analisys.app.entities.factory.PassengerFactory;
import com.titanic.data.analisys.app.simulators.SourceSimulator;
import com.titanic.data.analisys.app.statistics.PassengersGroup;
import com.titanic.data.analisys.app.statistics.PassengersGroups;
import com.titanic.data.analisys.app.statistics.PassengersSorter;
import com.titanic.data.analisys.app.statistics.PropertyBean;
import com.titanic.data.analisys.app.statistics.utils.PrinterFormats;
import com.titanic.data.analisys.app.utils.CsvUtils;

import reactor.core.publisher.Flux;

@Component
public class Runner implements CommandLineRunner {
	
	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(SourceSimulator.class);
	
	/**
	 * File of data
	 */
	@Value("classpath:titanic_passengers.csv")
	private Resource resourceFile;

	@Override
	public void run(String... args) throws Exception {
		log.info("------ METHOD START ------");	
		 /**
		  * Queue simulator	
		  */
		 SourceSimulator simulator = new SourceSimulator(resourceFile.getFile());
		 
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
				 		List<PassengersGroup> groups = new ArrayList<>();
				 		
				 		clasification.forEach((sorter, passengers) ->  groups.add(new PassengersGroup(sorter, passengers)));
				 		
				 		return Flux.fromIterable(groups);
				 	})
				 	.log()
				 	.doOnNext(group -> 
				 		group.addStatisticValue(passengers -> {
					 		Long survivors = 
				 					passengers
				 						.stream()
				 						.filter(p -> p.getSurvived() == 1)
				 						.collect(Collectors.counting());
					 		
					 		return new PropertyBean("relativeFrequency", Double.valueOf(survivors) / passengers.size());
					 	})
				 	).log();	 	
		 
		 
		 List<PassengersGroup> passengersGroups = new ArrayList<>();
		 classification.subscribe(
				 passengersGroups::add,
				 error -> log.error("Error in subscribe", error),
				 () -> {
					 PassengersGroups groups = new PassengersGroups(passengersGroups);		 
					 
					 groups.toDo("relativeFrequency", PrinterFormats.SYSTEM_OUT_TABLE);					 
				 });
		 
		 log.info("------ END OF METHOD ------");	
	}

}
