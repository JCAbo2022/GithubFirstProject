package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringbootProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testUUID(){
        for(int i = 0; i < 100; i ++){
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

}
