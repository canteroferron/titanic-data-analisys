package com.titanic.data.analisys.app.simulators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.FluxSink;

/**
 * This class implement a simulator for emit lines to {@code FluxSink}
 * @author jcantero
 *
 */
public class QueueSimulator {

	
	private static final Logger log = LoggerFactory.getLogger(QueueSimulator.class);
	
	private final List<String> lines;
	
	/**
	 * Constructor
	 * @param file 
	 * 			File where are the data
	 * @throws IOException
	 * 			File not exist
	 */
	public QueueSimulator(File file) {
		lines = readFiles(file);
	}
	
	/**
	 * 
	 * @param file
	 * 			File where are the data
	 * @return
	 * 		{@code List<String>} with the lines of the file
	 */
	private List<String> readFiles(File file) {
		List<String> ret = new ArrayList<>();
		
		try {
			ret.addAll(Files.readAllLines(Path.of(file.toURI())));
		} catch (IOException e) {
			log.error("Error reading file", e);
		}		
		
		return ret;
	}
	
	
	/**
	 *  This method perform the emit of the lines to {@code FluxSink} that receive like parameter
	 *  @param sink Object {@code FluxSink} for emit the lines.
	 */
	public void emitTo(final FluxSink<Object> sink) {		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lines
					.stream()
					.forEach(s -> {
						try {
							Thread.sleep(ThreadLocalRandom.current().nextLong(50));
						} catch (InterruptedException e) {
							log.error("Error try sleep the thread", e);
						}
						
						sink.next(s);
					});
				
				log.info("sink.complete()");
				sink.complete();	
			}
		});
		
		t.start();
	}
	
	
	
}
