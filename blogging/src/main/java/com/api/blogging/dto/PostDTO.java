package com.api.blogging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private int postId;
    private String title;
    private String content;
    private String status; // Cambié status a String para la representación
    private Date createdAt;
    private Date updatedAt;
    private int userId;
    private int categoryId;
    private int[] tags; // Array de IDs de tags
}
