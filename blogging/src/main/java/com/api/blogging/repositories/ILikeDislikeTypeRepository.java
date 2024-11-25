package com.api.blogging.repositories;

import com.api.blogging.models.LikeDislikeTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeDislikeTypeRepository extends JpaRepository<LikeDislikeTypeModel, Long> {
}
