package com.api.blogging.models;

import jakarta.persistence.*;

@Entity
@Table(name = "LikeDislikeType")
public class LikeDislikeTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    @Column(nullable = false, unique = true)
    private String typeName;

    // Getters y Setters
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
