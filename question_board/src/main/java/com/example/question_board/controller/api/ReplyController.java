package com.example.question_board.controller.api;

import com.example.question_board.dto.request.ReplyRequest;
import com.example.question_board.dto.response.ReplyResponse;
import com.example.question_board.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<ReplyResponse> createReply(
            @RequestParam("postId") Long postId,
            @Valid @RequestBody ReplyRequest request
    ) {
        ReplyResponse response = replyService.createReply(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 게시글별 댓글 목록 조회
    @GetMapping
    public ResponseEntity<List<ReplyResponse>> getRepliesByPost(@RequestParam("postId") Long postId) {
        List<ReplyResponse> response = replyService.getRepliesByPost(postId);
        return ResponseEntity.ok(response);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(
            @PathVariable("id") Long replyId,
            @RequestBody Map<String, String> request
    ) {
        String password = request.get("password");
        if (password == null) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        replyService.deleteReply(replyId, password);
        return ResponseEntity.noContent().build();
    }
}
