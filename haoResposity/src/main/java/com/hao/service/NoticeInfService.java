package com.hao.service;

import com.hao.dto.PageResoponse;
import com.hao.entity.NoticeInf;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luozehao123
 * @author zhanzhenchang
 * @since 2022-01-05
 */
public interface NoticeInfService  {

    //增加公告
    int addNotice(NoticeInf notice);

    //更改公告
    int updateNotice(NoticeInf notice);

    //删除公告
    int deleteNotice(int id);

    //查询全部公告
    ArrayList<NoticeInf> selectAllNotice(int page,int size);

    //模糊搜索公告名称查询公告
    PageResoponse<ArrayList<NoticeInf>> searchNoticeByName(String title, String content, int page, int size);

}
