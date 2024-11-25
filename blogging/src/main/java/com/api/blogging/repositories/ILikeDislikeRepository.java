package com.api.blogging.repositories;

import com.api.blogging.models.LikeDislikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeDislikeRepository extends JpaRepository<LikeDislikeModel, Long> {
    List<LikeDislikeModel> findByPostPostId(int postId);
}
