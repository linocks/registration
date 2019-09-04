package com.linocks.registration.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/")
    public String loadHomePage(){

        return "home";
    }

    @GetMapping("/registration")
    public String loadRegistrationPage(){

        return "registration";
    }
}
