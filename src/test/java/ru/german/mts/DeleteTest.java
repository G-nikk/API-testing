package ru.german.mts;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

// этот метод апи всегда возвращает 204
public class DeleteTest {

    static AllureRestAssured allureFilter;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        allureFilter = new AllureRestAssured();
    }


    @Test
    @Story("DELETE request of user_id=2")
    @Description("success DELETE /users/2")
    public void successDeleteUserTest() {
        given()
                .filter(allureFilter)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    @Story("DELETE request of user_id=42")
    @Description("fail DELETE /users/")
    public void failDeleteUserTest() {
        given()
                .filter(allureFilter)
                .when()
                .delete("/users/42")
                .then()
                .statusCode(204);
    }
}
