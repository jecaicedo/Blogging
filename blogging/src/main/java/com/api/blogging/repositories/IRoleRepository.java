package com.api.blogging.repositories;

import com.api.blogging.models.UserModel;
import com.api.blogging.models.UserRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository  extends JpaRepository<UserRoleModel, Long> {
}
