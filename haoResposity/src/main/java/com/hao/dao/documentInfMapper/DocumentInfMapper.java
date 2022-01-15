package com.hao.dao.documentInfMapper;


import com.hao.entity.DeptInf;
import com.hao.entity.DocumentInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luozehao123
 * @since 2022-01-05
 */

@Mapper
public interface DocumentInfMapper  {

    int addDocInf(@Param("doc") DocumentInf documentInf);

    int deleteDocInf(int id);

    int updateDocInf(@Param("doc") DocumentInf documentInf);

    ArrayList<DocumentInf> selectAllDocInf();

    ArrayList<DocumentInf> selectDocInfByTitle(@Param("title") String title);


    int getCount(@Param("title") String title);
}
