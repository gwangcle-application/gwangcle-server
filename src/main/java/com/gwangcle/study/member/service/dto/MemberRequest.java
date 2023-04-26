package com.gwangcle.study.member.service.dto;

import com.gwangcle.study.member.domain.CareerLevel;
import com.gwangcle.study.member.domain.JobType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequest {

    private String name;
    private CareerLevel careerLevel;
    private JobType jobType;

    protected MemberRequest() {
        //no-op
    }

    @Builder
    public MemberRequest(String name, CareerLevel careerLevel, JobType jobType) {
        this.name = name;
        this.careerLevel = careerLevel;
        this.jobType = jobType;
    }
}
