package com.cloud.consumer;

import com.cloud.consumer.aspectj.Aservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest
public class AspectJTest {
    @Autowired
    private Aservice aservice;

    @Test
    public void testAspectJ() throws ClassNotFoundException {
        int add = aservice.add();
        System.out.println(add);
    }


    @Test
    public void testAspectJ2() throws ClassNotFoundException {
        String delete = aservice.delete();
        System.out.println(delete);
    }
}
