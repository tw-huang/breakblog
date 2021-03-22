package me.breakblog.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    private Integer id;

    private String name;

    private String path;

    private String icon;

    private List<MenuVo> children;
}
