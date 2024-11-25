package com.api.blogging.repositories;

import com.api.blogging.models.PostModel;
import com.api.blogging.models.PostStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostStatusRepository extends JpaRepository<PostStatusModel, Long> {
    PostStatusModel findByStatus(String name);
}
