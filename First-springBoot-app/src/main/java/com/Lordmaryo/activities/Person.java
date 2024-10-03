package com.Lordmaryo.activities;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public record Person(String name,
                     int age,
                     List<String> favProgrammingLang) {
}
