package com.example.question_board.repository;

import com.example.question_board.entity.Board;
import com.example.question_board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByBoard(Board board, Pageable pageable);

    // 제목 또는 내용으로 검색하는 메서드 추가
    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
