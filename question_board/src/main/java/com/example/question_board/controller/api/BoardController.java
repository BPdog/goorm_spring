package com.example.question_board.controller.api;

import com.example.question_board.dto.request.BoardRequest;
import com.example.question_board.dto.response.BoardResponse;
import com.example.question_board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 새로운 게시판 생성
    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody @Valid BoardRequest request) {
        BoardResponse response = boardService.createBoard(request.getBoardName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 특정 게시판 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable("id") Long boardId) {
        BoardResponse response = boardService.getBoard(boardId);
        return ResponseEntity.ok(response);
    }



    // 전체 게시판 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<BoardResponse> response = boardService.getAllBoards();
        return ResponseEntity.ok(response);
    }
}
