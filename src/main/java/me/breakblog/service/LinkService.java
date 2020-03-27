package me.breakblog.service;

import me.breakblog.entity.Link;

import java.util.List;

public interface LinkService {

    List<Link> findAll();

    Link findById(int id);

    int addLink(Link link);

    int updateLink(Link link);

    int deleteLink(int id);
}
