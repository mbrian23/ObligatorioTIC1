package com.example.moviecrud;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MovieCrudApplication {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(MovieCrudApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
