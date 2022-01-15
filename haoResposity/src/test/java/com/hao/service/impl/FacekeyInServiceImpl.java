package com.hao.service.impl;
import com.hao.entity.FacekeyInf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;


/**
 * @Auther: 袁智慧
 * @Date: 2022/1/5 20:49
 * @Description: 测试部门服务的方法
 */
public class FacekeyInServiceImpl {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    /**
     * 测试查询结果
     */


    @Test
    public  void test01(){
        FacekeyInfServiceImpl facekeyInfServiceImpl= context.getBean("facekeyInfServiceImpl", FacekeyInfServiceImpl.class);
        ArrayList<FacekeyInf> facekeyInfs = facekeyInfServiceImpl.selectFacekeyInf(1,5);
        for(FacekeyInf facekeyInf : facekeyInfs){
            System.out.println(facekeyInf);
        }
    }


    /**
     * 测试修改数据
     */
    @Test
    public  void test02(){
        FacekeyInfServiceImpl facekeyInfServiceImpl = context.getBean("facekeyInfServiceImpl", FacekeyInfServiceImpl.class);
        FacekeyInf facekeyInf = new FacekeyInf();
        facekeyInf.setId(2);
        facekeyInf.setAppID("12");
        facekeyInf.setApiKey("12");
        facekeyInf.setSecretKey("dddddd");
        int i = facekeyInfServiceImpl.updateFacekeyInf(facekeyInf);
        System.out.println(i);
    }

    /**
     * 测试添加数据
     */
    @Test
    public  void test03(){
        FacekeyInfServiceImpl facekeyInfServiceImpl = context.getBean("facekeyInfServiceImpl", FacekeyInfServiceImpl.class);
        FacekeyInf facekeyInf = new FacekeyInf();
        facekeyInf.setId(5);
        facekeyInf.setAppID("1");
        facekeyInf.setApiKey("12");
        facekeyInf.setSecretKey("ddddd");
        int i = facekeyInfServiceImpl.addFacekeyInf(facekeyInf);
        System.out.println(i);
    }

    /**
     * 测试删除数据
     */
    @Test
    public  void test04(){
        FacekeyInfServiceImpl facekeyInfServiceImpl = context.getBean("facekeyInfServiceImpl", FacekeyInfServiceImpl.class);
        int id = 2;
        int i = facekeyInfServiceImpl.deleteFacekeyInf(id);
        System.out.println(i);
    }




}
