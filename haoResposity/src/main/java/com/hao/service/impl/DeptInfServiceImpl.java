package com.hao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hao.dao.deptInfMapper.DeptInfMapper;
import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.service.DeptInfService;
import com.hao.utils.StaticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 罗泽豪
 * @since 2022-01-05
 */
@Service
public class DeptInfServiceImpl implements DeptInfService {

    @Autowired
    DeptInfMapper deptInfMapper;

    /**
     *  在添加数据的时候,不需要设置id属性,
     *  所以在controller也不需要传入id属性
     *
     * @param deptInf
     * @return
     */
    @Override
    public int addDeptInf(DeptInf deptInf) {
        return deptInfMapper.addDeptInf(deptInf);
    }

    @Override
    public int deleteDeptInf(int id) {
        return deptInfMapper.deleteDeptInf(id);
    }

    @Override
    public int updateDeptInf(DeptInf deptInf) {
        return deptInfMapper.updateDeptInf(deptInf);
    }

    @Override
    public PageResoponse<ArrayList<DeptInf>> selectDeptlnf(int page , int size) {
        /**
         * 如果有分页,请使用
         * PageHelper.startPage(page,size);
         * 传入 页面数(从1开始) 和 展示数据大小
         */
        PageHelper.startPage(page,size);
        return new PageResoponse<ArrayList<DeptInf>>(deptInfMapper.selectDeptlnf(null),deptInfMapper.getCountAll(),page);
    }

    @Override
    public PageResoponse<ArrayList<DeptInf>> selectDeptlnfByCondition(int page, int size, String name) {
        PageHelper.startPage(page,size);
        return new PageResoponse<ArrayList<DeptInf>>(deptInfMapper.selectDeptlnf(name),deptInfMapper.getCountByCondition(name),page);
    }
}
