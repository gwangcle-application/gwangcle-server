package com.gwangcle.study.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static com.gwangcle.study.acceptance.AcceptanceSetUp.simpleRequestBodySnippet;
import static com.gwangcle.study.acceptance.AcceptanceSetUp.simpleResponseFieldsSnippet;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class MemberSteps {

    @Test
    public static ExtractableResponse<Response> 회원_생성_요청(RequestSpecification spec, String name, String careerLevel, String jobType) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("careerLevel", careerLevel);
        params.put("jobType", jobType);

        return RestAssured.given(spec).log().all()
                .body(params)
                .filter(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        simpleRequestBodySnippet(),
                        simpleResponseFieldsSnippet()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/members")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    @Test
    public static ExtractableResponse<Response> PK_값으로_회원_정보_요청(RequestSpecification spec, ExtractableResponse<Response> createResponse) {
        return RestAssured.given(spec).log().all()
                .filter(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(createResponse.header("location"))
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    @Test
    public static ExtractableResponse<Response> 회원_정보_전체_요청(RequestSpecification spec) {
        return RestAssured.given(spec).log().all()
                .filter(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .when().get("/api/members")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    @Test
    public static ExtractableResponse<Response> PK_값으로_회원_삭제_요청(RequestSpecification spec, ExtractableResponse<Response> createResponse) {
        return RestAssured.given(spec).log().all()
                .filter(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .when().delete(createResponse.header("location"))
                .then().log().all()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract();
    }
}
