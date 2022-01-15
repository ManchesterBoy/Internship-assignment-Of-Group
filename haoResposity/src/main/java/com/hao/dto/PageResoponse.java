package com.hao.dto;

import com.hao.entity.DeptInf;
import com.hao.entity.JobInf;
import com.hao.utils.StaticData;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Auther: 罗泽豪
 * @Date: 2022/1/5 21:45
 * @Description: 对有需求的分页的数据,进行封装处理
 */
@Data
public class PageResoponse<T> {

    //数据源
    private T data;

    //最大页数
    private int pageMax;

    //每一页的大小
    private int pageSize = StaticData.pageSize;

    //当前页面
    private int currentPage;

    public PageResoponse(T data, int pageMax, int currentPage) {
        this.data = data;
        this.pageMax = pageMax;
        this.currentPage = currentPage;
    }

    private PageResoponse<ArrayList<DeptInf>> depthInf;
    private PageResoponse<ArrayList<JobInf>> jobInf;
    



}
