package exercise.controller.users;

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
@RequestMapping("/api/users/{userId}/posts")
public class PostsController {

    @PostMapping
    public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post) {
        post.setUserId(userId);
        Data.getPosts().add(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Post> getPosts(@PathVariable int userId) {
        return Data.getPosts().stream()
                .filter(p -> p.getUserId() == userId)
                .collect(Collectors.toList());
    }
}

// END
