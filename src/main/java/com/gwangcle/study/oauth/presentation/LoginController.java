package com.gwangcle.study.oauth.presentation;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.gwangcle.study.member.domain.Member;
import com.gwangcle.study.member.service.MemberService;
import com.gwangcle.study.member.service.dto.MemberResponse;
import com.gwangcle.study.oauth.dto.TokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@RestController
public class LoginController {

    private final MemberService memberService;
    private final GoogleIdTokenVerifier verifier;

    public LoginController(@Value("${google.client-id}") String clientId, MemberService memberService) {
        this.memberService = memberService;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    @PostMapping("/api/oauth/google/members")
    public ResponseEntity<MemberResponse> findAlreadyExistedMemberByToken(@RequestBody TokenRequest request) {
        MemberResponse memberResponse = verifyIDToken(request.getIdToken());
        return ResponseEntity.ok().body(memberResponse);
    }

    private MemberResponse verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
                //TODO 예외 처리
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String email = payload.getEmail();
            Optional<Member> member = memberService.findByEmail(email);
            if (member.isPresent()) {
                return MemberResponse.of(member.get());
            }
            return MemberResponse.builder()
                    .email(email)
                    .name((String) payload.get("name"))
                    .active(false)
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            return null;
            //TODO 예외 처리
        }
    }
}
