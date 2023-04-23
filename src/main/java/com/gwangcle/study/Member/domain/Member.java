package com.gwangcle.study.Member.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private EmploymentStatus employmentStatus;
    @Enumerated(value = EnumType.STRING)
    private Task task;

    protected Member() {
        // no-op
    }

    @Builder
    public Member(Long id, String name, EmploymentStatus employmentStatus, Task task) {
        this.id = id;
        this.name = name;
        this.employmentStatus = employmentStatus;
        this.task = task;
    }
}
