package com.mycompany.dailyMission.domain;

import org.springframework.stereotype.Component;

@Component("piano")
public class Piano implements Instrument {
    @Override
    public String play() {
        System.out.println("Piano is playing...");
        return "Piano is playing...";
    }
}
