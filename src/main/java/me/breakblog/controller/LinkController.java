package me.breakblog.controller;

import me.breakblog.entity.Link;
import me.breakblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class LinkController {
    private LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping(value = "/link/new", method = RequestMethod.GET)
    public String newLinkGet() {
        return "admin/newLink";
    }

    @RequestMapping(value = "/link/new", method = RequestMethod.POST)
    public String newLinkPost(Link link, Model model) {
        boolean save = linkService.save(link);
        if (save) {
            return "redirect:/admin/link/manage";
        }
        model.addAttribute("msg", "New Link Error！");
        return "forward:/admin/link/manage";
    }

    @RequestMapping(value = "/link/edit/{id}", method = RequestMethod.GET)
    public String editLinkGet(@PathVariable("id") int id, Model model) {
        Link link = linkService.getById(id);
        model.addAttribute("link", link);
        return "admin/editLink";
    }

    @RequestMapping(value = "/link/edit/{id}", method = RequestMethod.POST)
    public String editLinkPost(@PathVariable("id") int id, Link link, Model model) {
        link.setId(id);
        boolean update = linkService.updateById(link);
        if (update) {
            return "redirect:/admin/link/manage";
        }
        model.addAttribute("msg", "Edit Link Error！");
        return "forward:/admin/link/manage";
    }

    @RequestMapping(value = "/link/delete/{id}", method = RequestMethod.POST)
    public String deleteLinkPost(@PathVariable("id") int id, Model model) {
        boolean remove = linkService.removeById(id);
        if (remove) {
            return "redirect:/admin/link/manage";
        }
        model.addAttribute("msg", "Delete Error！");
        return "forward:/admin/link/manage";
    }

    @RequestMapping(value = "/link/manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String manageLink(Model model) {
        List<Link> links = linkService.list();
        model.addAttribute("links", links);
        return "admin/manageLink";
    }
}
