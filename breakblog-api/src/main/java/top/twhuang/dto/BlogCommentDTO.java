package top.twhuang.dto;

import lombok.Data;

@Data
public class BlogCommentDTO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer postId;
}
