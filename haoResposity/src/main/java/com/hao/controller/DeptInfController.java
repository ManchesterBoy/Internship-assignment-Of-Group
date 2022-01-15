package com.hao.controller;


import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.service.impl.DeptInfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 请按照这个模板来
 * 功能:
 *         对部门的增删改查
 *
 *  方法:
 *  *          get获取数据
 *  *          post添加数据
 *  *          put修改数据
 *  *          delete删除数据
 *  *
 *  * @author 罗泽豪
 *  * @since 2022-01-05
 */
@Controller
@ResponseBody
@RequestMapping(value = "/deptInf")
public class DeptInfController {

    @Autowired
    DeptInfServiceImpl deptInfService;

    @GetMapping
    public PageResoponse<ArrayList<DeptInf>> selectAllDeptlnf(
            int page,
            int size,
            @RequestParam(required = false) String name
    ){
        /**
         * 如果有分页,请在这里接受页面数,和页面数据大小
         *@RequestParam("page") Integer page,
         * @RequestParam(value = "size",required = false) Integer size
         *
         * if(size == null) size = StaticData.pageSize;
         * '
         * 再传给服务层的方法,后再数据库获取总页面数和当前页数
         * 在传到PageRespopnse方法进行封装一次,最后进行返回对象
         */
        if(name != null){
            //有条件查询
            return deptInfService.selectDeptlnfByCondition(page,size,name);
        }else{
            return deptInfService.selectDeptlnf(page,size);
        }
    }

    @PostMapping
    public String addDeptlnf(
            @RequestBody(required = false) DeptInf deptInf
    ){
//        DeptInf deptInf = new DeptInf();
//        deptInf.setName(map.get("name").toString());
//        deptInf.setRemark(map.get("remark").toString());

        if(deptInf.getName() == null || deptInf.getRemark() == null) return "error";
        return deptInfService.addDeptInf(deptInf) == 1 ? "success" : "error" ;
    }

    @PutMapping
    public String updateDeptlnf(
            @RequestBody(required = false) DeptInf deptInf
    ){
//        DeptInf deptInf = new DeptInf();
//        deptInf.setId((Integer) map.get("id"));
//        deptInf.setName(map.get("name").toString());
//        deptInf.setRemark(map.get("remark").toString());
        if(deptInf.getId() == null ||
                deptInf.getName() == null ||
                deptInf.getRemark() == null) return "error";
        return deptInfService.updateDeptInf(deptInf) == 1 ? "success" : "error" ;
    }

    @DeleteMapping
    public String deleteDeptlnf(
            @RequestBody ArrayList<Integer> arrayList
    ){
        int state = 0;
        for(Integer id : arrayList){
            if (deptInfService.deleteDeptInf(id) != 1) {
                state++;
            }
        }
        return state == 0 ? "success" : "error" ;
    }
}

