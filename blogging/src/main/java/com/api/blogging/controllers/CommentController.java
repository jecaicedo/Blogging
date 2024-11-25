package com.api.blogging.controllers;

import com.api.blogging.dto.CommentDTO;
import com.api.blogging.dto.StatusRequestDTO;
import com.api.blogging.models.CommentModel;
import com.api.blogging.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentModel> createComment(@RequestBody CommentDTO commentDTO) {
        CommentModel createdComment = commentService.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/post/{postId}")
    public List<CommentModel> getCommentsByPost(@PathVariable int postId) {
        return commentService.getCommentsByPost(postId);
    }

    @PutMapping("/moderate/{commentId}")
    public ResponseEntity<CommentModel> moderateComment(@PathVariable int commentId, @RequestBody StatusRequestDTO statusRequest) {
        CommentModel moderatedComment = commentService.moderateComment(commentId, statusRequest.getStatusId());
        return ResponseEntity.ok(moderatedComment);
    }
}
