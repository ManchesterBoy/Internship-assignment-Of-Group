package com.hao.service;

import com.hao.dto.PageResoponse;
import com.hao.entity.EmployeeInf;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 *  EmployeeInfService
 * </p>
 *
 * @author LeslieLu
 * @since 2022-01-06
 */
public interface EmployeeInfService  {

    /**
     * 增加
     */
    int addEmployeeInf(EmployeeInf employeeInf);

    /**
     *删除
     */
    int deleteEmployeeInf(int id);

    /**
     * 修改
     */
    int updateEmployeeInf(EmployeeInf employeeInf);

    /**
     * 查询
     */
//    ArrayList<EmployeeInf> selectEmployeeInf(int page,int size);

    /**
     * 带条件查询
     */
    PageResoponse<ArrayList<EmployeeInf>> selectEmployeeInfByCondition
    (EmployeeInf employeeInf,int page,int size);

}
