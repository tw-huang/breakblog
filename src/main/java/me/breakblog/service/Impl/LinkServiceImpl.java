package me.breakblog.service.Impl;

import me.breakblog.dao.LinkDao;
import me.breakblog.entity.Link;
import me.breakblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    private LinkDao linkDao;

    @Autowired
    public LinkServiceImpl(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    @Override
    public List<Link> findAll() {
        return linkDao.findAll();
    }

    @Override
    public Link findById(int id) {
        return linkDao.findById(id);
    }

    @Override
    public int addLink(Link link) {
        return linkDao.addLink(link);
    }

    @Override
    public int updateLink(Link link) {
        return linkDao.updateLink(link);
    }

    @Override
    public int deleteLink(int id) {
        return linkDao.deleteLink(id);
    }
}
