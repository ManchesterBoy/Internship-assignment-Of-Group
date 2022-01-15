package com.hao.dao.deptInfMapper;


import com.hao.entity.DeptInf;
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
public interface DeptInfMapper  {

    int addDeptInf(@Param("dept") DeptInf deptInf);

    int deleteDeptInf(int id);

    int updateDeptInf(@Param("dept") DeptInf deptInf);

    ArrayList<DeptInf> selectDeptlnf(@Param("name") String name);


    int getCountAll();

    int getCountByCondition(String name);

}
