package be.fve.javaspringtutorials.modules.jpatutorials.jpaonetomany;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class PostService implements PostFacade {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post createPost(CreateNewPostDto post) {
        Post savePost = new Post();
        savePost.setDescription(post.getDescription());
        savePost.setContent(post.getContent());
        savePost.setTitle(post.getTitle());
        return postRepository.save(savePost);
    }

    @Override
    public Post updatePost(Long postId, UpdatePostDto postRequest) {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(() -> new PostNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("PostId " + postId + " not found"));
        postRepository.delete(post);
    }

    @Override
    public Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public Comment createComment(Long postId, CreateNewCommentToPostDto comment) {
        return postRepository.findById(postId).map(post -> {
            Comment commentToSave = new Comment();
            commentToSave.setText(comment.getText());
            commentToSave.setPost(post);
            return commentRepository.save(commentToSave);
        }).orElseThrow(() -> new PostNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, UpdateCommentToPostDto commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new PostNotFoundException("PostId " + postId + " not found");
        }

        Comment commentToSave = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("CommentId " + commentId + "not found"));
        commentToSave.setText(commentRequest.getText());
        return commentRepository.save(commentToSave);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Comment byIdAndPostId = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id " + commentId + " and postId " + postId));

        commentRepository.delete(byIdAndPostId);
    }
}
