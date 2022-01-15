package com.hao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
public class DeptInf implements Serializable {

    private Integer id;
    private String name;
    private String remark;


}
