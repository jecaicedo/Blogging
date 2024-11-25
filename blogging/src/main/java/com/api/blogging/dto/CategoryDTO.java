package com.api.blogging.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private int categoryId;
    private String name;
    private String description;
}
