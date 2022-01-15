package com.hao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author luozehao123
 * @since 2022-01-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInf implements Serializable {

    private Integer id;

    private Integer deptId;
    private Integer jobId;
    private Integer sex;
    private String phone;
    private String name;
    private String cardId;

    private String address;
    private String postCode;
    private String tel;
    private String qqNum;
    private String email;
    private String party;
    private String birthday;
    private String race;
    private String education;
    private String speciality;
    private String hobby;
    private String remark;
    private String createDate;

    private DeptInf deptInf;
    private JobInf jobInf;

}
