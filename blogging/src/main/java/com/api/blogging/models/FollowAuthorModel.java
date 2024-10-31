package com.api.blogging.models;

import jakarta.persistence.*;

@Entity
@Table(name = "FollowAuthor")
public class FollowAuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followId;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private UserModel follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false)
    private UserModel followed;

    // Getters y Setters
    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public UserModel getFollower() {
        return follower;
    }

    public void setFollower(UserModel follower) {
        this.follower = follower;
    }

    public UserModel getFollowed() {
        return followed;
    }

    public void setFollowed(UserModel followed) {
        this.followed = followed;
    }
}
