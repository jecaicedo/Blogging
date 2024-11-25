package com.api.blogging.services;

import com.api.blogging.dto.*;
import com.api.blogging.models.*;
import com.api.blogging.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IPostStatusRepository postStatusRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private IPostTagRepository postTagRepository;

    public PostDTO createPost(PostDTO postDTO) {
        PostModel post = new PostModel();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());

        // Establecemos el estado por defecto como 'draft' o 'pending'
        PostStatusModel status = postStatusRepository.findByStatus("draft"); // Asegúrate que este estado exista
        post.setStatus(status);

        // Obtener usuario autenticado (debe ser un AUTHOR)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userRepository.findByUsername(auth.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        post.setUser(user);

        // Configurar categoría
        CategoryModel category = categoryRepository.findById((long) postDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        post.setCategory(category);

        // Guardar post
        post = postRepository.save(post);

        return new PostDTO(post.getPostId(), post.getTitle(), post.getContent(), status.getStatus(), post.getCreatedAt(), post.getUpdatedAt(), user.getId(), category.getCategoryId(), new int[]{});
    }

    public PostDTO updatePost(long postId, PostDTO postDTO) {
        PostModel post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUpdatedAt(new Date());

        // Actualiza el estado
        PostStatusModel status = postStatusRepository.findByStatus(postDTO.getStatus());
        post.setStatus(status);

        // Actualizar la categoría
        CategoryModel category = categoryRepository.findById((long) postDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        post.setCategory(category);

        post = postRepository.save(post);
        return new PostDTO(post.getPostId(), post.getTitle(), post.getContent(), status.getStatus(), post.getCreatedAt(), post.getUpdatedAt(), post.getUser().getId(), post.getCategory().getCategoryId(), new int[]{});
    }

    public void deletePost(long postId, boolean isAdmin) {
        PostModel post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        if (!isAdmin && post.getUser().getId() != getAuthenticatedUserId()) {
            throw new RuntimeException("You do not have permission to delete this post");
        }
        postRepository.delete(post);
    }

    public void publishPost(long postId) {
        PostModel post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        PostStatusModel status = postStatusRepository.findByStatus("published"); // Asegúrate de tener este estado
        post.setStatus(status);
        post.setUpdatedAt(new Date());
        postRepository.save(post);
    }

    public void unpublishPost(long postId) {
        PostModel post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        PostStatusModel status = postStatusRepository.findByStatus("draft");
        post.setStatus(status);
        post.setUpdatedAt(new Date());
        postRepository.save(post);
    }

    public List<PostDTO> getAllPosts() {
        List<PostModel> posts = postRepository.findAllByStatusStatus("published"); // Solo posts publicados
        // Convierte PostModel a PostDTO
        return posts.stream().map(post -> new PostDTO(post.getPostId(), post.getTitle(), post.getContent(), post.getStatus().getStatus(), post.getCreatedAt(), post.getUpdatedAt(), post.getUser().getId(), post.getCategory().getCategoryId(), new int[]{})).toList();
    }

    private int getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userRepository.findByUsername(auth.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    public void addTagsToPost(int postId, List<Integer> tagIds) {
        PostModel post = postRepository.findById((long) postId).orElseThrow(() -> new RuntimeException("Post not found"));

        for (int tagId : tagIds) {
            PostTagModel postTag = new PostTagModel();
            postTag.setPostId(postId);
            postTag.setTagId(tagId);
            postTag.setPost(post);
            TagDTO tagDTO = tagService.getTagById(tagId);
            TagModel tagModel = convertDtoToModel(tagDTO);
            postTag.setTag(tagModel);
            postTagRepository.save(postTag);
        }
    }
    public TagModel convertDtoToModel(TagDTO tagDTO) {
        TagModel tagModel = new TagModel();
        tagModel.setTagId(tagDTO.getTagId());
        tagModel.setName(tagDTO.getName());
        return tagModel;
    }
}
