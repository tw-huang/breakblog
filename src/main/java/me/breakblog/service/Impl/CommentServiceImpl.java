package me.breakblog.service.Impl;

import com.github.pagehelper.PageHelper;
import me.breakblog.dao.CommentDao;
import me.breakblog.entity.Comment;
import me.breakblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> findAllByPostId(int id,int page,int size) {
        PageHelper.startPage(page, size);
        return commentDao.findAllByPostId(id);
    }

    @Override
    public List<Comment> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return commentDao.findAll();
    }

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public int deleteComment(int id) {
        return commentDao.deleteComment(id);
    }

    @Override
    public int updateComment(int id, int reviewed) {
        return commentDao.updateComment(id, reviewed);
    }
}
