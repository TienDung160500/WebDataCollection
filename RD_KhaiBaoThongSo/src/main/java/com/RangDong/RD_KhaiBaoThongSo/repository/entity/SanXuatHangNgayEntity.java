package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "san_xuat_hang_ngay")
public class SanXuatHangNgayEntity {
    @Id
    @Column(name = "id_san_xuat_hang_ngay")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSanXuatHangNgay;

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

    @Column(name = "trang_thai")
    private String status;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sanXuatHangNgay")
    @JsonManagedReference
    private List<ChiTietSanXuatEntity> chiTietSanXuat;
}
