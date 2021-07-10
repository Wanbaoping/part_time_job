package com.baoge.controller.admin;

import com.baoge.entity.LogInfo;
import com.baoge.service.LogInfoService;
import com.baoge.utils.MsgResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/logInfo")
public class LogInfoController {

    @Autowired
    LogInfoService logInfoService;

    @RequestMapping("/toLogInfoManager")
    public String toLogInfoManager(){

        return "admin/logInfoManager";
    }

    @RequestMapping("/listLogInfoAct")
    @ResponseBody
    public MsgResult listLogInfoAct(int page, int limit){
         PageHelper.startPage(page, limit);
        List<LogInfo> logInfos=logInfoService.listLogInfo();
        PageInfo<LogInfo> info = new PageInfo<>(logInfos);
        return new MsgResult(0,"",logInfos, info.getTotal());
    }

    @RequestMapping("/searchLogInfo")
    @ResponseBody
    public MsgResult searchLogInfo(@RequestParam(name = "loginName") String loginName,
                                   @RequestParam(name = "loginStatus") String  loginStatus,
                                   @RequestParam(name = "start_time") String start_time,
                                   @RequestParam(name = "end_time")String end_time, int page, int limit){


        PageHelper.startPage(page, limit);
        List<LogInfo> logInfos = logInfoService.searchLogInfo(loginName,loginStatus,start_time,end_time);
        PageInfo<LogInfo> info = new PageInfo<>(logInfos);
        return new MsgResult(0, "", logInfos, info.getTotal());

    }
    @RequestMapping(value = "/deleteLogInfosAct/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public MsgResult deleteLogInfosAct(@PathVariable("ids") String ids){

        int retNum = logInfoService.deleteLogInfos(ids);
        if (retNum > 0) {
            return MsgResult.build(1, "删除成功");
        } else {
            return MsgResult.build(0, "删除失败，请重新操作");
        }
    }

}
