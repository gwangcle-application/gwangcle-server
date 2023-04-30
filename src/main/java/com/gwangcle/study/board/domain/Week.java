package com.gwangcle.study.board.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Entity
public class Week implements Comparable<Week> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    protected Week() {
        //no-op
    }

    public Week(String name) {
        this.name = name;
    }

    @Builder
    public Week(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    @Override
    public int compareTo(Week w) {
        String[] order = {"월", "화", "수", "목", "금", "토", "일"};
        int thisWeekIndex = Arrays.asList(order).indexOf(this.getName());
        int otherWeekIndex = Arrays.asList(order).indexOf(w.getName());
        return Integer.compare(thisWeekIndex, otherWeekIndex);
    }
}
