package com.hao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hao.dao.deptInfMapper.DeptInfMapper;
import com.hao.dao.jobInfMapper.JobInfMapper;
import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.JobInf;
import com.hao.service.JobInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蒋江龙
 * @since 2022-01-05
 */
@Service
public class JobInfServiceImpl  implements JobInfService {
    @Autowired
    JobInfMapper jobInfMapper;

    @Override
    public int addJobInf(JobInf jobInf) {
        return jobInfMapper.addJobInf(jobInf);
    }

    @Override
    public int deleteJobInf(int id) {
        return jobInfMapper.deleteJobInf(id);
    }

    @Override
    public int updateJobInf(JobInf jobInf) {
        return jobInfMapper.updateJobInf(jobInf);
    }

    @Override
    public PageResoponse<ArrayList<JobInf>> selectJoblnf(int page,int size,String name) {
        PageHelper.startPage(page,size);
        /**
         * 如果有分页,请使用
         * PageHelper.startPage(page,size);
         * 传入 页面数(从1开始) 和 展示数据大小
         */
        return new PageResoponse<ArrayList<JobInf>>(jobInfMapper.selectJoblnf(name),jobInfMapper.getCount(name),page);
    }



}
