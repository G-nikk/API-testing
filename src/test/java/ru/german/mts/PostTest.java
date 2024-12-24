package ru.german.mts;

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


public class PostTest {

    static AllureRestAssured allureFilter;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        allureFilter = new AllureRestAssured();
    }

    // JSON объект для положительного теста
    public String getSuccessData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eve.holt@reqres.in");
        jsonObject.put("password", "pistol");

        return jsonObject.toString();
    }

    @Test
    @Story("POST request for /register")
    @Description("success POST /register")
    public void successRegisterTest() {
        given()
                .filter(allureFilter)
                .contentType(ContentType.JSON)
                .body(getSuccessData())
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue());
    }

    // JSON объект для негативного теста
    public String getFailData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eve.holt@reqres.in");

        return jsonObject.toString();
    }

    @Test
    @Story("POST request for /register")
    @Description("fail POST /register")
    public void failRegisterTest() {
        given()
                .filter(allureFilter)
                .contentType(ContentType.JSON)
                .body(getFailData())
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}
