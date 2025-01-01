package com.example.springbootcs;

import org.springframework.stereotype.Service;

@Service
public class CakeBaker {
    final Frosting frosting;
    final Syrup syrup;

    public CakeBaker(Frosting frosting,Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }
    String getFrosting(){
      return frosting.getFrostingType();
    }
    String getSyrup(){
        return syrup.getSyrupType();
    }
}
