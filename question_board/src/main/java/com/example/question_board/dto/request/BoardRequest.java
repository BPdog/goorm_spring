package com.example.question_board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequest {

    @NotBlank(message = "게시판 이름은 비워둘 수 없습니다.")
    private String boardName;
}
