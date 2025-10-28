package com.example.question_board.dto.response;

import com.example.question_board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {

    private Long id;
    private String boardName;

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .boardName(board.getBoardName())
                .build();
    }
}
