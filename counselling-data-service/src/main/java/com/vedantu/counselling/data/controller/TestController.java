package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.view.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/testMethod")
    public String testMethod(){
        return "test String";
    }

    @GetMapping("/test/testMethod2")
    public String testMethod2(){
        return "Test String 2";
    }

    @GetMapping("/test/person")
    public Person getPerson(){
        return new Person(1, "Ravi");
    }
}
