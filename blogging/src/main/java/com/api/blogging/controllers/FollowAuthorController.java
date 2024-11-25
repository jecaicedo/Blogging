package com.api.blogging.controllers;

import com.api.blogging.dto.FollowAuthorDTO;
import com.api.blogging.models.UserModel;
import com.api.blogging.services.FollowAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
public class FollowAuthorController {

    @Autowired
    private FollowAuthorService followAuthorService;

    @PostMapping("follow_author")
    public ResponseEntity<Void> followAuthor(@RequestBody FollowAuthorDTO followAuthorDTO) {
        followAuthorService.followAuthor(followAuthorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/{userId}")
    public List<UserModel> getFollowedAuthors(@PathVariable int userId) {
        return followAuthorService.getFollowedAuthors(userId);
    }
}
