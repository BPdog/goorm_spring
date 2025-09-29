package com.mycompany.daily0925.controller;

import com.mycompany.daily0925.domain.Musician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConcertController {

    private final Musician musician;

    @Autowired
    public ConcertController(Musician musician) {
        this.musician = musician;
    }

    @GetMapping("/concert")
    public String startConcert(Model model) {
        musician.perform();
        model.addAttribute("msg", "Concert started!");
        return "concert";
    }
}
