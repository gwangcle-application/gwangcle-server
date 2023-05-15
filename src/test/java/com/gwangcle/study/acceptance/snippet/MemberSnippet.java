package com.gwangcle.study.acceptance.snippet;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class MemberSnippet {

    public static Snippet memberRequestSnippet() {
        return requestFields(
                fieldWithPath("email")
                        .type(JsonFieldType.STRING)
                        .description("이메일"),
                fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("이름(암호화)"),
                fieldWithPath("jobType")
                        .type(JsonFieldType.STRING)
                        .description("분야"),
                fieldWithPath("careerLevel")
                        .type(JsonFieldType.STRING)
                        .description("연차")
        );
    }

    public static Snippet memberResponseSnippet() {
        return responseFields(
                fieldWithPath("id")
                        .type(JsonFieldType.NUMBER)
                        .description("아이디"),
                fieldWithPath("email")
                        .type(JsonFieldType.STRING)
                        .description("이메일"),
                fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("이름(암호화)"),
                fieldWithPath("jobType")
                        .type(JsonFieldType.STRING)
                        .description("분야"),
                fieldWithPath("careerLevel")
                        .type(JsonFieldType.STRING)
                        .description("연차"),
                fieldWithPath("role")
                        .type(JsonFieldType.STRING)
                        .description("접근 권한"),
                fieldWithPath("active")
                        .type(JsonFieldType.BOOLEAN)
                        .description("아이디 존재 유무")
        );
    }
}
