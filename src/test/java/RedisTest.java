import me.breakblog.BreakblogSpringbootApplication;
import me.breakblog.service.*;
import me.breakblog.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BreakblogSpringbootApplication.class})
public class RedisTest {

    @Autowired
    private LinkService linkService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {

    }
}
