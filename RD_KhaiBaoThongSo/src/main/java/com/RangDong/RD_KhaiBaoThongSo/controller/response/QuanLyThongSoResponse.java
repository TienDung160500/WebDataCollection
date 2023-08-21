package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class QuanLyThongSoResponse {
    private Integer idThongSo;
    private String maThongSo;
    private String tenThongSo;
    private String moTa;
    private Date ngayTao;
    private Date timeUpdate;
    private String updateBy;
    private String status;

    public QuanLyThongSoResponse(){}
}
