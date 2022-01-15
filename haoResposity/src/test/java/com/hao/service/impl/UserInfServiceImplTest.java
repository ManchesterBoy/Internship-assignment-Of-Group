package com.hao.service.impl;

import com.hao.dto.PageResoponse;
import com.hao.entity.UserInf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;


/**
 * UserInfServiceImplTest
 * 用户表的增删改查测试
 * @author LeslieLu
 * @since 2022-01-06
 */

public class UserInfServiceImplTest {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    /**
     * 带条件(用户名，状态)查询
     */
    @Test
    public void test05(){
        UserInfServiceImpl userInfServiceImpl = context.getBean("userInfServiceImpl",UserInfServiceImpl.class);
        String s1=null;
        Integer s = 1;
        int page = 1;
        int size = 1;
        PageResoponse<ArrayList<UserInf>> arrayListPageResoponse = userInfServiceImpl.selectUserInFByCondition(s1, s, page, size);

        for(UserInf userInf : arrayListPageResoponse.getData()){
            System.out.println(userInf);
        }
    }


    /**
     * 查询
     */
    @Test
    public void test01(){
        UserInfServiceImpl userInfServiceImpl = context.getBean("userInfServiceImpl",UserInfServiceImpl.class);
        int page=1;
        int size= 1;
        PageResoponse<ArrayList<UserInf>> arrayListPageResoponse = userInfServiceImpl.selectUserInF(page, size);
        System.out.println(arrayListPageResoponse);
        for(UserInf userInf : arrayListPageResoponse.getData()){
            System.out.println(userInf);
        }
    }

    /**
     * 修改
     */
    @Test
    public void test02(){
        UserInfServiceImpl userInfServiceImpl = context.getBean("userInfServiceImpl",UserInfServiceImpl.class);
        UserInf userInf = new UserInf();
        userInf.setId(9);
        userInf.setUsername("username");
        userInf.setStatus(1);
        userInf.setLoginname("loginname");
        int i = userInfServiceImpl.updateUserInF(userInf);
        System.out.println(i);
    }

    /**
     *添加
     */

    @Test
    public void test03(){
        UserInfServiceImpl userInfServiceImpl = context.getBean("userInfServiceImpl",UserInfServiceImpl.class);
        UserInf userInf = new UserInf();
        userInf.setUsername("11");
        userInf.setStatus(1);
        userInf.setLoginname("songyq");
        userInf.setPassword("123456");
        int i = userInfServiceImpl.addUserInF(userInf);
        System.out.println(i);
    }

    /**
     * 删除
     */
    @Test
    public void test04(){
        UserInfServiceImpl userInfServiceImpl = context.getBean("userInfServiceImpl",UserInfServiceImpl.class);
        int id=10;
        int i=userInfServiceImpl.deleteUserInF(id);
        System.out.println(i);
    }

    /**
     * 测试用户登录
     */
    @Test
    public void test11(){
        UserInfServiceImpl userInfServiceImpl = context.getBean("userInfServiceImpl",UserInfServiceImpl.class);

            UserInf songyq = userInfServiceImpl.checkUserInfo("songyq", "96e79218965eb72c92a549dd5a330112");
        System.out.println(songyq);
    }

    /**
     * 通过id查询用户
     */
    @Test
    public void test011(){
        UserInfServiceImpl userInfServiceImpl = context.getBean("userInfServiceImpl",UserInfServiceImpl.class);

        UserInf songyq = userInfServiceImpl.selectById(1);
        System.out.println(songyq);


        
    }

}
