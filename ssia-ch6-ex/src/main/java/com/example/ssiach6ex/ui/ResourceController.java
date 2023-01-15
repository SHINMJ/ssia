package com.example.ssiach6ex.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/login")
    public String login(){
        return "login!!";
    }

    @GetMapping("/all")
    public String allRequest(){
        return "all Request!!";
    }
}
