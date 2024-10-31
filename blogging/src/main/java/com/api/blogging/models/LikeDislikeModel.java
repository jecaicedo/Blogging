package com.api.blogging.models;

import jakarta.persistence.*;

@Entity
@Table(name = "LikeDislike")
public class LikeDislikeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeDislikeId;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private LikeDislikeTypeModel type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostModel post;

    // Getters y Setters
    public int getLikeDislikeId() {
        return likeDislikeId;
    }

    public void setLikeDislikeId(int likeDislikeId) {
        this.likeDislikeId = likeDislikeId;
    }

    public LikeDislikeTypeModel getType() {
        return type;
    }

    public void setType(LikeDislikeTypeModel type) {
        this.type = type;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }
}
