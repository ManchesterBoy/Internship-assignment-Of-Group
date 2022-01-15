package com.hao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hao.dao.documentInfMapper.DocumentInfMapper;
import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.DocumentInf;
import com.hao.service.DocumentInfService;
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
 */
@Service
public class DocumentInfServiceImpl  implements DocumentInfService {

    @Autowired
    DocumentInfMapper documentInfMapper;

    /**
     *  在添加数据的时候,不需要设置id属性,
     *  所以在controller也不需要传入id属性
     *
     * @param documentInf
     * @return
     */
    @Override
    public int addDocInf(DocumentInf documentInf) {
        return documentInfMapper.addDocInf(documentInf);
    }

    @Override
    public int deleteDocInf(int id) {
        return documentInfMapper.deleteDocInf(id);
    }

    @Override
    public int updateDocInf(DocumentInf documentInf) {
        return documentInfMapper.updateDocInf(documentInf);
    }

    @Override
    public ArrayList<DocumentInf> selectAllDocInf(int page,int size) {
        /**
         * 如果有分页,请使用
         * PageHelper.startPage(page,size);
         * 传入 页面数(从1开始) 和 展示数据大小
         */

        PageHelper.startPage(page,size);
        return documentInfMapper.selectAllDocInf();
    }

    @Override
    public PageResoponse<ArrayList<DocumentInf>> selectDocInfByTitle(String title, int page, int size) {
        PageHelper.startPage(page,size);
        return new PageResoponse<ArrayList<DocumentInf>>(documentInfMapper.selectDocInfByTitle(title),
                documentInfMapper.getCount(title),page);
    }
}
