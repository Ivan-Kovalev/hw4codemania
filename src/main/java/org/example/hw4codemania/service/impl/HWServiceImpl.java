package org.example.hw4codemania.service.impl;

import org.example.hw4codemania.service.HWService;
import org.springframework.stereotype.Service;

@Service
public class HWServiceImpl implements HWService {

    @Override
    public String getHW() {
        return "Hello World!";
    }
}
