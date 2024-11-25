package com.api.blogging.services;

import com.api.blogging.dto.TagDTO;
import com.api.blogging.models.TagModel;
import com.api.blogging.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private ITagRepository tagRepository;

    // Crear una nueva etiqueta
    public TagDTO createTag(TagDTO tagDTO) {
        TagModel tag = new TagModel();
        tag.setName(tagDTO.getName());
        tag = tagRepository.save(tag);
        return new TagDTO(tag.getTagId(), tag.getName());
    }

    // Obtener todas las etiquetas
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new TagDTO(tag.getTagId(), tag.getName()))
                .toList();
    }

    // Obtener una etiqueta por ID
    public TagDTO getTagById(int tagId) {
        Optional<TagModel> tag = tagRepository.findById(tagId);
        return tag.map(t -> new TagDTO(t.getTagId(), t.getName())).orElse(null);
    }

    // Eliminar una etiqueta
    public void deleteTag(int tagId) {
        tagRepository.deleteById(tagId);
    }
}
