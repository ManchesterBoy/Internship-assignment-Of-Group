package com.hao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hao.dao.userInfMapper.UserInfMapper;
import com.hao.dto.PageResoponse;
import com.hao.entity.UserInf;
import com.hao.service.UserInfService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 *  UserInfServiceImpl
 * </p>
 *
 * @author LeslieLu
 * @since 2022-01-06
 */
@Service
public class UserInfServiceImpl  implements UserInfService {

    @Autowired
    UserInfMapper userInfMapper;

    /**
     * 判断用户的登录名是否有重复
     * @return
     */
    private Boolean noRepeatLoginname(UserInf userInfs){
        PageResoponse<ArrayList<UserInf>> arrayListPageResoponse = selectUserInF(1, 999999);
        for(UserInf userInf : arrayListPageResoponse.getData()){
            if(userInf.getLoginname().equals(userInfs.getLoginname())
                && userInf.getId() != userInfs.getId()
            ) return false;
        }

        return true;
    }
    
    //新增用户：姓名、状态、登录名
    @Override
    public int addUserInF(UserInf userInf) {
        if(!noRepeatLoginname(userInf))  return 0;

        return userInfMapper.addUserInF(userInf);
    }

    /*点击删除按钮（管理员权限可操作）：如果有选中的用户，对用户进行删除，
    之后输出新的用户列表；如果没有选择的，输出“请选择要删除的用户”提示。*/
    @Override
    public int deleteUserInF(int id){
        return userInfMapper.deleteUserInF(id);
    }

    //编辑用户：姓名、状态、登录名
    @Override
    public int updateUserInF(UserInf userInf){
        if(!noRepeatLoginname(userInf))  return 0;
        return userInfMapper.updateUserInF(userInf);

    }


    @Override
    public PageResoponse<ArrayList<UserInf>> selectUserInFByCondition(String username,Integer status,int page,int size) {
        PageHelper.startPage(page,size);


//        return userInfMapper.selectUserInFByCondition(username,status);
        return new PageResoponse<ArrayList<UserInf>>(userInfMapper.selectUserInFByCondition(username,status),
                userInfMapper.getAllCountByCondition(username,status),page);
    }

    /**
     * 进行验证用户登录
     * @param username
     * @param password
     * @return
     *
     * @Author 罗泽豪
     */
    @Override
    public UserInf checkUserInfo(String username, String password) {
        return userInfMapper.checkUser(username,password);
    }

    @Override
    public UserInf selectByLoginname(String loginname) {
        return userInfMapper.selectByLoginname(loginname);
    }

    @Override
    public UserInf selectById(Integer id) {
        return userInfMapper.selectById(id);
    }

    /*点击查询按钮：根据输入的用户名和用户状态，输出用户列表，
        如果输入的用户名和用户状态，为空，输出所有的文章列表*/
    @Override
    public PageResoponse<ArrayList<UserInf>> selectUserInF(int page,int size){
        PageHelper.startPage(page,size);
        return  new PageResoponse<ArrayList<UserInf>>(userInfMapper.selectUserInF(),userInfMapper.getAllCount(),page);
    }


}
