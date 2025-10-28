package com.example.question_board.service;

import com.example.question_board.dto.request.PostRequest;
import com.example.question_board.dto.response.PostResponse;
import com.example.question_board.entity.Board;
import com.example.question_board.entity.Post;
import com.example.question_board.entity.status.PostStatus;
import com.example.question_board.repository.BoardRepository;
import com.example.question_board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;

    // 게시글 생성
    @Transactional
    public PostResponse createPost(Long boardId, PostRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다. id=" + boardId));

        Post post = Post.builder()
                .board(board)
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword())) // 비밀번호 암호화
                .email(request.getEmail())
                .title(request.getTitle())
                .content(request.getContent())
                .status(PostStatus.POSTED)
                .postedAt(LocalDateTime.now())
                .build();

        Post savedPost = postRepository.save(post);
        return PostResponse.forDetail(savedPost);
    }


    // 게시글 상세 조회
    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return PostResponse.forDetail(post);
    }

    // 게시판별 게시글 페이징 조회
    public Page<PostResponse> getPostsByBoard(Long boardId, Pageable pageable) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다. id=" + boardId));
        return postRepository.findByBoard(board, pageable).map(PostResponse::forList);
    }

    // 게시글 검색
    public Page<PostResponse> searchPosts(String keyword, Pageable pageable) {
        return postRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable)
                .map(PostResponse::forList);
    }

    // 게시글 수정
    @Transactional
    public PostResponse updatePost(Long postId, PostRequest request) {
        Post post = findPostById(postId);
        verifyPassword(request.getPassword(), post.getPassword());

        post.update(request.getTitle(), request.getContent());
        return PostResponse.forDetail(post);
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId, String password) {
        Post post = findPostById(postId);
        verifyPassword(password, post.getPassword());

        postRepository.delete(post);
    }

    /**
     * 게시글 ID로 게시글을 조회하고, 존재하지 않으면 예외를 발생시킵니다.
     * @param postId 게시글 ID
     * @return 조회된 Post 엔티티
     */
    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. id=" + postId));
    }

    /**
     * 입력된 비밀번호와 저장된 비밀번호가 일치하는지 확인합니다.
     * @param rawPassword 사용자 입력 비밀번호
     * @param encodedPassword 데이터베이스에 저장된 암호화된 비밀번호
     */
    private void verifyPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
