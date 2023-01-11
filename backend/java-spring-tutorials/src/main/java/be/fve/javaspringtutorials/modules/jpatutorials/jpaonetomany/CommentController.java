package be.fve.javaspringtutorials.modules.jpatutorials.jpaonetomany;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/example")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final PostFacade facade;

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId,
                                                Pageable pageable) {
        return facade.getAllCommentsByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody CreateNewCommentToPostDto comment) {
//                                 @Valid @RequestBody Comment comment) {
        return facade.createComment(postId, comment);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody UpdateCommentToPostDto commentRequest) {
//                                 @Valid @RequestBody Comment commentRequest) {

//        if(!postRepository.existsById(postId)) {
//            throw new PostNotFoundException("PostId " + postId + " not found");
//        }
//
//        return commentRepository.findById(commentId).map(comment -> {
//            comment.setText(commentRequest.getText());
//            return commentRepository.save(comment);
//        }).orElseThrow(() -> new CommentNotFoundException("CommentId " + commentId + "not found"));

        return facade.updateComment(postId, commentId, commentRequest);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {

        facade.deleteComment(postId, commentId);

        return ResponseEntity.ok().build();

//        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
//            commentRepository.delete(comment);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new CommentNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }
}