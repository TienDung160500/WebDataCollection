package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietKichBanResponse {
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
