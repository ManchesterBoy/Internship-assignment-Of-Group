package com.hao.dao.userInfMapper;



import com.baidubce.model.User;
import com.hao.entity.UserInf;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  UserInfMapper 接口
 * </p>
 *
 * @author LeslieLu
 * @since 2022-01-06
 */

public interface UserInfMapper  {

    //新增用户：姓名、状态、登录名
    int addUserInF(UserInf userInf);

    /*点击删除按钮（管理员权限可操作）：如果有选中的用户，对用户进行删除，
    之后输出新的用户列表；如果没有选择的，输出“请选择要删除的用户”提示。*/
    int deleteUserInF(int id);

    //编辑用户：姓名、状态、登录名
    int updateUserInF(@Param("userInf") UserInf userInf);

    //带条件（用户名，用户状态）查询
    ArrayList<UserInf> selectUserInFByCondition(@Param("username") String username,@Param("status") Integer status);

    /*点击查询按钮：根据输入的用户名和用户状态，输出用户列表，
    如果输入的用户名和用户状态，为空，输出所有的文章列表*/
    ArrayList<UserInf> selectUserInF();

    UserInf checkUser(@Param("username")String username,@Param("password")String password);

    int getAllCount();

    int getAllCountByCondition(@Param("username") String username,@Param("status") Integer status);

    UserInf selectByLoginname(String loginname);

    UserInf selectById(Integer id);
}
