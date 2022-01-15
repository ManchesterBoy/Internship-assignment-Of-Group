package com.hao.dao.jobInfMapper;


import com.hao.entity.DeptInf;
import com.hao.entity.JobInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 蒋江龙
 * @since 2022-01-05
 */
@Mapper
public interface JobInfMapper  {

    int addJobInf(@Param("job") JobInf jobInf);

    int deleteJobInf(int id);

    int updateJobInf(@Param("job") JobInf jobInf);

    ArrayList<JobInf> selectJoblnf(@Param("name") String name);

    int getCount(@Param("name")String name);


}
