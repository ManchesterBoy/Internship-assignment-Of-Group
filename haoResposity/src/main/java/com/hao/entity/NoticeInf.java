package com.hao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author luozehao123
 * @author zhanzhenchang
 * @since 2022-01-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeInf implements Serializable {

    private Integer id;
    private String title;
    private String content;
    private String createDate;
    private Integer userId;

    private UserInf userInf;

}
