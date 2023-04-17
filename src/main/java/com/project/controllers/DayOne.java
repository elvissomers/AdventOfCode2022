package com.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("1")
public class DayOne {
    @GetMapping("1")
    public int getAnswerOne(){
        return 0;
    }
}
