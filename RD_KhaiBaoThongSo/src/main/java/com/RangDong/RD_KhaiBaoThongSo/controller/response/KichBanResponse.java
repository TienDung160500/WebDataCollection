package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KichBanResponse {
    private Integer idKichBan;
    private String maKichBan;

    private String maThietBi;

    private String loaiThietBi;

    private String dayChuyen;

    private String maSanPham;

    private String versionSanPham;

    private Date ngayTao;

    private Date timeUpdate;

    private String updateBy;

    private String status;
}
