package com.api.blogging.repositories;

import com.api.blogging.models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<CommentModel, Long> {
    List<CommentModel> findByPostPostId(int postId);
}
