package org.example.hw4codemania.controller;

import org.example.hw4codemania.service.HWService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HWController {
    private final HWService service;

    public HWController(HWService service) {
        this.service = service;
    }

    @GetMapping(path = "/hw")
    public ResponseEntity<String> getHW() {
        String hw = service.getHW();
        return ResponseEntity.ok(hw);
    }
}
