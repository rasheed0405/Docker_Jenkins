package com.rasheed.firstdocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/Health")
    public String healthCgeck(){
        return  "Application Running";
        //Fiest run go
        //second go
    }
}
