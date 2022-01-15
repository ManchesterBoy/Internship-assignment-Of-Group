package com.hao.service.impl;

import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.DocumentInf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Auther: 黄炎俊
 * @Date: 2022/1/6 20:49
 * @Description: 测试文件管理的方法
 */
public class DocumentlnServiceImpl {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    /**
     * 测试查询结果
     */
    @Test
    public  void test01(){
        DocumentInfServiceImpl documentInfService = context.getBean("documentInfServiceImpl", DocumentInfServiceImpl.class);
        PageResoponse<ArrayList<DocumentInf>> sssss = documentInfService.selectDocInfByTitle(null, 1, 5);
        for(DocumentInf documentInf : sssss.getData()){
            System.out.println(documentInf);
        }
    }

    /**
     * 测试修改数据
     */
    @Test
    public  void test02(){
        DocumentInfServiceImpl documentInfService = context.getBean("documentInfServiceImpl", DocumentInfServiceImpl.class);
        DocumentInf documentInf = new DocumentInf();

        Date   now   =   new   Date();
        SimpleDateFormat dateFormat   =   new   SimpleDateFormat();//可以方便地修改日期式
        String  str= dateFormat.format(now);


        documentInf.setId(7);
        documentInf.setTitle("第二个文件");
        documentInf.setRemark("这是第二个文件");
        documentInf.setFilename("文件二");
        documentInf.setUserId(1);
//        documentInf.setCreateDate(now);
        int i = documentInfService.updateDocInf(documentInf);
        System.out.println(i);
    }

    /**
     * 测试添加数据
     */
    @Test
    public  void test03(){
        DocumentInfServiceImpl documentInfService = context.getBean("documentInfServiceImpl", DocumentInfServiceImpl.class);
        DocumentInf documentInf = new DocumentInf();


//        获得系统时间
//        Date   now   =   new   Date();
//        SimpleDateFormat dateFormat   =   new   SimpleDateFormat();//可以方便地修改日期式
//        String  str= dateFormat.format(now);
        
        documentInf.setTitle("第二个文件");
        documentInf.setFilename("文件二");
        documentInf.setRemark("这是第二个文件");
        documentInf.setUserId(1);
//        documentInf.setCreateDate(now);
        
        int i = documentInfService.addDocInf(documentInf);
        System.out.println(i);
    }

    /**
     * 测试删除数据
     */
    @Test
    public  void test04(){
        DocumentInfServiceImpl documentInfService = context.getBean("documentInfServiceImpl", DocumentInfServiceImpl.class);
        DocumentInf documentInf = new DocumentInf();

        int id = 9;
        int i = documentInfService.deleteDocInf(id);
        System.out.println(i);
    }




}
