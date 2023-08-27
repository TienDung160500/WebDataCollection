package com.RangDong.RD_KhaiBaoThongSo.controller.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class KichBanRequest {
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
