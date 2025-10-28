package com.example.question_board.service;

import com.example.question_board.dto.response.BoardResponse;
import com.example.question_board.entity.Board;
import com.example.question_board.entity.status.BoardStatus;
import com.example.question_board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    // 새로운 게시판 생성
    @Transactional
    public BoardResponse createBoard(String boardName) {
        Board board = Board.builder()
                .boardName(boardName)
                .status(BoardStatus.ACTIVE) // 기본 상태를 ACTIVE로 설정
                .build();
        Board savedBoard = boardRepository.save(board);
        return BoardResponse.from(savedBoard);
    }

    // 특정 게시판 상세 조회
    public BoardResponse getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다. id=" + boardId));
        return BoardResponse.from(board);
    }

    // 전체 게시판 목록 조회
    public List<BoardResponse> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }
}
