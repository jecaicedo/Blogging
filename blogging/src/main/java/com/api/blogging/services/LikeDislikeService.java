package com.api.blogging.services;

import com.api.blogging.dto.LikeDislikeDTO;
import com.api.blogging.models.LikeDislikeModel;
import com.api.blogging.models.LikeDislikeTypeModel;
import com.api.blogging.models.PostModel;
import com.api.blogging.models.UserModel;
import com.api.blogging.repositories.ILikeDislikeRepository;
import com.api.blogging.repositories.ILikeDislikeTypeRepository;
import com.api.blogging.repositories.IPostRepository;
import com.api.blogging.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeDislikeService {

    @Autowired
    private ILikeDislikeRepository likeDislikeRepository;

    @Autowired
    private ILikeDislikeTypeRepository likeDislikeTypeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    public LikeDislikeModel createLikeDislike(LikeDislikeDTO likeDislikeDTO) {
        LikeDislikeModel likeDislike = new LikeDislikeModel();

        UserModel user = userRepository.findById((long) likeDislikeDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        likeDislike.setUser(user);

        PostModel post = postRepository.findById((long) likeDislikeDTO.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        likeDislike.setPost(post);

        LikeDislikeTypeModel type = likeDislikeTypeRepository.findById((long) likeDislikeDTO.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException("LikeDislike type not found"));
        likeDislike.setType(type);

        return likeDislikeRepository.save(likeDislike);
    }

    public Map<String, Long> getLikesAndDislikesForPost(int postId) {
        List<LikeDislikeModel> interactions = likeDislikeRepository.findByPostPostId(postId);
        Map<String, Long> result = new HashMap<>();
        result.put("likes", interactions.stream().filter(ld -> ld.getType().getTypeName().equals("LIKE")).count());
        result.put("dislikes", interactions.stream().filter(ld -> ld.getType().getTypeName().equals("DISLIKE")).count());
        return result;
    }
}
