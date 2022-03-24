package com.blog.userservice.VO;

import com.blog.userservice.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    public User user;
    public List<Post> post;
}
