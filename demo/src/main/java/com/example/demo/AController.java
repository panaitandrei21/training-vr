package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AController {
    private final AService aService;
    private final UserRepository userRepository;
    @GetMapping // http://localhost:8080
    public String hello() {
        System.out.println("Chem pe "+ aService.getClass());
        return aService.metodaSmechera();
    }
    @GetMapping("/export")
    public String export() {
        String uuid = UUID.randomUUID().toString();
        aService.generate(uuid);
        return uuid;
    }
    @GetMapping("/proxy-bug")
    public List<User> proxy() {
        aService.testProxyNuMergePtCaApelezDinAceeasiClasa();
        return userRepository.findAll();
    }



}
