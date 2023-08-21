package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import lombok.Data;

@Data
public class QuanLyThongSoPostRequest {
    private Integer maThongSo;
    private String tenThongSo;
    private String moTa;
    private String status;
}
