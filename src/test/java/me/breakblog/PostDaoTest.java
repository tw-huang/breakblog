package me.breakblog;

import me.breakblog.dao.PostDao;
import me.breakblog.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostDaoTest {
    @Autowired
    private PostDao postDao;

    @Test
    void findAllTest() {
        List<Post> all = postDao.findAll();
        for (Post post:all){
            System.out.println(post);
        }
    }
}
