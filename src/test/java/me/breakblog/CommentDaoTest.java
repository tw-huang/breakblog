package me.breakblog;

import me.breakblog.dao.CommentDao;
import me.breakblog.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    void findByPostIdTest() {
        List<Comment> allByPostId = commentDao.findAllByPostId(15);
        System.out.println(allByPostId.size());
        for(Comment comment:allByPostId){
            System.out.println(comment.getComment());
        }
    }

    @Test
    void addComment() {
        Comment comment = new Comment();
        comment.setAuthor("hao");
        comment.setEmail("tw.huang@foxmail.com");
        comment.setSite("www.baidu.com");
        comment.setBody("nihao");
        comment.setFromAdmin(0);
        comment.setReviewed(0);
        comment.setTimestamp(new Timestamp(new java.util.Date().getTime()));
        comment.setPostId(15);
        int i = commentDao.addComment(comment);
        System.out.println(i);
    }
}