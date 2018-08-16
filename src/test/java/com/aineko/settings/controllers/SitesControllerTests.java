package com.aineko.settings.controllers;

import com.aineko.settings.entities.Site;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

public class SitesControllerTests {

    SitesController controller;

    @Test
    @Ignore
    public void checkConfig(){
        assertNotNull(controller);
    }

    @Test
    @Ignore
    public void
    sites_getWithout_id_returnsEmptyList() {


    }


}

