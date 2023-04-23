package com.gwangcle.study.Member.service.dto;

import com.gwangcle.study.Member.domain.EmploymentStatus;
import com.gwangcle.study.Member.domain.Task;
import com.gwangcle.study.Member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private EmploymentStatus employmentStatus;
    private Task task;

    public MemberResponse() {
        //no-op
    }

    @Builder
    public MemberResponse(Long id, String name, EmploymentStatus employmentStatus, Task task) {
        this.id = id;
        this.name = name;
        this.employmentStatus = employmentStatus;
        this.task = task;
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
                .employmentStatus(member.getEmploymentStatus())
                .task(member.getTask())
                .build();
    }
}
