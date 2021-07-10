package com.baoge.controller.admin;

import com.baoge.queryvo.CardImageVo;
import com.baoge.service.CardImageService;
import com.baoge.utils.MsgResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/userIdCard")
public class CardImageController {
    @Autowired
    CardImageService cardImageService;

    @RequestMapping("/toUserIdCardManager")
    public String toUserIdCardManager() {
        return "admin/userIdCardManager";
    }

    @RequestMapping("/listUserIdCardAct")
    @ResponseBody
    public MsgResult listUserIdCardAct(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<CardImageVo> UserIdCards = cardImageService.listUserIdCard();
        PageInfo<CardImageVo> info = new PageInfo<>(UserIdCards);
        return new MsgResult(0, "", UserIdCards, info.getTotal());
    }

    @RequestMapping("/statusSucessAct")
    @ResponseBody
    public MsgResult statusSucessAct(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        if (status == 0) {
            return MsgResult.build(0, "已经通过审核了！");
        } else {
            Integer i = cardImageService.setStatus(id, 0);
            return MsgResult.build(1, "通过审核");
        }
    }

    @RequestMapping("/statusfailAct")
    @ResponseBody
    public MsgResult statusfailAct(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        if (status == 2) {
            return MsgResult.build(0, "已经没有通过审核！");
        } else {
            Integer i = cardImageService.setStatus(id, 2);
            return MsgResult.build(1, "审核不通过");
        }
    }

    @RequestMapping("/searchUserIdCard")
    @ResponseBody
    public MsgResult searchUserIdCard(@RequestParam(name = "name") String name, @RequestParam(name = "roleId") Integer roleId, @RequestParam(name = "status") Integer status, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<CardImageVo> UserIdCards = cardImageService.searchUserIdCard(name, roleId, status);
        PageInfo<CardImageVo> info = new PageInfo<>(UserIdCards);
        return new MsgResult(0, "", UserIdCards, info.getTotal());
    }
}
