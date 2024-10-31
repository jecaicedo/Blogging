package com.api.blogging.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comment")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private CommentStatusModel status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostModel post;

    // Getters y Setters
    // ...
}
