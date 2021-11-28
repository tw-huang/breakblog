package top.twhuang.dto;

import lombok.Data;

@Data
public class BlogHomeDTO {

    private Integer pageNum;

    private Integer pageSize;

    private String keyword;

    private Integer categoryId;
}
