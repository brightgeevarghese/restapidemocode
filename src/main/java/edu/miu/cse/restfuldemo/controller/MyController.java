package edu.miu.cse.restfuldemo.controller;

import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")//base uri
public class MyController {
    @GetMapping("/say-hello")
    public String sayHello() {
        return "Hello World!";
    }
    @GetMapping("/say-another-hello")
    public String sayHello2(){
        return "Hello World2!";
    }

    @GetMapping("/numbers")
    public List<Integer> numbers(){
        return List.of(1,23);
    }

    @GetMapping("/{course-code}")
    public String courseCode(@PathVariable(value = "course-code") String courseCode){
        return courseCode;
    }

    @GetMapping("/re-say-hello")
    public ResponseEntity<String> reSayHello(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/jpeg");
        return ResponseEntity.accepted()
                .headers(headers).
                body("Hello World!");
    }
}
