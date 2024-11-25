package com.api.blogging.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
