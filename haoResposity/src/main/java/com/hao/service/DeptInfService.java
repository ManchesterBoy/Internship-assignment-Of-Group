package com.hao.service;

import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 罗泽豪
 * @since 2022-01-05
 */
public interface DeptInfService  {

    int addDeptInf(DeptInf deptInf);

    int deleteDeptInf(int id);

    int updateDeptInf(DeptInf deptInf);

    PageResoponse<ArrayList<DeptInf>> selectDeptlnf(int page , int size);

    PageResoponse<ArrayList<DeptInf>> selectDeptlnfByCondition(int page , int size, String name);


}
