package com.hao.controller;


import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.EmployeeInf;
import com.hao.entity.JobInf;
import com.hao.service.impl.DeptInfServiceImpl;
import com.hao.service.impl.EmployeeInfServiceImpl;
import com.hao.service.impl.JobInfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 *  EmployeeInfController
 *  功能:
 *           对员工的增删改查
 *  方法:
 *            get获取数据
 *            post添加数据
 *            put修改数据
 *            delete删除数据
 * </p>
 *
 * @author LeslieLu
 * @since 2022-01-06
 */
@Controller
@ResponseBody
@RequestMapping("/employeeInf")
public class EmployeeInfController {

    @Autowired
    EmployeeInfServiceImpl employeeInfService;
    @Autowired
    DeptInfServiceImpl deptInfService;
    @Autowired
    JobInfServiceImpl jobInfService;
    /*
    查询全部
     */
    @GetMapping()
    public PageResoponse<ArrayList<EmployeeInf>> selectEmployeeInf(
            int page,
            int size,
            @RequestParam(required = false) Integer sex,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String cardId,
            @RequestParam(required = false) Integer jobId,
            @RequestParam(required = false) Integer depId,
            @RequestParam(required = false) String name
    ){
        EmployeeInf employeeInf = new EmployeeInf();
        employeeInf.setName(name);
        employeeInf.setSex(sex);
        employeeInf.setPhone(phone);
        employeeInf.setCardId(cardId);
        employeeInf.setJobId(jobId);
        employeeInf.setDeptId(depId);

        PageResoponse<ArrayList<EmployeeInf>> arrayListPageResoponse = employeeInfService.selectEmployeeInfByCondition(employeeInf, page, size);
        arrayListPageResoponse.setDepthInf(deptInfService.selectDeptlnf(1, 999));
        arrayListPageResoponse.setJobInf(jobInfService.selectJoblnf(1, 999,null));

        return arrayListPageResoponse;
    }
    
    /*
    增加
     */
    @PostMapping
    public String addEmployeeInf(
            @RequestBody(required = false) EmployeeInf employeeInf
    ){
//        EmployeeInf employeeInf = new EmployeeInf();
//        employeeInf.setName(map.get("name").toString());
//        employeeInf.setCardId(map.get("cardId").toString());
//        employeeInf.setPhone(map.get("phone").toString());
//        employeeInf.setAddress(map.get("address").toString());
//        employeeInf.setEmail(map.get("email").toString());
//        employeeInf.setJobId((Integer) map.get("jobId"));
//        employeeInf.setDeptId((Integer) map.get("deptId"));
//        employeeInf.setSex((Integer) map.get("sex"));
        if(employeeInf.getName() == null || employeeInf.getCardId() == null
            || employeeInf.getJobId() == null || employeeInf.getDeptId() == null
            || employeeInf.getPhone() ==null || employeeInf.getSex() ==null
            ||  employeeInf.getAddress() == null || employeeInf.getEmail() == null) return "error";
        return employeeInfService.addEmployeeInf(employeeInf) == 1 ? "success" : "error" ;
    }

    /*
    修改
     */
    @PutMapping
    public String updateEmployeeInf(
            @RequestBody(required = false) EmployeeInf employeeInf
    ){
//        EmployeeInf employeeInf = new EmployeeInf();
//        employeeInf.setName(map.get("name").toString());
//        employeeInf.setCardId(map.get("cardId").toString());
//        employeeInf.setPhone(map.get("phone").toString());
//        employeeInf.setAddress(map.get("address").toString());
//        employeeInf.setEmail(map.get("email").toString());
//        employeeInf.setJobId((Integer) map.get("jobId"));
//        employeeInf.setDeptId((Integer) map.get("deptId"));
//        employeeInf.setSex((Integer) map.get("sex"));
//        employeeInf.setId((Integer) map.get("id"));
        if(employeeInf.getName() == null || employeeInf.getCardId() == null
                || employeeInf.getJobId() == null || employeeInf.getDeptId() == null
                || employeeInf.getPhone() ==null || employeeInf.getSex() ==null 
                || employeeInf.getId() == null  ||  employeeInf.getAddress() == null
                || employeeInf.getEmail() == null) return "error";
        return employeeInfService.updateEmployeeInf(employeeInf) == 1 ? "success" : "error";
    }

    /*
    删除
     */
    @DeleteMapping
    public String deleteEmployeeInf(
            @RequestBody ArrayList<Integer> arrayList
    ){
        int state = 0;
        for(Integer id : arrayList){
            if (employeeInfService.deleteEmployeeInf(id) != 1) {
                state++;
            }
        }
        return state == 0 ? "success" : "error" ;
    }


}

