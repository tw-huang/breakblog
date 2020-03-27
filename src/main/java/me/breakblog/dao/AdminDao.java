package me.breakblog.dao;

import me.breakblog.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminDao {

    Admin findAdmin();

    int updateAdmin(Admin admin);
}
