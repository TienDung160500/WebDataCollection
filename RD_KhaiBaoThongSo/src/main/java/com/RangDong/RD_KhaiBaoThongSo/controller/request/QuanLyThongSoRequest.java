package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class QuanLyThongSoRequest {
    private String maThongSo;
    private String tenThongSo;
    private String moTa;
    private String status;
    private Date ngayTao;
    private Date timeUpdate;
    private String updateBy;
}
