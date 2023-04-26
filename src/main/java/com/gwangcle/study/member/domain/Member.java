package com.gwangcle.study.member.domain;

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
    private CareerLevel careerLevel;
    @Enumerated(value = EnumType.STRING)
    private JobType jobType;

    protected Member() {
        // no-op
    }

    @Builder
    public Member(Long id, String name, CareerLevel careerLevel, JobType jobType) {
        this.id = id;
        this.name = name;
        this.careerLevel = careerLevel;
        this.jobType = jobType;
    }
}
