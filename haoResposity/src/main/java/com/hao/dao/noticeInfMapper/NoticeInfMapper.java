package com.hao.dao.noticeInfMapper;

import com.hao.entity.NoticeInf;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanzhenchang
 * @since 2022-01-05
 */
public interface NoticeInfMapper  {
    //增加公告
    int addNotice(@Param("notice") NoticeInf notice);

    //更改公告
    int updateNotice(@Param("notice") NoticeInf notice);

    //删除公告
    int deleteNotice(int id);


    //查询全部公告
    ArrayList<NoticeInf> selectAllNotice();



    ArrayList<NoticeInf> searchNoticeByName(@Param("title") String title,@Param("content") String content);

    int getCount(@Param("title") String title,@Param("content") String content);
}
