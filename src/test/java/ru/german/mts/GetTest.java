package ru.german.mts;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetTest {

    static AllureRestAssured allureFilter;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        allureFilter = new AllureRestAssured();
    }


    @Test
    @Story("GET request of user_id=2")
    @Description("success GET /users/2")
    public void successGetUserTest() {
        given()
                .filter(allureFilter)
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Test
    @Story("GET request of user_id=42 which not present")
    @Description("fail GET /users/42")
    public void failGetUserTest() {
        given()
                .filter(allureFilter)
                .when()
                .get("/users/42")
                .then()
                .statusCode(404);
    }
}
