package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "lich_su_update")
public class LichSuUpdateEntity {
    @Id
    @Column(name = "id_lich_su_update")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLichSuUpdate;

    @Column(name = "ma_kich_ban")
    private String maKichBan;

    @Column(name = "ma_thiet_bi")
    private String maThietBi;

    @Column(name = "loai_thiet_bi")
    private String loaiThietBi;

    @Column(name = "day_chuyen")
    private String dayChuyen;

    @Column(name = "ma_san_pham")
    private String maSanPham;

    @Column(name = "version_san_pham")
    private String versionSanPham;

    @Column(name = "time_update")
    private Date timeUpdate;

    @Column(name = "status")
    private String status;
}
