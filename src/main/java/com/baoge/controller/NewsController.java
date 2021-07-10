package com.baoge.controller;

import com.baoge.entity.News;
import com.baoge.entity.User;
import com.baoge.service.NewsService;
import com.baoge.utils.MsgResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping("/news")
    public String tonews(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 2);
        List<News> news = newsService.getallNews();
        PageInfo<News> pageInfo = new PageInfo<>(news);
        model.addAttribute("pageInfo", pageInfo);
        return "front/news";
    }

    @RequestMapping("/abouts")
    public String abouts() {
        return "front/abouts";
    }

    @RequestMapping("/admin/news/toNewsManager")
    public String toNewsManager() {
        return "admin/NewsManager";
    }

    @RequestMapping("/admin/news/listNewsAct")
    @ResponseBody
    public MsgResult listNewsAct(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<News> news = newsService.listNews();
        PageInfo<News> info = new PageInfo<>(news);
        return new MsgResult(0, "", news, info.getTotal());
    }

    @RequestMapping("/admin/news/addNews")
    public String addNews() {

        return "admin/addNews";
    }
    @RequestMapping("/admin/user/addNewsAct")
    @ResponseBody
    public MsgResult addNewsAct(News news){
        Integer retNum = newsService.addNews(news);
        if (retNum > 0) {
            return MsgResult.build(1, "添加成功");
        } else {
            return MsgResult.build(0, "添加失败");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/admin/news/deleteNewAct", method = RequestMethod.POST)
    public MsgResult deleteNewAct(@RequestParam(name = "id") int id) {

        int retNum = newsService.deleteNew(id);
        if (retNum == 1) {
            return MsgResult.build(1, "删除成功");
        } else {
            return MsgResult.build(0, "删除失败，请重新操作");
        }
    }
    /*批量删除*/
    @ResponseBody
    @RequestMapping(value = "/admin/news/deleteNewsAct/{del_idstr}", method = RequestMethod.POST)
    public MsgResult deleteNewsAct(@PathVariable(value = "del_idstr") String ids) {

        if (ids.contains("-")) {
            int retNum = newsService.deleteNews(ids);
            /*System.out.println("retnum:"+retNum);*/
            if (retNum > 0) {
                return MsgResult.build(1, "删除成功");
            } else {
                return MsgResult.build(0, "删除失败，请重新操作");
            }
        } else {
            Integer id = Integer.parseInt(ids);
            int retNum = newsService.deleteNew(id);
            if (retNum > 0) {
                return MsgResult.build(1, "删除成功");
            } else {
                return MsgResult.build(0, "删除失败，请重新操作");
            }
        }

    }
    @RequestMapping("/admin/news/editNews/{id}")
    public String editNews(@PathVariable("id") Integer id, Model model){
       News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "admin/editNews";
    }

    @RequestMapping(value = "/admin/news/editNewsAct", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult editNewsAct(News news) {
        /*System.out.println("editUserAct:" + user);*/
        int retNum = newsService.editNews(news);
        if (retNum > 0) {
            return MsgResult.build(1, "修改成功");
        } else {
            return MsgResult.build(0, "修改失败，请重新提交");
        }

    }
}
