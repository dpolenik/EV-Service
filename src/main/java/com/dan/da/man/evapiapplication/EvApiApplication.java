package com.dan.da.man.evapiapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class EvApiApplication {

    public static void main(String[] cheese) {
        SpringApplication.run(EvApiApplication.class, cheese);
    }

}
