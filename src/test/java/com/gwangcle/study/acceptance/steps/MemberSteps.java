package com.gwangcle.study.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MemberSteps {

    @Test
    public static ExtractableResponse<Response> 회원_생성_요청(String name, String employmentStatus, String task) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("employmentStatus", employmentStatus);
        params.put("task", task);

        return RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/members")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    @Test
    public static ExtractableResponse<Response> PK_값으로_회원_정보_요청(ExtractableResponse<Response> createResponse) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(createResponse.header("location"))
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    @Test
    public static ExtractableResponse<Response> 회원_정보_전체_요청() {
        return RestAssured.given().log().all()
                .when().get("/api/members")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    @Test
    public static ExtractableResponse<Response> PK_값으로_회원_삭제_요청(ExtractableResponse<Response> createResponse) {
        return RestAssured.given().log().all()
                .when().delete(createResponse.header("location"))
                .then().log().all()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract();
    }
}
