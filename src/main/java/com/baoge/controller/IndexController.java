package com.baoge.controller;

import com.baoge.entity.*;
import com.baoge.service.*;
import com.baoge.utils.MsgResult;
import com.baoge.utils.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    LogInfoService logInfoService;
    @Autowired
    JobService jobService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    CardImageService cardImageService;

    @RequestMapping("/")
    public String test(Model model, HttpSession session) {
        List<Job> jobs = jobService.getJobsBynews();
        model.addAttribute("jobs", jobs);

        return "front/index";
    }

    @RequestMapping("/register")
    public String toregister() {
        return "front/register";
    }

    @PostMapping("/registerAct")
    @ResponseBody
    public MsgResult registerAct(User user, @RequestParam("roleId") long roleId, HttpSession session) {
        user.setStatus(0);
        int i = userService.searchUserByUserName(user.getUsername());
        if (i == 1) {
            return MsgResult.build(0, "用户名存在");
        } else {
            int n = userService.addUser(user);
            userService.addUserRole(user.getId(), roleId);
            return MsgResult.build(1, "注册成功");
        }
    }

    @RequestMapping("/login")
    public String tologin() {
        return "front/login";
    }

    @RequestMapping("/loginAct")
    @ResponseBody
    public MsgResult loginAct(@RequestParam("username") String username, @RequestParam("password") String password,
                              HttpSession session, HttpServletRequest request) {
        String ipAddress = NetworkUtil.getIpAddress(request);
        User frontuser = userService.checkUser(username, password);
        if (frontuser == null) {
            return MsgResult.build(0, "账号不存在或密码错误");
        }
        if (frontuser.getStatus() == 1) {
            return MsgResult.build(0, "帐号已禁用");
        }
        if (frontuser != null) {
            session.setAttribute("frontuser", frontuser);
            List<Integer> roleids = userService.getRoleidsById(frontuser.getId());
            session.setAttribute("roleids", roleids);
            Resume resume = resumeService.getResumeByUserid(frontuser.getId());
            session.setAttribute("resume", resume);
            CardImage cardImage = cardImageService.getCardImageByUserid(frontuser.getId());
            session.setAttribute("cardImage", cardImage);
            LogInfo logInfo = new LogInfo(username, ipAddress, 0, "登入成功", new Date());
            logInfoService.addLogInfo(logInfo);
            return MsgResult.build(1, "登入成功");
        } else {
            LogInfo logInfo = new LogInfo(username, ipAddress, 1, "账号不存在或密码错误", new Date());
            logInfoService.addLogInfo(logInfo);
            return MsgResult.build(0, "账号不存在或密码错误");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("frontuser");
        return "redirect:/";
    }

    @GetMapping("/logoutToLogin")
    public String logoutToLogin(HttpSession session) {
        session.removeAttribute("frontuser");
        session.removeAttribute("roleids");
        session.removeAttribute("resume");
        session.removeAttribute("cardImage");
        return "redirect:/login";
    }
}
