package com.hao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hao.dao.facekeyInfMapper.FacekeyInfMapper;
import com.hao.entity.FacekeyInf;
import com.hao.service.FacekeyInfService;
import org.springframework.stereotype.Service;
import com.hao.utils.StaticData;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁智慧
 * @since 2022-01-05
 */
@Service
public class FacekeyInfServiceImpl implements FacekeyInfService {
    @Autowired
    FacekeyInfMapper facekeyInfMapper;

    /**
     *  在添加数据的时候,不需要设置id属性,
     *  所以在controller也不需要传入id属性
     *
     * @param facekeyInf
     * @return
     */
    @Override
    public int addFacekeyInf(FacekeyInf facekeyInf) {
        return facekeyInfMapper.addFacekeyInf(facekeyInf);
    }

    @Override
    public int deleteFacekeyInf(int id) {
        return facekeyInfMapper.deleteFacekeyInf(id);
    }

    @Override
    public int updateFacekeyInf(FacekeyInf facekeyInf) {
        return facekeyInfMapper.updateFacekeyInf(facekeyInf);
    }

    @Override
    public ArrayList<FacekeyInf> selectFacekeyInf(int page,int size) {
        /**
         * 如果有分页,请使用
         * PageHelper.startPage(page,size);
         * 传入 页面数(从1开始) 和 展示数据大小
         */
        PageHelper.startPage(page, size);
        return facekeyInfMapper.selectFacekeyInf(page,size);
    }

}
