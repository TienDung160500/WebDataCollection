package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import lombok.Data;

import java.util.Date;
@Data
public class ThietBiRequest {
    private String maThietBi;
    private String loaiThietBi;
    private String dayChuyen;
    private Date ngayTao;
    private Date timeUpdate;
    private String updateBy;
    private String status;
}
