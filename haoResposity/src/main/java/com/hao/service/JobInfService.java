package com.hao.service;

import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.JobInf;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 蒋江龙
 * @since 2022-01-05
 */
public interface JobInfService  {
    int addJobInf(JobInf jobInf);

    int deleteJobInf(int id);

    int updateJobInf(JobInf jobInf);

    PageResoponse<ArrayList<JobInf>> selectJoblnf(int page, int size,String name);



}
