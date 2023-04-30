package com.gwangcle.study.board.service.dto;

import com.gwangcle.study.board.domain.*;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
public class BoardRequest {
    private String name;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    private List<Week> weeks;
    private List<Time> times;
    private String location;
    private Long capacity;
    private String mention;

    protected BoardRequest() {
        //no-op
    }

    @Builder
    public BoardRequest(String name, String content, Difficulty difficulty, List<Week> weeks, List<Time> times, String location, String capacity, String mention) {
        this.name = name;
        this.content = content;
        this.difficulty = difficulty;
        this.weeks = weeks;
        this.times = times;
        this.location = location;
        this.capacity = Long.getLong(capacity);
        this.mention = mention;
    }
}
