package com.hao.service.impl;

import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.NoticeInf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Auther: 詹圳昌
 * @since: 2022/1/6 10：00
 * @author zhanzhenchang
 * @Description: 测试公告服务的方法
 */
public class NoticeServiceImpl {
    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    /**
     * 测试查询结果
     */
    @Test
    public  void test01(){
        NoticeInfServiceImpl noticeInfServiceImpl = context.getBean("noticeInfServiceImpl", NoticeInfServiceImpl.class);
        PageResoponse<ArrayList<NoticeInf>> arrayListPageResoponse = noticeInfServiceImpl.searchNoticeByName("参加会议通知", "今天有技术部有会议", 1, 2);
        for(NoticeInf noticeInf : arrayListPageResoponse.getData()){
            System.out.println(noticeInf);
        }
    }

    /**
     * 测试修改数据
     */
    @Test
    public  void test02(){
        NoticeInfServiceImpl noticeInfServiceImpl = context.getBean("noticeInfServiceImpl", NoticeInfServiceImpl.class);
        NoticeInf noticeInf = new NoticeInf();
        Date date = new Date();
        noticeInf.setId(24);
        noticeInf.setTitle("adsasd");
        noticeInf.setContent("欢迎来到提瓦特");
//        noticeInf.setCreateDate(date);
        noticeInf.setUserId(9);
        int i = noticeInfServiceImpl.updateNotice(noticeInf);
        System.out.println(i);
    }

    /**
     * 测试添加数据
     */
    @Test
    public  void test03(){
        NoticeInfServiceImpl noticeInfServiceImpl = context.getBean("noticeInfServiceImpl", NoticeInfServiceImpl.class);
        NoticeInf noticeInf = new NoticeInf();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        noticeInf.setTitle("震惊！！");
        noticeInf.setContent("代码报错了");
//        noticeInf.setCreateDate(date);
        int i = noticeInfServiceImpl.addNotice(noticeInf);
        System.out.println(i);
    }

    /**
     * 测试删除数据
     */
    @Test
    public  void test04(){
        NoticeInfServiceImpl noticeInfServiceImpl = context.getBean("noticeInfServiceImpl", NoticeInfServiceImpl.class);
        int id = 23;
        int i = noticeInfServiceImpl.deleteNotice(id);
        System.out.println(i);
    }

    @Test
    public void test05(){
        NoticeInfServiceImpl noticeInfServiceImpl = context.getBean("noticeInfServiceImpl", NoticeInfServiceImpl.class);
        String search_title = "d";
//        ArrayList<NoticeInf> notices = noticeInfServiceImpl.searchNoticeByName(search_title,1,2);
//        System.out.println(notices);
    }
}
