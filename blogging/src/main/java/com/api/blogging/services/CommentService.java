package com.api.blogging.services;

import com.api.blogging.dto.CommentDTO;
import com.api.blogging.models.CommentModel;
import com.api.blogging.models.CommentStatusModel;
import com.api.blogging.models.PostModel;
import com.api.blogging.models.UserModel;
import com.api.blogging.repositories.ICommentRepository;
import com.api.blogging.repositories.ICommentStatusRepository;
import com.api.blogging.repositories.IPostRepository;
import com.api.blogging.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private ICommentStatusRepository commentStatusRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    public CommentModel createComment(CommentDTO commentDTO) {
        CommentModel comment = new CommentModel();
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(new Date());

        UserModel user = userRepository.findById((long) commentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        comment.setUser(user);

        PostModel post = postRepository.findById((long) commentDTO.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        comment.setPost(post);

        CommentStatusModel status = commentStatusRepository.findById((long) commentDTO.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Comment status not found"));
        comment.setStatus(status);

        return commentRepository.save(comment);
    }

    public List<CommentModel> getCommentsByPost(int postId) {
        return commentRepository.findByPostPostId(postId);
    }

    public CommentModel moderateComment(int commentId, int statusId) {
        CommentModel comment = commentRepository.findById((long) commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        CommentStatusModel status = commentStatusRepository.findById((long) statusId)
                .orElseThrow(() -> new EntityNotFoundException("Comment status not found"));

        comment.setStatus(status);
        return commentRepository.save(comment);
    }
}
