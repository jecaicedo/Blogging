package com.api.blogging.controllers;

import com.api.blogging.dto.PostDTO;
import com.api.blogging.services.PostService;
import com.api.blogging.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    // Endpoint para crear un nuevo post
    @PostMapping("/create")
    @PreAuthorize("hasRole('AUTHOR')")
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    // Endpoint para editar un post
    @PutMapping("/update/{postId}")
    @PreAuthorize("hasRole('AUTHOR')")
    public PostDTO updatePost(@PathVariable int postId, @RequestBody PostDTO postDTO) {
        return postService.updatePost(postId, postDTO);
    }

    // Endpoint para eliminar un post (solo AUTHOR puede eliminar sus posts, ADMIN puede eliminar cualquier post)
    @DeleteMapping("/delete/{postId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
    public void deletePost(@PathVariable int postId) {
        boolean isAdmin = false; // Aquí agregar lógica para verificar si el usuario es admin
        postService.deletePost(postId, isAdmin);
    }

    // Endpoint para publicar un post
    @PostMapping("/publish/{postId}")
    @PreAuthorize("hasRole('AUTHOR')")
    public void publishPost(@PathVariable int postId) {
        postService.publishPost(postId);
    }

    // Endpoint para despublicar un post
    @PostMapping("/unpublish/{postId}")
    @PreAuthorize("hasRole('AUTHOR')")
    public void unpublishPost(@PathVariable int postId) {
        postService.unpublishPost(postId);
    }

    // Endpoint para obtener todos los posts publicados
    @GetMapping("/get_all_posts_published")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    // Endpoint para agregar etiquetas a un post
    @PostMapping("/add_tags/{postId}")
    public void addTagsToPost(@PathVariable int postId, @RequestBody List<Integer> tagIds) {
        postService.addTagsToPost(postId, tagIds);
    }
}
