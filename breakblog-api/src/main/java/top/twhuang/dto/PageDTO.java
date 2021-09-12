package top.twhuang.dto;

import lombok.Data;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 2:02
 * @Description: 查询参数对象
 */
@Data
public class PageDTO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String keyword;

}
