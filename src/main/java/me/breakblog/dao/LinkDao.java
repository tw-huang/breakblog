package me.breakblog.dao;

import me.breakblog.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LinkDao {

    List<Link> findAll();

    Link findById(int id);

    int addLink(Link link);

    int updateLink(Link link);

    int deleteLink(Integer id);
}
