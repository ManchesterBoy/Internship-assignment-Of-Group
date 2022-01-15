package com.hao.dto;

import lombok.Data;

/**
 * @Auther: 罗泽豪
 * @Date: 2022/1/9 11:56
 * @Description:  返回状态
 */
@Data
public class State<T> {

    T data;
    int state;

    public State(T data, int state) {
        this.data = data;
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
