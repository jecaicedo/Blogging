package com.api.blogging.repositories;

import com.api.blogging.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<PostModel, Long> {
    List<PostModel> findAllByStatusStatus(String status);
}
