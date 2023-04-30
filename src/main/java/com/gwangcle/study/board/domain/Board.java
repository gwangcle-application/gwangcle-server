package com.gwangcle.study.board.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    private String location;
    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Week> weeks = new ArrayList<>();
    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Time> times = new ArrayList<>();
    private Long capacity;
    private String mention;

    protected Board() {
        //no-op
    }

    @Builder
    public Board(Long id, String name, String content, Difficulty difficulty, String location, Long capacity, String mention) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.difficulty = difficulty;
        this.location = location;
        this.capacity = capacity;
        this.mention = mention;
    }

    public void setWeeks(List<Week> weeks){
        this.weeks = weeks;
    }

    public void setTimes(List<Time> times){
        this.times = times;
    }
}
