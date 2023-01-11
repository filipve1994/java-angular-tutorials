package be.fve.javaspringtutorials.modules.jpatutorials.jpaonetomany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostFacade {
    Page<Post> getAllPosts(Pageable pageable);

    Post createPost(CreateNewPostDto post);

    Post updatePost(Long postId, UpdatePostDto postRequest);

    void deletePost(Long postId);

    Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable);

    Comment createComment(Long postId, CreateNewCommentToPostDto comment);

    Comment updateComment(Long postId, Long commentId, UpdateCommentToPostDto commentRequest);

    void deleteComment(Long postId, Long commentId);
}
