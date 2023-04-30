package com.gwangcle.study.board.presentation;

import com.gwangcle.study.board.service.BoardService;
import com.gwangcle.study.board.service.dto.BoardRequest;
import com.gwangcle.study.board.service.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity<BoardResponse> save(@RequestBody BoardRequest boardRequest) {
        BoardResponse board = boardService.save(boardRequest);
        return ResponseEntity.created(URI.create("/api/boards/" + board.getId())).body(board);
    }

    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boards = boardService.findAllBoards();
        return ResponseEntity.ok().body(boards);
    }
}
