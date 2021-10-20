package top.twhuang.dto;

import lombok.Data;

@Data
public class BlogHomeDTO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String keyword;

    private Integer categoryId;
}
