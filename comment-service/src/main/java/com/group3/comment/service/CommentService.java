package com.group3.comment.service;

import com.group3.comment.domain.Comment;
import com.group3.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAll(){
        return commentRepository.findAll();
    }
    public Comment save(Comment comment){
       return commentRepository.save(comment);
    }
    public void updateComment(Comment comment){
        commentRepository.save(comment);
    }
    public void deletePost(Long commentId){
        commentRepository.deleteById(commentId);
    }
    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).get();
    }

}
