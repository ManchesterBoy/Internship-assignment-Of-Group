package com.hao.service.impl;

import com.hao.dto.PageResoponse;
import com.hao.entity.EmployeeInf;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * EmployeeInfServiceImplTest
 * 员工表的增删改查
 * @author LeslieLu
 *  @since 2022-01-06
 */

public class EmployeeInfServiceImplTest {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    /**
     * 带条件(按顺序：sex，phone,cardId,deptname,jobname)查询
     */
    @Test
    public void test05(){
        EmployeeInfServiceImpl employeeInfServiceImpl = context.getBean("employeeInfServiceImpl",EmployeeInfServiceImpl.class);
        EmployeeInf employeeInf1 = new EmployeeInf();
        employeeInf1.setJobId(1);

        PageResoponse<ArrayList<EmployeeInf>> arrayListPageResoponse = employeeInfServiceImpl.selectEmployeeInfByCondition(
                employeeInf1,
                1,999);
//        System.out.println(arrayListPageResoponse);
        for(EmployeeInf employeeInf : arrayListPageResoponse.getData()){
            System.out.println(employeeInf);
        }
    }


    /**
     * 查询
     */
    @Test
    public void test01(){
        EmployeeInfServiceImpl employeeInfServiceImpl = context.getBean("employeeInfServiceImpl",EmployeeInfServiceImpl.class);
        int page=1;
        int size=1;
//        ArrayList<EmployeeInf> employeeInfs = employeeInfServiceImpl.selectEmployeeInf(page,size);
//
//        for(EmployeeInf employeeInf : employeeInfs){
//            System.out.println(employeeInf);
//        }
    }

    /**
     * 修改
     */

    @Test
    public void test02(){
        EmployeeInfServiceImpl employeeInfServiceImpl = context.getBean("employeeInfServiceImpl",EmployeeInfServiceImpl.class);
        EmployeeInf employeeInf = new EmployeeInf();

        employeeInf.setId(30);

        employeeInf.setHobby("diu你么么哒");
        employeeInf.setDeptId(1);
        employeeInf.setJobId(1);
        employeeInf.setName("Lex");
        employeeInf.setCardId("6666");
        employeeInf.setAddress("韶关学院");
        employeeInf.setPostCode("66666");
        employeeInf.setTel("020-999999");
        employeeInf.setPhone("123456789");
        employeeInf.setQqNum("1472583690");
        employeeInf.setEmail("999@qq.com");
        employeeInf.setSex(1);
        employeeInf.setParty("群众");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s="2022-12-31";
            Date date = sdf.parse(s);
//            employeeInf.setBirthday(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        employeeInf.setRace("汉");
        employeeInf.setEducation("硕士");
        employeeInf.setSpeciality("软件工程");
        employeeInf.setRemark("牛子分牛");


        int i = employeeInfServiceImpl.updateEmployeeInf(employeeInf);
        System.out.println(i);
    }

    /**
     * 增加
     */
    @Test
    public void test03(){
        EmployeeInfServiceImpl employeeInfServiceImpl = context.getBean("employeeInfServiceImpl",EmployeeInfServiceImpl.class);
        EmployeeInf employeeInf = new EmployeeInf();

        employeeInf.setDeptId(1);
        employeeInf.setJobId(1);
        employeeInf.setName("Leslie");
        employeeInf.setCardId("9999");
        employeeInf.setAddress("韶关学院");
//        employeeInf.setPostCode("999999");
//        employeeInf.setTel("020-66666");
        employeeInf.setPhone("123456789");
//        employeeInf.setQqNum("1472583690");
        employeeInf.setEmail("999@qq.com");
        employeeInf.setSex(1);
//        employeeInf.setParty("群众");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s="2022-08-06";
            Date date = sdf.parse(s);
            System.out.println(date);
//            employeeInf.setBirthday(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }



//        employeeInf.setRace("汉");
//        employeeInf.setEducation("硕士");
//        employeeInf.setSpeciality("软件工程");
//        employeeInf.setRemark("牛子分牛");
//        employeeInf.setHobby("你好牛啊");

        int i = employeeInfServiceImpl.addEmployeeInf(employeeInf);
        System.out.println(i);
    }

    /**
     * 删除
     */
    @Test
    public void test04(){
        EmployeeInfServiceImpl employeeInfServiceImpl = context.getBean("employeeInfServiceImpl",EmployeeInfServiceImpl.class);
        int id = 28;
        int i = employeeInfServiceImpl.deleteEmployeeInf(id);
        System.out.println(i);
    }

}
