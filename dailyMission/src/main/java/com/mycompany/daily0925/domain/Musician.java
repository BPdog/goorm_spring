package com.mycompany.daily0925.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Musician {

    private final Instrument instrument;
/*  @Qualifier로 guitar를 주입
    @Autowired
    public Musician(@Qualifier("guitar") Instrument instrument) {
        this.instrument = instrument;
    }

//*/
///* @Qualifier를 바꿔서 Piano를 주입
    @Autowired
    public Musician(@Qualifier("piano") Instrument instrument) {
        this.instrument = instrument;
    }
    //*/
    public void perform() {
        instrument.play();
    }
}
