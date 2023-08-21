package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {
    private Integer id;
    private String userName;
    private String password;
    private Date createAt;
    private Date lastLogin;

    public UserResponse(Integer id, String userName, String password, Date createAt, Date lastLogin) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.createAt = createAt;
        this.lastLogin = lastLogin;
    }

    public UserResponse() {
    }
}
