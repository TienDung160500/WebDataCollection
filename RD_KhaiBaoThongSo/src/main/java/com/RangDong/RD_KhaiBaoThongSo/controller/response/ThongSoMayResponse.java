package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThongSoMayResponse {
    private Integer idThongSoThietBi;
    private Integer idThietBi;
    private Integer idThongSo;
    private String maThietBi;
    private String loaiThietBi;
    private Integer rows;
    private String thongSo;
    private String mo_ta;
    private String status;
    private String phanLoai;

    public ThongSoMayResponse() {
    }
}
