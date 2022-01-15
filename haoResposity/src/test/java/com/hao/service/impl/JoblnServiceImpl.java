package com.hao.service.impl;


import com.hao.dto.PageResoponse;
import com.hao.entity.JobInf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
/**
 * @Auther: 蒋江龙
 * @Date: 2022年1月6日10:50:29
 * @Description: 测试部门服务的方法
 */

public class JoblnServiceImpl {
    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");
    /**
     * 测试查询结果
     */

   @Test
    public  void test01(){
        JobInfServiceImpl JobInfServiceImpl = context.getBean("jobInfServiceImpl", JobInfServiceImpl.class);
       PageResoponse<ArrayList<JobInf>> arrayListPageResoponse = JobInfServiceImpl.selectJoblnf(1, 5,"职员");
       for(JobInf JobInf : arrayListPageResoponse.getData()){
            System.out.println(JobInf);
        }
    }
    /**
     * 测试修改数据
     */
    @Test
    public  void test02(){
        JobInfServiceImpl jobInfServiceImpl = context.getBean("jobInfServiceImpl", JobInfServiceImpl.class);
        JobInf jobInf = new JobInf();
        jobInf.setId(9);
        jobInf.setName("你好6666");
        jobInf.setRemark("你不好");
        int i = jobInfServiceImpl.updateJobInf(jobInf);
        System.out.println(i);
    }
    /**
     * 测试添加数据
     */

    @Test
    public  void test03(){
       JobInfServiceImpl  jobInfServiceImpl = context.getBean("jobInfServiceImpl", JobInfServiceImpl.class);
        JobInf  jobInf = new  JobInf();
        jobInf.setName("你好");
        jobInf.setRemark("你不好");
        int i =  jobInfServiceImpl.addJobInf(jobInf);
        System.out.println(i);
    }
    /**
     * 测试删除数据
     */
    @Test
    public  void test04(){
       JobInfServiceImpl jobInfServiceImpl = context.getBean("jobInfServiceImpl", JobInfServiceImpl.class);
        int id = 13;
        int i = jobInfServiceImpl.deleteJobInf(id);
        System.out.println(i);
    }













}
