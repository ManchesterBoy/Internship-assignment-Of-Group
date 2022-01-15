package com.hao.service;

import com.hao.dto.PageResoponse;
import com.hao.entity.UserInf;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 *  UserInfService
 * </p>
 *
 * @author LeslieLu
 * @since 2022-01-06
 */
public interface UserInfService  {

    /**
     * 新增用户：姓名、状态、登录名
     */
    int addUserInF(UserInf userInf);

    /**
     * 点击删除按钮（管理员权限可操作）：如果有选中的用户，对用户进行删除，
    之后输出新的用户列表；如果没有选择的，输出“请选择要删除的用户”提示。*/
    int deleteUserInF(int id);

    /**
     * 编辑用户：姓名、状态、登录名
     */
    int updateUserInF(UserInf userInf);

    /*点击查询按钮：根据输入的用户名和用户状态，输出用户列表，
    如果输入的用户名和用户状态，为空，输出所有的文章列表*/
    PageResoponse<ArrayList<UserInf>> selectUserInF(int page, int size);

    /**
     * 带条件的查询
     * @param username
     * @param status
     * @return
     */
    PageResoponse<ArrayList<UserInf>> selectUserInFByCondition(String username,Integer status,int page,int size);

    UserInf checkUserInfo(String username,String password);

    UserInf selectByLoginname(String loginname);

    UserInf selectById(Integer id);
}
