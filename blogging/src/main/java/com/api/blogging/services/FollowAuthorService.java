package com.api.blogging.services;

import com.api.blogging.dto.FollowAuthorDTO;
import com.api.blogging.models.FollowAuthorModel;
import com.api.blogging.models.UserModel;
import com.api.blogging.repositories.IFollowAuthorRepository;
import com.api.blogging.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowAuthorService {

    @Autowired
    private IFollowAuthorRepository followAuthorRepository;

    @Autowired
    private IUserRepository userRepository;

    public void followAuthor(FollowAuthorDTO followAuthorDTO) {
        UserModel follower = userRepository.findById((long) followAuthorDTO.getFollowerId())
                .orElseThrow(() -> new EntityNotFoundException("Follower user not found"));
        UserModel followed = userRepository.findById((long) followAuthorDTO.getFollowedId())
                .orElseThrow(() -> new EntityNotFoundException("Followed user not found"));

        FollowAuthorModel follow = new FollowAuthorModel();
        follow.setFollower(follower);
        follow.setFollowed(followed);

        followAuthorRepository.save(follow);
    }

    public List<UserModel> getFollowedAuthors(int userId) {
        List<FollowAuthorModel> follows = followAuthorRepository.findByFollower_Id(userId);
        return follows.stream().map(FollowAuthorModel::getFollowed).collect(Collectors.toList());
    }
}
