package com.gwangcle.study.board.service;

import com.gwangcle.study.board.domain.*;
import com.gwangcle.study.board.service.dto.BoardRequest;
import com.gwangcle.study.board.service.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final WeekRepository weekRepository;
    private final TimeRepository timeRepository;

    @Transactional
    public BoardResponse save(BoardRequest boardRequest) {
        Board board = boardRepository.save(
                Board.builder()
                        .name(boardRequest.getName())
                        .content(boardRequest.getContent())
                        .difficulty(boardRequest.getDifficulty())
                        .location(boardRequest.getLocation())
                        .capacity(boardRequest.getCapacity())
                        .mention(boardRequest.getMention())
                        .build());
        setWeeks(boardRequest, board);
        setTimes(boardRequest, board);

        return BoardResponse.of(board);
    }

    private void setTimes(BoardRequest boardRequest, Board board) {
        final List<Time> times = new ArrayList<>();
        boardRequest.getTimes()
                .forEach(time -> times.add(timeRepository.save(
                        Time.builder()
                                .name(time.getName())
                                .board(board)
                                .build()
                )));

        board.setTimes(times);
    }

    private void setWeeks(BoardRequest boardRequest, Board board) {
        final List<Week> weeks = new ArrayList<>();
        boardRequest.getWeeks()
                .forEach(week -> weeks.add(weekRepository.save(
                        Week.builder()
                                .name(week.getName())
                                .board(board)
                                .build()
                )));
        board.setWeeks(weeks);
    }

    public List<BoardResponse> findAllBoards() {
        List<Board> boards = boardRepository.findAllTablesDesc();
        return BoardResponse.of(boards);
    }
}
