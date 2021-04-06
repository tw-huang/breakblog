package me.breakblog.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    private Integer id;

    private String name;

    private String path;

    private String icon;

    private List<MenuVO> children;
}
