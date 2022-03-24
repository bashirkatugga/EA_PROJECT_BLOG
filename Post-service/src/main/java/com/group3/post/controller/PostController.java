package com.group3.post.controller;

import com.group3.post.domain.Post;
import com.group3.post.service.PostService;
import com.group3.post.valueObject.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAll(){
        return postService.getAll();
    }

   @PostMapping
    public RedirectView savePost(@Valid @RequestBody Post post, BindingResult result){
        if(result.hasErrors()){
            System.out.println("May not be empty");
        }
        postService.save(post);
        return new RedirectView("/posts/" + post.getPostId());
    }
    @PutMapping("/{postId}")
    public void updatePost(@Valid @RequestBody Post post, @PathVariable Long postId){
        postService.updatePost(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

    @GetMapping("/{postId}")
    public ResponseTemplateVO getPostWithComments(@PathVariable Long postId){
        return postService.getPostWithComments(postId);
    }

}
