package com.api.blogging.repositories;

import com.api.blogging.models.PostTagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostTagRepository extends JpaRepository<PostTagModel, Long> {
}
