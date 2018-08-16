package com.aineko.settings.controllers;

import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Tests {

    @Test
    @Ignore
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {

        when().
                get("/lotto/{id}", 5).
                then().
                statusCode(200).
                body("lotto.lottoId", equalTo(5),
                        "lotto.winners.winnerId", containsInAnyOrder(23, 54));
    }
}
