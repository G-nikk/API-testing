package ru.german.mts;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutTest {

    static AllureRestAssured allureFilter;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        allureFilter = new AllureRestAssured();
    }

    // JSON объект для положительного теста
    public String getSuccessData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "morpheus");
        jsonObject.put("job", "zion resident");

        return jsonObject.toString();
    }

    @AllureId("PUT-SUCCESS")
    @Test
    @Story("PUT request for /users/2")
    @Description("success PUT /users/2")
    public void successUpdateUserTest() {
        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(getSuccessData())
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
    }

    // JSON объект для отрицательного теста
    public String getFailData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "morpheus");

        return jsonObject.toString();
    }

    @AllureId("PUT-FAIL")
    @Test
    @Story("PUT request for /users/2")
    @Description("fail PUT /users/2")
    public void failUpdateUserTest() {
        given()
                .filter(allureFilter)
                .contentType(ContentType.JSON)
                .body(getFailData())
                .when()
                .put("/users/9999")
                .then()
                .statusCode(200)
                .body("name", equalTo("morpheus"));
    }
}
