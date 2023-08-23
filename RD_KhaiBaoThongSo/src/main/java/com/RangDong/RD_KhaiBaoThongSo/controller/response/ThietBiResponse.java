package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ThietBiResponse {
    private Integer idThietBi;
    private String maThietBi;
    private String loaiThietBi;
    private String dayChuyen;
    private Date ngayTao;
    private Date timeUpdate;
    private String updateBy;
    private String status;

    public ThietBiResponse() {
    }
}
