package com.gwangcle.study.member.presentation;

import com.gwangcle.study.member.service.MemberService;
import com.gwangcle.study.member.service.dto.MemberRequest;
import com.gwangcle.study.member.service.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest memberRequest) {
        MemberResponse member = memberService.save(memberRequest);
        return ResponseEntity.created(URI.create("api/members/" + member.getId())).body(member);
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id) {
        MemberResponse member = memberService.findById(id);
        return ResponseEntity.ok().body(member);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<MemberResponse>> findAllMembers() {
        List<MemberResponse> members = memberService.findAllMembers();
        return ResponseEntity.ok().body(members);
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
