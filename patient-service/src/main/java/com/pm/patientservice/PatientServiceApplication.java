package com.pm.patientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class PatientServiceApplication {

    public static void main(String[] args) {SpringApplication.run(PatientServiceApplication.class, args);}

}
