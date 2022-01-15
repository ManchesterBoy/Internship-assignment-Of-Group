package com.hao.controller;


import com.hao.dto.PageResoponse;
import com.hao.dto.State;
import com.hao.entity.UserInf;
import com.hao.service.impl.UserInfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能:
 *         对用户的增删改查
 *         UserInfController
 *
 *  方法:
 *          get获取数据
 *          post添加数据
 *          put修改数据
 *          delete删除数据
 *
 * @author LeslieLu
 * @since 2022-01-06
 */

/**
 *
 * @Param  status的权限值,1为管理员.2为普通用户
 * 登录名和用户应该保持一致,所以只需要传入登录名,用户名进行传值便可
 *
 * 增加验证登录
 *
 * @author 罗泽豪补充
 * @since 2022-01-09
 */
@Controller
@RequestMapping("/userInf")
@ResponseBody
public class UserInfController {

    @Autowired
    UserInfServiceImpl userInfService;

    /**
     * 登录验证
     * @return
     */
    @PostMapping("/loginByPass")
    public State<UserInf> loginByPass(
            @RequestBody HashMap<String,String> map
    ){
        String username = map.get("username");
        String password = map.get("password");
        if(username == null || password == null) return new State<UserInf>(null,400);
        UserInf userInf = userInfService.checkUserInfo(username, password);
        int state = 200;
        if(userInf == null) state = 400;
        return new State<UserInf>(userInf,state);
    }

    /*
    通过登录名查询用户是否已经注册人脸识别!
     */
    @GetMapping("/byLoginname")
    public Map byLoginame(
        @RequestParam String loginname
    ){
        UserInf s = userInfService.selectByLoginname(loginname);
        HashMap<String, String> hashMap = new HashMap<>();
        if(s == null) return hashMap;
        hashMap.put("id", String.valueOf(s.getId()));
        hashMap.put("path",s.getFacepath());
        return hashMap;
    }

    
    /*
    查询全部
     */
    @GetMapping( )
    public PageResoponse<ArrayList<UserInf>> selectAllUserInF(
            int page,
            int size,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer status
    ){
        if(username != null || status != null ){
            //条件查询
            return userInfService.selectUserInFByCondition(username,status,page,size);
        }else{
            //普通查询
            return userInfService.selectUserInF(page,size);
        }
    }

//    /*
//    条件查询
//     */
//    @GetMapping
//    public ArrayList<UserInf> selectEmployeeInfByCondition(String username,Integer status,int page,int size){
//        return userInfService.selectUserInFByCondition(username,status,page,size);
//    }


    /*
    增加
     */
    @PostMapping
    public String addUserInf(
            @RequestBody(required = false) UserInf userInf
    ){
//        UserInf userInf = new UserInf();
//        userInf.setLoginname(map.get("loginname").toString());
//        userInf.setUsername(map.get("username").toString());
//        userInf.setPassword(map.get("password").toString());
//        userInf.setStatus((Integer) map.get("status"));

        if(userInf.getLoginname() == null ||
                userInf.getStatus() == null ||
                userInf.getUsername() == null ||
                userInf.getPassword() == null)
            return "error";
        userInf.setUsername(userInf.getLoginname());
        return userInfService.addUserInF(userInf) == 1 ? "success" : "error" ;
    }

    /*
    修改
     */
    @PutMapping
    public String updateUserInF(
            @RequestBody(required = false) UserInf userInf
    ){
//        UserInf userInf = new UserInf();
//        userInf.setId((Integer) map.get("id"));
//
//        userInf.setLoginname(map.get("loginname").toString());
//        userInf.setUsername(map.get("username").toString());
//        userInf.setStatus((Integer) map.get("status"));

        if(userInf.getId() == null ) return "error";
        return userInfService.updateUserInF(userInf) == 1 ? "success" : "error" ;
    }

    /*
    删除
     */
    @DeleteMapping
    public String deleteUserInF(
            @RequestBody ArrayList<Integer> arrayList
    ){
        int state = 0;
        for(Integer id : arrayList){
            if (userInfService.deleteUserInF(id) != 1) {
                state++;
            }
        }
        return state == 0 ? "success" : "error" ;
    }
    





}

