package com.hao.service;

import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.DocumentInf;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄炎俊
 * @since 2022-01-06
 */
public interface DocumentInfService {

    int addDocInf(DocumentInf documentInf);

    int deleteDocInf(int id);

    int updateDocInf(DocumentInf documentInf);

    ArrayList<DocumentInf> selectAllDocInf(int page,int size);

    PageResoponse<ArrayList<DocumentInf>> selectDocInfByTitle(String title, int page, int size);
}
