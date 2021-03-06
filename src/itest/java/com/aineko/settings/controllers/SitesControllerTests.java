package com.aineko.settings.controllers;

import com.aineko.settings.entities.Site;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default")
public class SitesControllerTests {

    @Autowired
    SitesController controller;

    @Test
    public void checkConfig(){
        assertNotNull(controller);
    }

    @Test
    public void
    sites_getWithout_id_returnsEmptyList() {

        when().
                get("/site").
                then().
                statusCode(200).
                body("", hasSize(0));
    }

    @Test
    public void site_crud_AllOperationsWorks(){
        String url = "http://www.vg.no";
        Response response =  when()
                .put("/site?url={url}", url)
                .then()
                .statusCode(200)
                .body("id",  greaterThan(0))
                .body("url", equalTo("http://www.vg.no"))
                .extract()
                    .response();

        // Delete with the newly created id should be ok
        Integer id = response.path("id");
        String newUrl = "http://ba.no";
        when()
                .patch("/site?url={url}&id={id}", newUrl, id)
                .then()
                .body("id", equalTo(id))
                .body("url", equalTo(newUrl))
                .statusCode(200);



        when()
                .delete("/site?id={id}", id)
                .then()
                .statusCode(200);

        //Check that the database is empty
        when().
                get("/site").
                then().
                statusCode(200).
                body("", hasSize(0));
    }


    @Test
    public void Save_HasListOfUrls_AllUrlsSaved(){
        List<String> urls = Arrays.asList("http://www.vg.no", "http://www.dagbladet.no", "http://vg.no");
        Map<String, List> urlMap = new HashMap<>();
        urlMap.put("urls", urls);

        Response response =  given().body(urlMap).when()
                .post("/sites")
                .then()
                .statusCode(200)
                .body("id",  greaterThan(0))
                .body("url", equalTo(Arrays.asList("http://www.vg.no", "http://www.dagbladet.no", "http://vg.no")))
                .extract()
                .response();
    }
}
