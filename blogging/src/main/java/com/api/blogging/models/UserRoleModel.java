package com.api.blogging.models;

import jakarta.persistence.*;

@Entity
@Table(name = "UserRole")
public class UserRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    // Getters y Setters
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
