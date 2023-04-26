package com.gwangcle.study.member.service;

import com.gwangcle.study.member.domain.Member;
import com.gwangcle.study.member.domain.MemberRepository;
import com.gwangcle.study.member.service.dto.MemberRequest;
import com.gwangcle.study.member.service.dto.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public List<MemberResponse> findAllMembers() {
        List<Member> allMembers = memberRepository.findAllMembersDesc();
        return MemberResponse.of(allMembers);
    }

    @Transactional
    public MemberResponse save(MemberRequest memberRequest) {
        Member member = memberRepository.save(Member.builder()
                .name(memberRequest.getName())
                .careerLevel(memberRequest.getCareerLevel())
                .jobType(memberRequest.getJobType())
                .build()
        );
        return MemberResponse.of(member);
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        return MemberResponse.of(Member.builder()
                .id(member.getId())
                .name(member.getName())
                .careerLevel(member.getCareerLevel())
                .jobType(member.getJobType())
                .build());
    }

    @Transactional
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
