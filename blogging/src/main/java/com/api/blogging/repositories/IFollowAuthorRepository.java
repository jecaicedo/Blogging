package com.api.blogging.repositories;

import com.api.blogging.models.CategoryModel;
import com.api.blogging.models.FollowAuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowAuthorRepository extends JpaRepository<FollowAuthorModel, Long> {
    public List<FollowAuthorModel> findByFollower_Id(int userId);
    List<FollowAuthorModel> findByFollowed_Id(int userId);
}
