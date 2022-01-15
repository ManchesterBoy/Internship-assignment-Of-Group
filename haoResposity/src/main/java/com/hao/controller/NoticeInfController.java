package com.hao.controller;


import com.hao.dto.PageResoponse;
import com.hao.entity.NoticeInf;
import com.hao.service.impl.NoticeInfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 功能:
 *         对公告的增删改查
 *
 *  方法:
 *          get获取数据
 *          post添加数据
 *          put修改数据
 *          delete删除数据
 *
 * @author 詹圳昌
 * @since 2022-01-06
 */
@Controller
@ResponseBody
@RequestMapping("/noticeInf")
public class NoticeInfController {
    @Autowired
    NoticeInfServiceImpl noticeInfServiceImpl;

//    //查询显示所有公告信息
//    @GetMapping(value = "allNoticeInf")
//    public ArrayList<NoticeInf> selectAllNoticeInf(int page,int size){
//        return noticeInfServiceImpl.selectAllNotice(page,size);
//    }

    //根据公告标题进行模糊查询
    @GetMapping()
    public PageResoponse<ArrayList<NoticeInf>> selectNoticeByName(
            @RequestParam(required = false) String title,
            @RequestParam(required = false)String content,
            int page,
            int size
    ){
        return noticeInfServiceImpl.searchNoticeByName(title,content,page,size);
    }


    //添加公告信息
    @PostMapping
    public String addNoticeInf(
            @RequestBody(required = false) NoticeInf noticeInf
    ){
//        NoticeInf noticeInf = new NoticeInf();
//        noticeInf.setTitle(map.get("title").toString());
//        noticeInf.setContent(map.get("content").toString());
//        noticeInf.setUserId((Integer) map.get("userId"));
        if(noticeInf.getTitle() == null ||
                noticeInf.getContent() == null ||
                noticeInf.getUserId() == null
        ) return "error";
        return noticeInfServiceImpl.addNotice(noticeInf) == 1 ? "success" : "error" ;
    }


    //更新公告信息
    @PutMapping
    public String updateNoticeInf(
            @RequestBody(required = false) NoticeInf noticeInf
    ){
//        NoticeInf noticeInf = new NoticeInf();
//        noticeInf.setTitle(map.get("title").toString());
//        noticeInf.setContent(map.get("content").toString());
//        noticeInf.setId((Integer) map.get("id"));

        if(noticeInf.getId() == null || noticeInf.getTitle() == null
                || noticeInf.getContent() ==null ) return "error";
        return noticeInfServiceImpl.updateNotice(noticeInf) == 1 ? "success" : "error" ;
    }


    //删除公告
    @DeleteMapping
    public String deleteNoticeInf(
            @RequestBody ArrayList<Integer> arrayList
    ){
        int state = 0;
        for(Integer id : arrayList){
            if (noticeInfServiceImpl.deleteNotice(id) != 1) {
                state++;
            }
        }
        return state == 0 ? "success" : "error" ;
    }

}

