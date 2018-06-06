package com.yukoon.turntablegames.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "commons/logout";
    }

}
