package com.api.blogging.models;

import java.io.Serializable;

public class PostTagId implements Serializable {
    private int postId;
    private int tagId;

    // Getters, Setters, hashCode, equals
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

    @Override
    public int hashCode() {
        return 31 * postId + tagId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PostTagId)) return false;
        PostTagId other = (PostTagId) obj;
        return postId == other.postId && tagId == other.tagId;
    }
}
