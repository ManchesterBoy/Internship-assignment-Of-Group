package com.hao.service.impl;

import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * @Auther: 罗泽豪
 * @Date: 2022/1/5 20:49
 * @Description: 测试部门服务的方法
 */
public class DeptlnServiceImpl {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    /**
     * 测试查询结果
     */
    @Test
    public  void test01(){
        DeptInfServiceImpl deptInfServiceImpl = context.getBean("deptInfServiceImpl", DeptInfServiceImpl.class);
        PageResoponse<ArrayList<DeptInf>> arrayListPageResoponse = deptInfServiceImpl.selectDeptlnf(1, 1);
        for(DeptInf deptInf : arrayListPageResoponse.getData()){
            System.out.println(deptInf);
        }
    }

    /**
     * 测试修改数据
     */
    @Test
    public  void test02(){
        DeptInfServiceImpl deptInfServiceImpl = context.getBean("deptInfServiceImpl", DeptInfServiceImpl.class);
        DeptInf deptInf = new DeptInf();
        deptInf.setId(8);
        deptInf.setName("你好");
        deptInf.setRemark("你不好");
        int i = deptInfServiceImpl.updateDeptInf(deptInf);
        System.out.println(i);
    }

    /**
     * 测试添加数据
     */
    @Test
    public  void test03(){
        DeptInfServiceImpl deptInfServiceImpl = context.getBean("deptInfServiceImpl", DeptInfServiceImpl.class);
        DeptInf deptInf = new DeptInf();
        deptInf.setName("你好");
        deptInf.setRemark("你不好");
        int i = deptInfServiceImpl.addDeptInf(deptInf);
        System.out.println(i);
    }

    /**
     * 测试删除数据
     */
    @Test
    public  void test04(){
        DeptInfServiceImpl deptInfServiceImpl = context.getBean("deptInfServiceImpl", DeptInfServiceImpl.class);
        int id = 16;
        int i = deptInfServiceImpl.deleteDeptInf(id);
        System.out.println(i);
    }




}
