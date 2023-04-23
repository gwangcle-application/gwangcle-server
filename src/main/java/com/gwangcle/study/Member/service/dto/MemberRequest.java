package com.gwangcle.study.Member.service.dto;

import com.gwangcle.study.Member.domain.EmploymentStatus;
import com.gwangcle.study.Member.domain.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequest {

    private String name;
    private EmploymentStatus employmentStatus;
    private Task task;

    protected MemberRequest() {
        //no-op
    }

    @Builder
    public MemberRequest(String name, EmploymentStatus employmentStatus, Task task) {
        this.name = name;
        this.employmentStatus = employmentStatus;
        this.task = task;
    }
}
