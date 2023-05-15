package com.gwangcle.study.oauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenRequest {

    private String idToken;

    protected TokenRequest() {
        //no-op
    }

    @Builder
    public TokenRequest(String idToken) {
        this.idToken = idToken;
    }
}
