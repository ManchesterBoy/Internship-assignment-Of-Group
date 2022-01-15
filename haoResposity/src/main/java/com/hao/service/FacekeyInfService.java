package com.hao.service;


import com.hao.entity.FacekeyInf;
import org.apache.ibatis.annotations.Param;
import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁智慧
 * @since 2022-01-05
 */

public interface FacekeyInfService  {



    int addFacekeyInf(FacekeyInf facekeyInf);

    int deleteFacekeyInf(int id);

    int updateFacekeyInf(FacekeyInf facekeyInf);

    ArrayList<FacekeyInf> selectFacekeyInf(int page,int size);




}
