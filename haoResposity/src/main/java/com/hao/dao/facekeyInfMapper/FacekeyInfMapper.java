package com.hao.dao.facekeyInfMapper;

import com.hao.entity.FacekeyInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁智慧
 * @since 2022-01-05
 */
@Mapper
public interface FacekeyInfMapper  {

    int addFacekeyInf(@Param("facekey") FacekeyInf facekeyInf);

    int deleteFacekeyInf(int id);

    int updateFacekeyInf(@Param("facekey") FacekeyInf facekeyInf);

    ArrayList<FacekeyInf> selectFacekeyInf(int page,int size);

}
