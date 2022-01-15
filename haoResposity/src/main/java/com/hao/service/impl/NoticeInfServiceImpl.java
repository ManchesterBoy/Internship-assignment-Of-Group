package com.hao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hao.dao.noticeInfMapper.NoticeInfMapper;
import com.hao.dto.PageResoponse;
import com.hao.entity.NoticeInf;
import com.hao.service.NoticeInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luozehao123
 * @since 2022-01-05
 * @author 1 zhanzhenchang
 */

@Service
public class NoticeInfServiceImpl  implements NoticeInfService {
    @Autowired
    NoticeInfMapper noticeInfMapper;

    //添加
    @Override
    public int addNotice(NoticeInf notice) {
        return noticeInfMapper.addNotice(notice);
    }

    //更新
    @Override
    public int updateNotice(NoticeInf notice) {
        return noticeInfMapper.updateNotice(notice);
    }

    //删除
    @Override
    public int deleteNotice(int id) {
        return noticeInfMapper.deleteNotice(id);
    }

    //显示所有公告
    @Override
    public ArrayList<NoticeInf> selectAllNotice(int page,int size) {
        PageHelper.startPage(page,size);
        return noticeInfMapper.selectAllNotice();
    }

    //根据公告名称模糊查询
    @Override
    public PageResoponse<ArrayList<NoticeInf>> searchNoticeByName(String title,String content,int page,int size) {
        PageHelper.startPage(page,size);
        return new PageResoponse<ArrayList<NoticeInf>>(noticeInfMapper.searchNoticeByName(title,content),
                noticeInfMapper.getCount(title,content),page);
    }
}
