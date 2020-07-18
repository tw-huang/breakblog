package me.breakblog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String blogTitle;

    private String blogSubtitle;

    private String name;

    private String about;

    private String email;
}
