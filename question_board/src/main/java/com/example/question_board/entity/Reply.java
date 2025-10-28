package com.example.question_board.entity;

import com.example.question_board.entity.status.ReplyStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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

    @Column(name = "user_name")
    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private ReplyStatus status;

    private String title;

    private String content;

    @CreatedDate
    @Column(name = "replied_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


}
