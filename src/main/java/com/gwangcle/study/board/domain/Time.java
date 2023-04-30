package com.gwangcle.study.board.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Entity
public class Time implements Comparable<Time> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    protected Time() {
        //no-op
    }

    public Time(String name) {
        this.name = name;
    }

    @Builder
    public Time(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    @Override
    public int compareTo(Time t) {
        final String[] order = {"오전", "오후", "저녁"};
        int thisDayIndex = Arrays.asList(order).indexOf(this.getName());
        int otherDayIndex = Arrays.asList(order).indexOf(t.getName());
        return Integer.compare(thisDayIndex, otherDayIndex);
    }
}
