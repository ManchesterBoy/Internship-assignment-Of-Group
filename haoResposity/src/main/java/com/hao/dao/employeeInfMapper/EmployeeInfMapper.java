package com.hao.dao.employeeInfMapper;

import com.hao.entity.DeptInf;
import com.hao.entity.EmployeeInf;
import com.hao.entity.JobInf;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  EmployeeInfMapper 接口
 * </p>
 *
 * @author LeslieLu
 * @since 2022-01-06
 */
public interface EmployeeInfMapper {

    /**
     * 增加
     */
    int addEmployeeInf(@Param("emp") EmployeeInf employeeInf);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteEmployeeInf(int id);

    /**
     * 修改
     * @param employeeInf
     * @return
     */
    int updateEmployeeInf(EmployeeInf employeeInf);

    /**
     * 查询
     * @return
     */
    ArrayList<EmployeeInf> selectEmployeeInf();

    /**
     * 带条件(职位，性别，手机，身份证号码，所属部门)查询
     */
    ArrayList<EmployeeInf> selectEmployeeInfByCondition(@Param("emp") EmployeeInf employeeInf);

    /**
     * 分步查询01
     * 通过查询员工Id来获得员工的部门号,职位号
     */
    ArrayList<EmployeeInf> selectEmployeeInfById(Integer id);

    int getCount(@Param("emp") EmployeeInf employeeInf );
}
