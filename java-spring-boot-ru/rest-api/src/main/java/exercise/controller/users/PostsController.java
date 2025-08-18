package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users/{id}/posts")
public class PostsController {
    private final List<Post> posts = new ArrayList<>(Data.getPosts());

    @GetMapping
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable("id") int id) {
        List<Post> userPosts = posts.stream()
                .filter(p -> p.getUserId() == id)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userPosts);
    }

    @PostMapping
    public ResponseEntity<Post> createPostForUser(@PathVariable("id") int id,
                                                  @RequestBody Post post) {
        post.setUserId(id);
        posts.add(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
// END
