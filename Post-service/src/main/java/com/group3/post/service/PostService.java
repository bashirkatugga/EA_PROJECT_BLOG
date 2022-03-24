package com.group3.post.service;

import com.group3.post.domain.Post;
import com.group3.post.repository.PostRepository;
import com.group3.post.valueObject.Comment;
import com.group3.post.valueObject.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Post save(Post post) {
        return postRepository.save(post);
    }
    public List<Post> getAll(){
        return postRepository.findAll();
    }
    public void updatePost(Post post){
        postRepository.save(post);
    }
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    public ResponseTemplateVO getPostWithComments(Long postId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Post post = postRepository.findById(postId).get();
        ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(
                "http://localhost:9001/comments/",
                HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Comment>>(){});

        List<Comment> comments = responseEntity.getBody()
                .stream().filter(item->item.getPostId()==postId)
                .collect(Collectors.toList());

        vo.setComment(comments);
        vo.setPost(post);
        return vo;
    }
}
