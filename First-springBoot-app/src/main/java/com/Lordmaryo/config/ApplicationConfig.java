package com.Lordmaryo.config;

import com.Lordmaryo.activities.BankDetails;
import com.Lordmaryo.activities.Customer;
import com.Lordmaryo.activities.FullPerson;
import com.Lordmaryo.activities.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Configuration
public class ApplicationConfig {
    Person person = new Person("Ebuka", 24, List.of("Java", "C#", "Go"));

    BankDetails bankDetails = new BankDetails(120_000.00, "Ebuka",
            LocalDate.of(2020, 12, 10));

    @Bean
    public Customer customer() {
        return new Customer(
                1, "Mary", "Mary@gmail.com", "10 joh thomas street", 20
        );
    }

    @Bean
    @GetMapping("/full-person")
    public FullPerson fullPerson() {
        return new FullPerson(person, bankDetails);
    }
}
