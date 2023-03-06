package com.paul.startclass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String start(){
        return "test";
    }

}
