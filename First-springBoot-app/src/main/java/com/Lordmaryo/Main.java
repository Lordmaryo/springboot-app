package com.Lordmaryo;

import com.Lordmaryo.activities.BankDetails;
import com.Lordmaryo.activities.Customer;
import com.Lordmaryo.activities.FullPerson;
import com.Lordmaryo.activities.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Main.class, args);

        FullPerson fullPerson = ctx.getBean("fullPerson", FullPerson.class);
        Customer customer = ctx.getBean("customer", Customer.class);
        System.out.println(fullPerson);
        System.out.println(customer);
    }

    //API
    @GetMapping("/")
    public FullPerson greet() {
        Person person = new Person("Emmanuel Ofoneta", 20,
                List.of("Java", "C#", "Golang"));

        BankDetails bankDetails = new BankDetails(120_000, "Lord maryo",
                LocalDate.of(2020, 9, 10));

        return new FullPerson(person, bankDetails);
    }
}
