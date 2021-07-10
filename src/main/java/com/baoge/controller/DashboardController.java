package com.baoge.controller;

import cn.hutool.core.util.IdUtil;
import com.baoge.entity.CardImage;
import com.baoge.entity.Job;
import com.baoge.entity.Resume;
import com.baoge.entity.User;
import com.baoge.queryvo.Application;
import com.baoge.queryvo.ApplicationAndResume;
import com.baoge.service.*;
import com.baoge.utils.MsgResult;
import com.baoge.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;
    @Autowired
    UserService userService;
    @Autowired
    JobService jobService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    CardImageService cardImageService;
    @Autowired
    ApplicationService applicationService;

    @RequestMapping("/personal")
    public String personal() {
        return "front/personal";
    }

    @RequestMapping("/personal/allApplication")
    public String allApplication(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/allApplication";
    }

    @PostMapping("/personal/allApplicationAct/{id}")
    @ResponseBody
    public MsgResult allApplicationAct(@PathVariable(value = "id") Integer id, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Application> applications = dashboardService.getAllApplication(id);
        PageInfo<Application> info = new PageInfo<>(applications);
        return new MsgResult(0, "", applications, info.getTotal());
    }

    @RequestMapping("/personal/passApplication")
    public String passApplication(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/passApplication";
    }

    @PostMapping("/personal/passApplicationAct/{id}")
    @ResponseBody
    public MsgResult passApplicationAct(@PathVariable(value = "id") Integer id, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Application> applications = dashboardService.getPassApplication(id);
        PageInfo<Application> info = new PageInfo<>(applications);
        return new MsgResult(0, "", applications, info.getTotal());
    }

    @RequestMapping("/personal/checkingApplication")
    public String checkingApplication(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/checkingApplication";
    }

    @PostMapping("/personal/checkingApplicationAct/{id}")
    @ResponseBody
    public MsgResult checkingApplication(@PathVariable(value = "id") Integer id, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Application> applications = dashboardService.getCheckingApplication(id);
        PageInfo<Application> info = new PageInfo<>(applications);
        return new MsgResult(0, "", applications, info.getTotal());
    }

    @RequestMapping("/personal/nopassApplication")
    public String nopassApplication(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/nopassApplication";
    }

    @PostMapping("/personal/nopassApplicationAct/{id}")
    @ResponseBody
    public MsgResult nopassApplicationAct(@PathVariable(value = "id") Integer id, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Application> applications = dashboardService.getnoPassApplication(id);
        PageInfo<Application> info = new PageInfo<>(applications);
        return new MsgResult(0, "", applications, info.getTotal());
    }

    @RequestMapping("/personal/editpassword")
    public String editpassword(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/editpassword";
    }

    @PostMapping("/personal/editpasswordAct/{id}")
    @ResponseBody
    public MsgResult editpasswordAct(@PathVariable(value = "id") Integer id, String oldpassword, String password) {
        User user = userService.checkUserPasswordById(oldpassword, id);
        if (user != null) {
            int i = userService.editUserpassword(password, id);
            return MsgResult.build(0, "修改成功");
        } else {
            return MsgResult.build(1, "初始密码错误");
        }
    }

    @RequestMapping("/personal/editUserinfo")
    public String editUserinfo(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "front/editUserinfo";
    }

    @PostMapping("/personal/editUserinfoAct")
    @ResponseBody
    public MsgResult editUserinfoAct(User user) {
        user.setStatus(null);
        /*System.out.println(user);*/
        try {
            Integer retNum = userService.editUser(user);
            if (retNum == 1) {

                return MsgResult.build(1, "修改成功");
            } else {
                return MsgResult.build(0, "修改失败，请重新提交");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.build(0, "修改失败，请重新提交");
        }
    }

    @RequestMapping("/personal/allJob")
    public String allJob(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/allJob";
    }

    @PostMapping("/personal/allJobAct/{id}")
    @ResponseBody
    public MsgResult allJobAct(@PathVariable(value = "id") Integer userid, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Job> jobs = jobService.getallJob(userid);
        PageInfo<Job> info = new PageInfo<>(jobs);
        return new MsgResult(0, "", jobs, info.getTotal());
    }

    @RequestMapping("/personal/passJob")
    public String passallJob(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/passJob";
    }

    @PostMapping("/personal/passJobAct/{id}")
    @ResponseBody
    public MsgResult passJobAct(@PathVariable(value = "id") Integer userid, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Job> jobs = jobService.getJobByUserAndstatus(userid, 0);
        PageInfo<Job> info = new PageInfo<>(jobs);
        return new MsgResult(0, "", jobs, info.getTotal());
    }

    @RequestMapping("/personal/checkingJob")
    public String checkingJob(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/checkingJob";
    }

    @PostMapping("/personal/checkingJobAct/{id}")
    @ResponseBody
    public MsgResult checkingJobAct(@PathVariable(value = "id") Integer userid, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Job> jobs = jobService.getJobByUserAndstatus(userid, 1);
        PageInfo<Job> info = new PageInfo<>(jobs);
        return new MsgResult(0, "", jobs, info.getTotal());
    }

    @RequestMapping("/personal/nopassJob")
    public String nopassJob(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/nopassJob";
    }

    @PostMapping("/personal/nopassJobAct/{id}")
    @ResponseBody
    public MsgResult nopassJobAct(@PathVariable(value = "id") Integer userid, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Job> jobs = jobService.getJobByUserAndstatus(userid, 2);
        PageInfo<Job> info = new PageInfo<>(jobs);
        return new MsgResult(0, "", jobs, info.getTotal());
    }

    @RequestMapping("/personal/resume")
    public String resume(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        Resume resume = resumeService.getResumeByUserid(id);
        if (resume != null) {
            model.addAttribute("resume", resume);
            return "front/editresume";
        } else {
            return "front/addresume";
        }
    }
    @RequestMapping("/personal/showResume/{userId}")
    public String showResume(@PathVariable("userId") Integer userId, Model model) {
        Resume resume = resumeService.getResumeByUserid(userId);
        User user = userService.getUserById(userId);
        model.addAttribute("resume",resume);
        model.addAttribute("user",user);
        return "front/showResume";
    }
    @RequestMapping("/personal/setResumeSucessStatus")
    @ResponseBody
    public MsgResult statusSucessAct(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
        if (status == 0) {
            return MsgResult.build(0, "已经通过审核了！");
        } else{
            Integer i=applicationService.setResumeStatus(id,0);
            return MsgResult.build(1,"通过审核");
        }
    }
    @RequestMapping("/personal/setResumeFailStatus")
    @ResponseBody
    public MsgResult statusfailAct(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
        if (status == 2) {
            return MsgResult.build(0, "已经没有通过审核！");
        } else{
            Integer i=applicationService.setResumeStatus(id,2);
            return MsgResult.build(1,"审核不通过");
        }
    }
    @PostMapping("/personal/editResumeAct")
    @ResponseBody
    public MsgResult editResumeAct(Resume resume) {
        Integer retNum = resumeService.editResume(resume);
        if (retNum > 0) {
            return MsgResult.build(0, "简历修改成功");
        } else {
            return MsgResult.build(1, "简历修改失败");
        }
    }

    @PostMapping("/personal/addResumeAct")
    @ResponseBody
    public MsgResult addResumeAct(Resume resume, @RequestParam("userid") Integer userid) {

        Integer retNum = resumeService.addResume(resume, userid);
        if (retNum > 0) {
            return MsgResult.build(0, "简历增加成功");
        } else {
            return MsgResult.build(1, "简历增加失败");
        }
    }

    @RequestMapping("/personal/releaseJob")
    public String releaseJob(@RequestParam("id") Integer id, Model model, HttpSession session) {
        User frontUser = UserUtils.getSubjectFontUser(session);
        if (frontUser != null) {
            CardImage cardImage1 = cardImageService.getCardImageByUserid(frontUser.getId());
            session.setAttribute("cardImage", cardImage1);
        }
        model.addAttribute("id", id);
        return "front/releaseJob";
    }

    @PostMapping("/personal/releaseJobAct")
    @ResponseBody
    public MsgResult releaseJobAct(Job job, @RequestParam("user_id") Integer user_id, HttpSession session) {
        User frontUser = UserUtils.getSubjectFontUser(session);
        CardImage cardImage = cardImageService.getCardImageByUserid(frontUser.getId());
        int status = cardImage.getStatus();
        if (status == 0) {
            Integer retNum = jobService.releaseJob(job, user_id);
            if (retNum > 0) {
                return MsgResult.build(0, "增加成功");
            } else {
                return MsgResult.build(1, "增加失败");
            }
        } else if (status == 1) {
            return MsgResult.build(1, "请等待管理员审核商家证明");
        } else {
            return MsgResult.build(1, "你的商家认证未通过，请到个人中心重新提交！");
        }
    }

    @RequestMapping("/personal/checkStudentResume")
    public String checkApplication(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "front/checkStudentResume";
    }

    @PostMapping("/personal/checkStudentResumeAct/{id}")
    @ResponseBody
    public MsgResult checkStudentResumeAct(@PathVariable(value = "id") Integer recruiterId, Model model, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<ApplicationAndResume> applicationAndResumes = applicationService.getApplicationAndResume(recruiterId);
        PageInfo<ApplicationAndResume> info = new PageInfo<>(applicationAndResumes);
        return new MsgResult(0, "", applicationAndResumes, info.getTotal());
    }
    @RequestMapping("/personal/studentCardImages")
    public String studentCardImages(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        CardImage cardImage = cardImageService.getCardImageByUserid(id);
        if (cardImage != null) {
            model.addAttribute("cardImage", cardImage);
            return "front/editCardImages";
        } else {
            return "front/addCardImages";
        }
    }

    @PostMapping("/personal/upload")
    @ResponseBody
    public Map upload(@RequestParam("file") MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        for (MultipartFile file : files) {
            //获取文件名以及后缀名
            String fileName1 = file.getOriginalFilename();
            String fileName = IdUtil.simpleUUID() + fileName1.substring(fileName1.lastIndexOf("."), fileName1.length());
            /*System.out.println(fileName);*/
            //获取jar包所在目录
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
            String dirPath = jarF.getParentFile().toString() + "/upload/";

            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                //将文件写入磁盘
                file.transferTo(new File(dirPath + fileName));
                /*   System.out.println(dirPath + fileName);*/
                //文件上传成功返回状态信息
                map.put("code", 0);
                map.put("imagesUrl", "/upload/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                //上传失败，返回失败信息
                map.put("code", 1);
            }
            //携带上传状态信息回调到文件上传页面
            //携带上传状态信息回调到文件上传页面

            /* return MsgResult(0,"",data);*/
        }
        return map;
    }

    @PostMapping("/personal/addCardImagesAct")
    @ResponseBody
    public MsgResult addCardImagesAct(CardImage cardImage) {
        Integer retNum = cardImageService.addCardImage(cardImage);
        if (retNum > 0) {
            return MsgResult.build(0, "增加成功");
        } else {
            return MsgResult.build(1, "增加失败");
        }
    }

    @PostMapping("/personal/editCardImagesAct")
    @ResponseBody
    public MsgResult editCardImagesAct(CardImage cardImage) {
        Integer retNum = cardImageService.editCardImage(cardImage);
        if (retNum > 0) {
            return MsgResult.build(0, "增加成功");
        } else {
            return MsgResult.build(1, "增加失败");
        }
    }

    @RequestMapping("/personal/storeCardImages")
    public String storeCardImages(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        CardImage cardImage = cardImageService.getCardImageByUserid(id);
        if (cardImage != null) {
            model.addAttribute("cardImage", cardImage);
            return "front/editStoreCardImages";
        } else {
            return "front/addStoreCardImages";
        }
    }


}
