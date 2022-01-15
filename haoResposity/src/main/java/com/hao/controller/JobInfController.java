package com.hao.controller;


import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.JobInf;
import com.hao.service.impl.DeptInfServiceImpl;
import com.hao.service.impl.JobInfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  功能:
 *         对部门的增删改查
 *
 *  方法:
 *          get获取数据
 *          post添加数据
 *          put修改数据
 *          delete删除数据
 *
 * @author 蒋江龙
 * @since 2022-01-05
 */
@Controller
@ResponseBody
@RequestMapping("/jobInf")
public class JobInfController {

    @Autowired
    JobInfServiceImpl jobInfService;

    @GetMapping
    public PageResoponse<ArrayList<JobInf>> selectAllJoblnf(
            int page,
            int size,
            @RequestParam(required = false) String name
    ){
        /**
         * 如果有分页,请在这里接受页面数,和页面数据大小
         * @RequestParam("page") Integer page,
         * @RequestParam(value = "size",required = false) Integer size
         *
         * if(size == null) size = StaticData.pageSize;
         * '
         * 再传给服务层的方法,后再数据库获取总页面数和当前页数
         * 在传到PageRespopnse方法进行封装一次,最后进行返回对象
         */

        //通过判断name来有无条件查询
        return jobInfService.selectJoblnf(page,size,name);
    }

    @PostMapping
    public String addJoblnf(
            @RequestBody(required = false) JobInf jobInf
    ){
//        JobInf jobInf = new JobInf();
//        jobInf.setName(map.get("name").toString());
//        jobInf.setRemark(map.get("remark").toString());
        if(jobInf.getName() == null || jobInf.getRemark() == null) return "error";
        return jobInfService.addJobInf(jobInf) == 1 ? "success" : "error" ;
    }

    @PutMapping
    public String updateJoblnf(
            @RequestBody(required = false) JobInf jobInf
    ){
//        JobInf jobInf = new JobInf();
//        jobInf.setId((Integer) map.get("id"));
//        jobInf.setName(map.get("name").toString());
//        jobInf.setRemark(map.get("remark").toString());
        if(jobInf.getId() == null ||
                jobInf.getName() == null ||
                jobInf.getRemark() == null) return "error";
        return jobInfService.updateJobInf(jobInf) == 1 ? "success" : "error" ;
    }


    @DeleteMapping
    public String deleteJoblnf(
            @RequestBody ArrayList<Integer> arrayList
    ){
        int state = 0;
        for(Integer id : arrayList){
            if (jobInfService.deleteJobInf(id) != 1) {
                state++;
            }
        }
        return state == 0 ? "success" : "error" ;
    }



}

