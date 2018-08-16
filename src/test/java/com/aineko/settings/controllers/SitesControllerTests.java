package com.aineko.settings.controllers;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
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

    @Test
    public void
    sites_getWithout_id_returnsEmptyList() {

        when().
                get("/site").
                then().
                statusCode(200).
                body("", hasSize(0));
    }

}

