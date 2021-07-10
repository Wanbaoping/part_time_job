package com.baoge.controller;

import com.baoge.entity.CardImage;
import com.baoge.entity.Job;
import com.baoge.entity.Resume;
import com.baoge.entity.User;
import com.baoge.service.ApplicationService;
import com.baoge.service.CardImageService;
import com.baoge.service.JobService;
import com.baoge.service.ResumeService;
import com.baoge.utils.MsgResult;
import com.baoge.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    CardImageService cardImageService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    ApplicationService applicationService;

    @RequestMapping("/ptjob")
    public String toptjobs(@RequestParam(value = "place", defaultValue = "all") String place,
                           @RequestParam(value = "type", defaultValue = "all") String type,
                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           Model model, HttpSession session) {
        PageHelper.startPage(pageNum, 3);
        List<Job> jobs = jobService.getJobPlaceAndType(place, type);
        PageInfo<Job> pageInfo = new PageInfo<>(jobs);
        model.addAttribute("place", place);
        model.addAttribute("type", type);
        model.addAttribute("pageInfo", pageInfo);
        User frontUser = UserUtils.getSubjectFontUser(session);
        if (frontUser != null) {
            CardImage cardImage1 = cardImageService.getCardImageByUserid(frontUser.getId());
            session.setAttribute("cardImage", cardImage1);
            Resume resume = resumeService.getResumeByUserid(frontUser.getId());
            session.setAttribute("resume", resume);
        }
        return "front/ptjob";
    }

    @RequestMapping("/pijob/{id}")
    public String topijob(@PathVariable int id, Model model, HttpSession session) {
        User frontUser = UserUtils.getSubjectFontUser(session);
        if (frontUser != null) {
            CardImage cardImage1 = cardImageService.getCardImageByUserid(frontUser.getId());
            session.setAttribute("cardImage", cardImage1);
            Resume resume = resumeService.getResumeByUserid(frontUser.getId());
            session.setAttribute("resume", resume);
        }
        Job job = jobService.getJobInfoByid(id);
        model.addAttribute("job", job);
        return "front/pijob";
    }

    @RequestMapping("/dashboard/personal/editJob/{id}")
    public String editJob(@PathVariable("id") Integer id, Model model) {
        Job job = jobService.getJobInfoByid2(id);
        model.addAttribute("job", job);
        return "front/editJob";
    }

    @RequestMapping("/dashboard/personal/editJobAct")
    @ResponseBody
    public MsgResult editJobAct(Job job, Model model) {
        Integer RetNum = jobService.editJob(job);
        if (RetNum == 1) {
            return MsgResult.build(0, "修改成功,管理员将重新审核");
        } else {
            return MsgResult.build(1, "修改失败");
        }
    }

    /*招聘方取消发布*/
    @RequestMapping("/dashboard/personal/deleteJob/{id}")
    @ResponseBody
    public MsgResult deleteJob(@PathVariable int id, int status) {
  /*      if (status==1) {
            Integer RetNum = jobService.deleteJobById(id);
            return MsgResult.build(0, "取消成功");
        }else {
            return MsgResult.build(1, "无法取消");
        }*/
        Integer RetNum = jobService.setStatus(id, 3);
        if (RetNum > 0) {
            return MsgResult.build(0, "取消成功");
        } else {
            return MsgResult.build(1, "取消失败");
        }
    }

    @RequestMapping("/admin/job/toJobManager")
    public String toJobManager() {
        return "admin/JobManager";
    }

    @RequestMapping("/admin/job/allJobAct")
    @ResponseBody
    public MsgResult allJobAct(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Job> jobs = jobService.allJobAct();
        PageInfo<Job> info = new PageInfo<>(jobs);
        return new MsgResult(0, "", jobs, info.getTotal());
    }

    @RequestMapping("/admin/job/searchJob")
    @ResponseBody
    public MsgResult searchJob(@RequestParam(name = "uname") String uname, @RequestParam(name = "name") String name, @RequestParam(name = "status") Integer status, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Job> jobs = jobService.searchJob(uname, name, status);
        PageInfo<Job> info = new PageInfo<>(jobs);
        return new MsgResult(0, "", jobs, info.getTotal());
    }

    @RequestMapping("/admin/job/statusSucessAct")
    @ResponseBody
    public MsgResult statusSucessAct(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {

        if (status == 0) {
            return MsgResult.build(0, "已经通过审核了！");
        } else {
            Integer i = jobService.setStatus(id, 0);
            return MsgResult.build(1, "通过审核");
        }
    }

    @RequestMapping("/admin/job/statusfailAct")
    @ResponseBody
    public MsgResult statusfailAct(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        if (status == 2) {
            return MsgResult.build(0, "已经没有通过审核！");
        } else {
            Integer i = jobService.setStatus(id, 2);
            return MsgResult.build(1, "审核不通过");
        }
    }

    @RequestMapping("/admin/job/deleteJobAct")
    @ResponseBody
    public MsgResult deleteJobAct(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        Integer applicationId = applicationService.getApplicationIdByJobId(id);
        if (applicationId!=null) {
            applicationService.deletePositionById(applicationId);
        }
        Integer retNum = jobService.deleteJobById(id);
        if (retNum > 0) {
            return MsgResult.build(1, "删除成功");
        } else {
            return MsgResult.build(1, "删除失败");
        }
    }
}
