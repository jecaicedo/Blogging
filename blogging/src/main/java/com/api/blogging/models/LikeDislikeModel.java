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

}
