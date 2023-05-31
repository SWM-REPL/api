package com.repl.replnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReplnoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplnoteApplication.class, args);
	}

}
