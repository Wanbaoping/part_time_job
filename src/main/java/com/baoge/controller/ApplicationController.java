package com.baoge.controller;

import com.baoge.entity.CardImage;
import com.baoge.entity.User;
import com.baoge.queryvo.Application;
import com.baoge.service.ApplicationService;
import com.baoge.service.CardImageService;
import com.baoge.utils.MsgResult;
import com.baoge.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    CardImageService cardImageService;
    @PostMapping("/applyPosition")
    @ResponseBody
    public MsgResult applyPosition(Application application, HttpSession session) {
        User frontUser = UserUtils.getSubjectFontUser(session);
        CardImage cardImage = cardImageService.getCardImageByUserid(frontUser.getId());
        int status= cardImage.getStatus();
        if(status==0){
        application.setUserId(frontUser.getId());
        Integer RetNum = applicationService.checkByUerIdAndJobIdAndRecruiterId(application.getUserId(), application.getJobId(), application.getRecruiterId());
        if (RetNum >0) {
            return MsgResult.build(2, "已经投递过，请前往个人中心查看");
        }else {
           Integer num= applicationService.addApplication(application);
            return MsgResult.build(0, "投递成功");
        }
        }else if (status==1){

                return MsgResult.build(1, "请等待管理员审核通过");

        }else {
            return MsgResult.build(1, "你的学生认证未通过，请到个人中心重新提交！");
        }
    }
    @PostMapping("/deletePosition/{id}")
    @ResponseBody
    public MsgResult deletePosition(@PathVariable int id,int status) {
        if (status==1) {
                Integer RetNum = applicationService.deletePositionById(id);
                return MsgResult.build(0, "取消成功");
        }else if (status==0){
            return MsgResult.build(1, "申请成功,无法取消");
        }else {
            return MsgResult.build(1, "已经拒绝,无法取消");
        }
    }


}
