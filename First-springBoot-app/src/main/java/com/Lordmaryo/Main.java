package com.Lordmaryo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //API
    @GetMapping("/")
    public FullPerson greet() {
        Person person = new Person("Emmanuel Ofoneta", 20,
                List.of("Java", "C#", "Golang"));

        BankDetails bankDetails = new BankDetails(120_000, "Lord maryo",
                LocalDate.of(2020, 9, 10));

        FullPerson response = new FullPerson(person, bankDetails);
        return response;
    }

    record Person(String name,
                  int age,
                  List<String> favProgrammingLang) {
    }

    record BankDetails(double savings, String accountName, LocalDate dateOfAccount) {
    }

    record FullPerson(Person person, BankDetails bankDetails) {
    }
}
