package com.api.blogging.controllers;

import com.api.blogging.dto.TagDTO;
import com.api.blogging.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    @Autowired
    private TagService tagService;

    // Endpoint para crear una nueva etiqueta
    @PostMapping("/create")
    public TagDTO createTag(@RequestBody TagDTO tagDTO) {
        return tagService.createTag(tagDTO);
    }

    // Endpoint para obtener todas las etiquetas
    @GetMapping("/get_all")
    public List<TagDTO> getAllTags() {
        return tagService.getAllTags();
    }

    // Endpoint para obtener una etiqueta por su ID
    @GetMapping("/get/{tagId}")
    public TagDTO getTagById(@PathVariable int tagId) {
        return tagService.getTagById(tagId);
    }

    // Endpoint para eliminar una etiqueta
    @DeleteMapping("/delete/{tagId}")
    public void deleteTag(@PathVariable int tagId) {
        tagService.deleteTag(tagId);
    }
}
