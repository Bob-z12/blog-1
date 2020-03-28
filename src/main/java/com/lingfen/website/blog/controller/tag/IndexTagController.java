package com.lingfen.website.blog.controller.tag;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfen.website.blog.bean.Tag;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.service.BlogService;
import com.lingfen.website.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexTagController {
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;
    @GetMapping("/tags/{tagId}")
    public String getBlogByTag(@PathVariable int tagId, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<PreviewBlog> previewBlogs=blogService.getPreviewBlogByTag(tagId);
        PageInfo<PreviewBlog> pageInfo = new PageInfo<>(previewBlogs);
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", tagId);
        return "/tags";
    }
}
