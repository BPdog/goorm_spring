package com.mycompany.daily0925.domain;
import org.springframework.stereotype.Component;

@Component("guitar")
public class Guitar implements Instrument {
    @Override
    public String play() {
        System.out.println("Guitar is playing...");
        return "Guitar is playing...";
    }
}