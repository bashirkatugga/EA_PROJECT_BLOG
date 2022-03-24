package com.blog.userservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long postId;
    private String description;
    private Date date;
    private String title;
    private Long userId;
}
