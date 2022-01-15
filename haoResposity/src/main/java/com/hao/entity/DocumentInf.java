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
public class DocumentInf implements Serializable {

    private Integer id;
    private String title;
    private String filename;
    private String remark;
    private String createDate;
    private Integer userId;
    
    private UserInf userInf;
}
