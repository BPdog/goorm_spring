package com.example.question_board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "reply")
// 답변
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "replied_at")
    private LocalDateTime repliedAt;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String status;

    private String title;

    private String content;
}
