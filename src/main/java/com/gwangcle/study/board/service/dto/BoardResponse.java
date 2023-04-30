package com.gwangcle.study.board.service.dto;

import com.gwangcle.study.board.domain.Board;
import com.gwangcle.study.board.domain.Difficulty;
import com.gwangcle.study.board.domain.Time;
import com.gwangcle.study.board.domain.Week;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class BoardResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    private String location;
    private List<String> weeks = new ArrayList<>();
    private List<String> times = new ArrayList<>();
    private Long capacity;
    private String mention;

    protected BoardResponse() {
        //no-op
    }

    @Builder
    public BoardResponse(Long id, String name, String content, Difficulty difficulty, String location, List<Week> weeks, List<Time> times, Long capacity, String mention) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.difficulty = difficulty;
        this.location = location;
        this.capacity = capacity;
        this.mention = mention;
        Collections.sort(weeks);
        Collections.sort(times);
        weeks
                .forEach(w -> this.weeks.add(w.getName()));
        times
                .forEach(t -> this.times.add(t.getName()));
    }

    public static BoardResponse of(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .content(board.getContent())
                .difficulty(board.getDifficulty())
                .weeks(board.getWeeks())
                .times(board.getTimes())
                .location(board.getLocation())
                .capacity(board.getCapacity())
                .mention(board.getMention())
                .build();
    }

    public static List<BoardResponse> of(List<Board> boards) {
        final List<BoardResponse> tableList = new ArrayList<>();
        boards
                .forEach(t -> tableList.add(BoardResponse.of(t)));
        return tableList;
    }
}
