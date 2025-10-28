package com.example.question_board.dto.response;

import com.example.question_board.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponse {

    private Long id;
    private String userName;
    private String content;
    private LocalDateTime createdAt;

    public static ReplyResponse from(Reply reply) {
        return ReplyResponse.builder()
                .id(reply.getId())
                .userName(reply.getUserName())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }
}
