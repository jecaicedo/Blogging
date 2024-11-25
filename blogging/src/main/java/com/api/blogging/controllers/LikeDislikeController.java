package com.api.blogging.controllers;

import com.api.blogging.dto.LikeDislikeDTO;
import com.api.blogging.models.LikeDislikeModel;
import com.api.blogging.services.LikeDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/likes-dislikes")
@RequiredArgsConstructor
public class LikeDislikeController {

    @Autowired
    private LikeDislikeService likeDislikeService;

    @PostMapping("/create")
    public ResponseEntity<LikeDislikeModel> createLikeDislike(@RequestBody LikeDislikeDTO likeDislikeDTO) {
        LikeDislikeModel createdLikeDislike = likeDislikeService.createLikeDislike(likeDislikeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLikeDislike);
    }

    @GetMapping("/post/{postId}")
    public Map<String, Long> getLikesAndDislikesForPost(@PathVariable int postId) {
        return likeDislikeService.getLikesAndDislikesForPost(postId);
    }
}
