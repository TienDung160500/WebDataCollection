package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import lombok.Data;

@Data
public class ChiTietKichBanRequest {
    private Integer idChiTietKichBan;

    private Integer idKichBan;

    private String maKichBan;

    private Integer rows;

    private String thongSo;

    private Float minValue;

    private Float maxValue;

    private Float trungBinh;

    private String donVi;

    private String phanLoai;
}
