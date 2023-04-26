package com.gwangcle.study.member.service.dto;

import com.gwangcle.study.member.domain.CareerLevel;
import com.gwangcle.study.member.domain.JobType;
import com.gwangcle.study.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private CareerLevel careerLevel;
    private JobType jobType;

    protected MemberResponse() {
        //no-op
    }

    @Builder
    public MemberResponse(Long id, String name, CareerLevel careerLevel, JobType jobType) {
        this.id = id;
        this.name = name;
        this.careerLevel = careerLevel;
        this.jobType = jobType;
    }

    public static List<MemberResponse> of(List<Member> members) {
        final List<MemberResponse> memberResponses = new ArrayList<>();
        members
                .forEach(member -> memberResponses.add(MemberResponse.of(member)));
        return memberResponses;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .jobType(member.getJobType())
                .careerLevel(member.getCareerLevel())
                .build();
    }
}
