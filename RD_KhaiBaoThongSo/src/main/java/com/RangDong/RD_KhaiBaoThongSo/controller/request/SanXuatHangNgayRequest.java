package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import lombok.Data;

import java.util.Date;
@Data
public class SanXuatHangNgayRequest {
    private Integer idSanXuatHangNgay;

    private String maKichBan;

    private String maThietBi;

    private String loaiThietBi;

    private String dayChuyen;

    private String maSanPham;

    private String versionSanPham;

    private Date ngayTao;

    private Date timeUpdate;

    private String status;
}
