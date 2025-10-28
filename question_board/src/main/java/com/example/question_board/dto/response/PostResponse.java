package com.example.question_board.dto.response;

import com.example.question_board.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 게시글 조회를 위한 응답 DTO
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private String boardName;
    private String userName;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<ReplyResponse> replies;


    public static PostResponse forList(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .boardName(post.getBoard().getBoardName())
                .userName(post.getUserName())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .build();
    }


    public static PostResponse forDetail(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .boardName(post.getBoard().getBoardName())
                .userName(post.getUserName())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .replies(post.getReplyList().stream()
                        .map(ReplyResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
