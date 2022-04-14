package com.example.restblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")// this connects the getRequest to the endpoint ex. /hello
    @ResponseBody
    public String hello() {
        return "Hello from Spring!"; // this is the body of the response
    }

    @GetMapping("/hello/{personName}")//the path parameter must have the "{}" and match all parameters
    @ResponseBody
    public String sayHello(@PathVariable String personName) { // <---- path parameter
        return "Hello, " + personName + "!";//<---- path parameter
    }

    @GetMapping("/increment/{number}")
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }
}
