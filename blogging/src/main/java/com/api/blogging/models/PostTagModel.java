package com.api.blogging.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PostTag")
@IdClass(PostTagId.class)
public class PostTagModel {

    @Id
    @Column(name = "post_id")
    private int postId;

    @Id
    @Column(name = "tag_id")
    private int tagId;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private PostModel post;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private TagModel tag;

    // Getters y Setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }
}
