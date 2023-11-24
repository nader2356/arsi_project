package com.example.service.serviceImpl;

import com.example.dto.requestDto.CommentRequest;
import com.example.dto.responseDto.CommentResponse;
import com.example.entity.Comment;
import com.example.exception.NotFoundException;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(CommentRequest commentRequest) {
        Comment comment = Comment.builder()
                .text(commentRequest.getText())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentResponse updateComment(Integer id, CommentRequest commentRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setText(commentRequest.getText());
            commentRepository.save(existingComment);
            return CommentResponse.makeComment(existingComment);
        } else {
            throw new NotFoundException("Comment with ID : " + id);
        }
    }

    @Override
    public List<CommentResponse> getAllComment() {
        List<Comment> comments = commentRepository.findAllComment();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for(Comment comment : comments) {
            CommentResponse commentDto = CommentResponse.makeComment(comment);
            commentResponses.add(commentDto);
        }
        return  commentResponses;
    }
    }
