package com.interpackage.users.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTest {

	@RequestMapping({ "/greeting" })
    public String welcomePage() {
        return "Welcome!";
    }

}
