package com.api.blogging.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PostStatus")
public class PostStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    @Column(nullable = false, unique = true)
    private String statusName;

    // Getters y Setters
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
