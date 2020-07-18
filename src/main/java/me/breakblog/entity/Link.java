package me.breakblog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Link implements Serializable {

    private Integer id;

    private String name;

    private String url;

}
