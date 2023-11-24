package com.example.service;

import com.example.dto.requestDto.CommentRequest;
import com.example.dto.responseDto.CommentResponse;

import java.util.List;

public interface CommentService {
    void createComment(CommentRequest commentRequest);

    void deleteComment(Integer id);

    CommentResponse updateComment(Integer id, CommentRequest commentRequest);

    List<CommentResponse> getAllComment();
}