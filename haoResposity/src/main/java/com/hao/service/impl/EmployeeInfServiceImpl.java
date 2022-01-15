package com.hao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hao.dao.employeeInfMapper.EmployeeInfMapper;
import com.hao.dto.PageResoponse;
import com.hao.entity.EmployeeInf;
import com.hao.service.EmployeeInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 *  EmployeeInfServiceImpl
 * </p>
 *
 * @author LeslieLu
 * @since 2022-01-06
 */
@Service
public class EmployeeInfServiceImpl  implements EmployeeInfService {

    @Autowired
    EmployeeInfMapper employeeInfMapper;

    @Override
    public int addEmployeeInf(EmployeeInf employeeInf) {
        return employeeInfMapper.addEmployeeInf(employeeInf);
    }

    @Override
    public int deleteEmployeeInf(int id) {
        return employeeInfMapper.deleteEmployeeInf(id);
    }

    @Override
    public int updateEmployeeInf(EmployeeInf employeeInf) {
        return employeeInfMapper.updateEmployeeInf(employeeInf);
    }

    @Override
    public PageResoponse<ArrayList<EmployeeInf>> selectEmployeeInfByCondition(
            EmployeeInf employeeInf,
            int page,
            int size
    ) {
        PageHelper.startPage(page,size);
        return new PageResoponse<ArrayList<EmployeeInf>>(employeeInfMapper.selectEmployeeInfByCondition(employeeInf),
                employeeInfMapper.getCount(employeeInf),page);
    }


//    @Override
//    public ArrayList<EmployeeInf> selectEmployeeInf(int page,int size) {
//        PageHelper.startPage(page,size);
//        return employeeInfMapper.selectEmployeeInf();
//    }
}
