package com.aineko.settings.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SitesControllerTests {

    @Autowired
    SitesController controller;

    @Test
    public void checkConfig(){
        assertNotNull(controller);
    }

    //@Test
    //public void contextLoads() {
    //}

}

