package com.group3.post.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long postId;
    @Lob
    @NotBlank(message = "Description may not be empty")
    private String description;
    @NotNull(message = "Date may not be empty")
    private Date date;
    @NotBlank(message = "Title may not be empty")
    private String title;
    private Long userId;

}
