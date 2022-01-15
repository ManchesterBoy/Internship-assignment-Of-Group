package com.hao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class FacekeyInf implements Serializable {

    private Integer id;
    private String appID;
    private String apiKey;
    private String secretKey;


}
