package com.api.blogging.repositories;

import com.api.blogging.models.CommentStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentStatusRepository extends JpaRepository<CommentStatusModel, Long> {
}
