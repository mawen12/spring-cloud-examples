package com.mawen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import sun.tools.jar.CommandLine;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/18
 */
@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().web(WebApplicationType.NONE).sources(ConfigClientApplication.class).run(args);
    }

    @Autowired
    private Environment environment;

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            System.out.println(
                    "book.category=" + environment.getProperty("book.category", "unknown")
            );
            System.out.println(
                    "book.author=" + environment.getProperty("book.author", "unknown")
            );
            System.out.println(
                    "book.name=" + environment.getProperty("book.name", "unknown")
            );
        };
    }
}
