package top.twhuang.vo;

import lombok.Data;
import top.twhuang.entity.Post;

@Data
public class PostVO extends Post {

    private Integer prevPostId;

    private String prevPostTitle;

    private Integer nextPostId;

    private String nextPostTitle;

}
