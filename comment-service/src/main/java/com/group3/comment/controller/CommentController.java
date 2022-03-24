package com.group3.comment.controller;

import com.group3.comment.domain.Comment;
import com.group3.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @GetMapping
    public List<Comment> getAll(){
        return commentService.getAll();
    }
    @GetMapping("/{commentId}")
    public Comment getById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }
    @PostMapping
    public RedirectView save(@Valid @RequestBody Comment comment){
        commentService.save(comment);
        return new RedirectView("/comments/" + comment.getCommentId());
    }
    @PutMapping("/{commentId}")
    public void updateComment(@Valid @RequestBody Comment comment, @PathVariable Long commentId){
        commentService.updateComment(comment);
    }
    @DeleteMapping("/{commentId}")
    public void deletePost(@PathVariable Long commentId){
        commentService.deletePost(commentId);
    }
}
