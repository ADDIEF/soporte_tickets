package com.sisST.soporte_tickets;

import org.springframework.boot.SpringApplication;

public class TestSoporteTicketsApplication {

	public static void main(String[] args) {
		SpringApplication.from(SoporteTicketsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
