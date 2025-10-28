package com.example.question_board.controller.api;

import com.example.question_board.dto.request.PostDeleteRequest;
import com.example.question_board.dto.request.PostRequest;
import com.example.question_board.dto.response.PostResponse;
import com.example.question_board.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestParam("boardId") Long boardId, @RequestBody @Valid PostRequest request) {
        PostResponse response = postService.createPost(boardId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 게시글 상세 조회
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long postId) {
        PostResponse response = postService.getPost(postId);
        return ResponseEntity.ok(response);
    }

    // 게시판별 게시글 페이징 조회
    @GetMapping("/paged")
    public ResponseEntity<Page<PostResponse>> getPostsByBoard(
            @RequestParam("boardId") Long boardId,
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponse> response = postService.getPostsByBoard(boardId, pageable);
        return ResponseEntity.ok(response);
    }

    // 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<Page<PostResponse>> searchPosts(
            @RequestParam("keyword") String keyword,
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponse> response = postService.searchPosts(keyword, pageable);
        return ResponseEntity.ok(response);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable("id") Long postId, @RequestBody @Valid PostRequest request) {
        PostResponse response = postService.updatePost(postId, request);
        return ResponseEntity.ok(response);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId, @RequestBody @Valid PostDeleteRequest request) {
        postService.deletePost(postId, request.getPassword());
        return ResponseEntity.noContent().build();
    }
}