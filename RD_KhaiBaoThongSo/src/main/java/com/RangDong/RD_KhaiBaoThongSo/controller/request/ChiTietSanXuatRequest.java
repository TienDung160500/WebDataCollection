package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ChiTietSanXuatRequest {
    private Integer idChiTietSanXuat;

    private Integer idSanXuatHangNgay;

    private String maKichBan;

    private Integer rows;

    private String thongSo;

    private Float minValue;

    private Float maxValue;

    private Float trungBinh;

    private String donVi;
}
