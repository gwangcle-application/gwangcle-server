package com.gwangcle.study.board.infra;

import com.gwangcle.study.board.domain.Board;
import com.gwangcle.study.board.domain.BoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaBoardRepository extends JpaRepository<Board, Long>, BoardRepository {

    @Override
    public Board save(Board board);

    @Query("select b from Board b order by b.id desc ")
    List<Board> findAllTablesDesc();
}
