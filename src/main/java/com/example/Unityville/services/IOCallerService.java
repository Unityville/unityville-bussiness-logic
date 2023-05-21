package com.example.Unityville.services;

import com.example.Unityville.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IOCallerService {
    private final String IOUrl = "http://core:8070";

    public CommunityOfPractice saveCOP(CommunityOfPractice cop) {
        return new RestTemplate().postForObject(IOUrl + "/cops", cop, CommunityOfPractice.class);
    }

    public void putCOP(CommunityOfPractice cop) {
        new RestTemplate().put(IOUrl + "/cops", cop);
    }

    public List<CommunityOfPractice> findAllCOP() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/cops", CommunityOfPractice[].class)));
    }

    public CommunityOfPractice getCOPById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/cops/{id}", CommunityOfPractice.class, id);
    }

    public List<Group> findAllGroups() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/groups_cop", Group[].class)));
    }

    public Group saveGroup(Group group) {
        return new RestTemplate().postForObject(IOUrl + "/groups_cop", group, Group.class);
    }

    public Group getGroupById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/groups_cop/{id}", Group.class, id);
    }


    public List<Like> findAllLikes() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/likes", Like[].class)));
    }

    public void saveLike(Like like) {
        new RestTemplate().postForObject(IOUrl + "/likes", like, Like.class);
    }

    public void deleteLikeByUserAndPost(Long userId, Long postId) {
        new RestTemplate().delete(IOUrl + "/likes/user/{userId}/post/{postId}", userId, postId);
    }

    public void deleteLikeByUserAndComment(Long userId, Long commentId) {
        new RestTemplate().delete(IOUrl + "/likes/user/{userId}/comment/{commentId}", userId, commentId);
    }

    public Post getPostByTitle(String title) {
        return new RestTemplate().getForObject(IOUrl + "/posts_cop/title/{title}", Post.class, title);
    }

    public Post savePost(Post post) {
        return new RestTemplate().postForObject(IOUrl + "/posts_cop", post, Post.class);
    }

    public void deletePostById(Long id) {
        new RestTemplate().delete(IOUrl + "/posts_cop/{id}", id);
    }

    public Post findPostById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/posts_cop/id/{id}", Post.class, id);
    }

    public List<Post> findAllPosts() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/posts_cop", Post[].class)));
    }

    public void putPost(Post post) {
        new RestTemplate().put(IOUrl + "/posts_cop", post);
    }

    public List<User> findAllUsers() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/users", User[].class)));
    }

    public User findUserByUsername(String username) {
        return new RestTemplate().getForObject(IOUrl + "/users/username/{username}", User.class, username);
    }

    public User findUserById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/users/{id}", User.class, id);
    }

    public User saveUser(User user) {
        return new RestTemplate().postForObject(IOUrl + "/users", user, User.class);
    }

    public void putUser(User user) {
        new RestTemplate().put(IOUrl + "/users", user);
    }

    public User getUserReferenceById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/users/{id}", User.class, id);
    }

    public Comment findCommentById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/comments/{id}", Comment.class, id);
    }

    public void saveComment(Comment comment) {
        new RestTemplate().postForObject(IOUrl + "/comments", comment, Comment.class);
    }

    public void putComment(Comment comment) {
        new RestTemplate().put(IOUrl + "/comments", comment);
    }

    public List<Comment> findAllComments() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/comments", Comment[].class)));
    }

    public void deleteCommentById(Long id) {
        new RestTemplate().delete(IOUrl + "/comments/{id}", id);
    }
}
