package com.example.controller;

import com.example.dto.requestDto.CommentRequest;
import com.example.dto.responseDto.CommentResponse;
import com.example.service.CommentService;
import com.example.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping(Constants.APP_ROOT_ADMIN + "/comment")
@CrossOrigin("*")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody @Valid CommentRequest commentRequest)
    {
        if (commentRequest != null) {
            commentService.createComment(commentRequest);
            return ResponseEntity.ok("Comment created successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid Comment data");
        }}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Delete successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Integer id, @RequestBody CommentRequest commentRequest) {
        CommentResponse updatedComment = commentService.updateComment(id, commentRequest);
        return ResponseEntity.ok(updatedComment);
    }
    @GetMapping("/allcomment")
    public ResponseEntity<List<CommentResponse>> getAllComment() {
        List<CommentResponse> comments = commentService.getAllComment();
        return ResponseEntity.ok(comments);
    }

}