package top.twhuang.breakBlog.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @RequestMapping(value = "/index")
    public ModelAndView demo(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index");
        return mv;
    }
}
