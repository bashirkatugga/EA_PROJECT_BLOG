package com.group3.post.valueObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long commentId;
    private String text;
    private Date date;
    private Long postId;
}
