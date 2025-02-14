package com.korit.springboot_study.controller;

import com.korit.springboot_study.ioc.ClassA;
import com.korit.springboot_study.ioc.ClassB;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IocController {

    private ClassA a;
    private ClassB b;

    @GetMapping("/api/ioc")
    public ResponseEntity<?> call() {
        a.classCallA();
        b.classCallB();
        return ResponseEntity.ok(null);
    }
}
