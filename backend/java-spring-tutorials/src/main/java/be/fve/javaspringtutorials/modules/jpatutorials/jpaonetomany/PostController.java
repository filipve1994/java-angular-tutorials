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
public class PostController {

    private final PostFacade facade;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return facade.getAllPosts(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody CreateNewPostDto post) {
        return facade.createPost(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody UpdatePostDto postRequest) {
        return facade.updatePost(postId, postRequest);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        facade.deletePost(postId);
        return ResponseEntity.ok().build();
    }

}
