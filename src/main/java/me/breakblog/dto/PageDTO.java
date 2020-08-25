package me.breakblog.dto;

import lombok.Data;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 2:02
 * @Description: TODO
 */
@Data
public class PageDTO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String keyword;

}
