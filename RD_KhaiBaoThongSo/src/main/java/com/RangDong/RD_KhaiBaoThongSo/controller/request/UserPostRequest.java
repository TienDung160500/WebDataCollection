package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserPostRequest {
    private String userName;
    private String password;
    private Date ngayTao;
    private Date lastLogin;
}
