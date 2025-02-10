package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller will ensure that this class will control how the object will be
 * used. It is done using two parts: 
 * 1. Get Mapping 
 * 2. Request Parameters
 */
@RestController
public class GreetingController {

    private static final String helloTemplate = "Hello, %s!";
    private static final String morningTemplate = "Good Morning, %s!";
    private static final String eveningTemplate = "Good Evening, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name,
                             @RequestParam(value = "greetingType", defaultValue = "hello") String greetingType) {

        String greetingMessage;

        // Logic to select greeting template based on 'greetingType'
        switch (greetingType.toLowerCase()) {
            case "morning":
                greetingMessage = String.format(morningTemplate, name);
                break;
            case "evening":
                greetingMessage = String.format(eveningTemplate, name);
                break;
            case "hello":
            default:
                greetingMessage = String.format(helloTemplate, name);
                break;
        }

        // Return Greeting object with message and a unique ID
        return new Greeting(counter.incrementAndGet(), greetingMessage);
    }
}
