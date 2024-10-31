package com.api.blogging.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Tag")
public class TagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(nullable = false)
    private String name;

    // Getters y Setters
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
