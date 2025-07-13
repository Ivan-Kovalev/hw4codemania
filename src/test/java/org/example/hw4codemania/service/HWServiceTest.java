package org.example.hw4codemania.service;

import org.example.hw4codemania.service.impl.HWServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HWServiceTest {

    private HWService service;

    @BeforeEach
    void setUp() {
        service = new HWServiceImpl();
    }

    @Test
    void getHW() {
        String returned = service.getHW();
        String expected = "Hello World!";
        Assertions.assertEquals(expected, returned);
    }
}