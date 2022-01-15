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
public class UserInf implements Serializable {

    private Integer id;
    private String loginname;
    private String password;
    private Integer status;
    private String createdate;
    private String username;
    private String faceurl;
    private String facepath;


}
