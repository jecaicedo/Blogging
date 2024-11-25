package com.api.blogging.repositories;

import com.api.blogging.models.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends JpaRepository<TagModel, Integer> {
}
