package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "kich_ban")
public class KichBanEntity {
    @Id
    @Column(name = "id_kich_ban")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKichBan;

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

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "time_update")
    private Date timeUpdate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "trang_thai")
    private String status;

    @OneToMany(mappedBy = "kichBan")
    @JsonManagedReference
    private List<ChiTietKichBanEntity> chiTietKichBan;
}
