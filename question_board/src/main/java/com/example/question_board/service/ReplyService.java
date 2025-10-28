package com.example.question_board.service;

import com.example.question_board.dto.request.ReplyRequest;
import com.example.question_board.dto.response.ReplyResponse;
import com.example.question_board.entity.Post;
import com.example.question_board.entity.Reply;
import com.example.question_board.repository.PostRepository;
import com.example.question_board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    // 댓글 작성
    @Transactional
    public ReplyResponse createReply(Long postId, ReplyRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. id=" + postId));

        Reply reply = Reply.builder()
                .post(post)
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword())) // 비밀번호 암호화
                .content(request.getContent())
                .status("POSTED")
                .repliedAt(LocalDateTime.now())
                .build();

        Reply savedReply = replyRepository.save(reply);
        return ReplyResponse.from(savedReply);
    }

    // 게시글별 댓글 목록 조회
    public List<ReplyResponse> getRepliesByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. id=" + postId));

        return post.getReplyList().stream()
                .map(ReplyResponse::from)
                .collect(Collectors.toList());
    }

    // 댓글 삭제
    @Transactional
    public void deleteReply(Long replyId, String password) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다. id=" + replyId));

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, reply.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        replyRepository.delete(reply);
    }



}
