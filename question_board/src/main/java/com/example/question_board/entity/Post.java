package com.example.question_board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.question_board.entity.status.PostStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post")
// 글
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private String title;

    private String content;

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public void changeStatus(PostStatus status) {
        this.status = status;
    }
}



