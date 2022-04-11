package com.cotton.algorithmer;

import com.cotton.algorithmer.service.AlgorithmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgorithmerApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
			.getLogger(AlgorithmerApplication.class);

	@Autowired
	private AlgorithmService algorithmService;

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION...");
		SpringApplication.run(AlgorithmerApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		String fileName = "input.txt";
		if (args.length > 0) {
			fileName = args[0];
		}

		algorithmService.execute((result, inputData) -> {
			System.out.println(inputData.getAlgorithm() + " : " + result + "  input: " + inputData.getData());
		}, fileName);
	}
}
