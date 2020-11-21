package com.leoram.emblemgenerator;

import com.leoram.emblemgenerator.dto.Base64DataUrlDTO;
import com.leoram.emblemgenerator.v1.BlazonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmblemgeneratorApplicationTests {

    @Autowired
    private BlazonController controller;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnBase64(){
    }


}
