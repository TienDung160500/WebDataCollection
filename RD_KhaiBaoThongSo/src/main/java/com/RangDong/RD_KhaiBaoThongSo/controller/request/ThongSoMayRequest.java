package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import lombok.Data;

@Data
public class ThongSoMayRequest {
    private Integer idThietBi;
    private Integer idThongSo;
    private String maThietBi;
    private String loaiThietBi;
    private Integer rows;
    private String thongSo;
    private String moTa;
    private String status;
    private String phanLoai;
}
