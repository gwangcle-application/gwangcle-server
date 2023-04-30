package com.gwangcle.study.board.domain;

import java.util.List;

public interface BoardRepository {

    Board save(Board board);

    List<Board> findAllTablesDesc();
}
