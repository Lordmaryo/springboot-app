package com.Lordmaryo.activities;

import org.springframework.stereotype.Component;

@Component
public record FullPerson(Person person, BankDetails bankDetails) {
}
