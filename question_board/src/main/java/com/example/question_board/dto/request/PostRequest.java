package com.example.question_board.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    @NotBlank(message = "작성자 이름은 필수입니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;



    private String content;
}
