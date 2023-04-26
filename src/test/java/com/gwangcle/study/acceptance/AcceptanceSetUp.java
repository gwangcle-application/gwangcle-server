package com.gwangcle.study.acceptance;

import com.gwangcle.study.utils.DatabaseCleanUp;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@ActiveProfiles("test")
@TestPropertySource(locations = "/test-application.properties")
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceSetUp {

    @Autowired
    private DatabaseCleanUp databaseCleanup;
    protected RequestSpecification spec;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder().addFilter(documentationConfiguration(restDocumentation))
                .build();
        databaseCleanup.execute();
    }

    public static Snippet simpleRequestBodySnippet() {
        return requestFields(
                fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("이름"),
                fieldWithPath("jobType")
                        .type(JsonFieldType.STRING)
                        .description("분야"),
                fieldWithPath("careerLevel")
                        .type(JsonFieldType.STRING)
                        .description("연차")
        );
    }

    public static Snippet simpleResponseFieldsSnippet() {
        return responseFields(
                fieldWithPath("id")
                        .type(JsonFieldType.NUMBER)
                        .description("아이디"),
                fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("이름"),
                fieldWithPath("jobType")
                        .type(JsonFieldType.STRING)
                        .description("분야"),
                fieldWithPath("careerLevel")
                        .type(JsonFieldType.STRING)
                        .description("연차")
        );
    }
}
