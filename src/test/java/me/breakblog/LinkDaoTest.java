package me.breakblog;

import me.breakblog.dao.LinkDao;
import me.breakblog.entity.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LinkDaoTest {
    @Autowired
    private LinkDao linkDao;

    @Test
    void findAllTest() {
        List<Link> all = linkDao.findAll();
        for(Link link:all){
            System.out.println(link);
        }
    }
}
