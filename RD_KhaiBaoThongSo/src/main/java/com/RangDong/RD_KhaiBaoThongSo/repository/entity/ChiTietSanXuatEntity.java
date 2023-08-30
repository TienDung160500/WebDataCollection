package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "chi_tiet_san_xuat")
public class ChiTietSanXuatEntity {
    @Id
    @Column(name = "id_chi_tiet_san_xuat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChiTietSanXuat;

    @Column(name = "ma_kich_ban")
    private String maKichBan;

    @Column(name = "hang_sxhn")
    private Integer rows;

    @Column(name = "thong_so")
    private String thongSo;

    @Column(name = "min_value")
    private Float minValue;

    @Column(name = "max_value")
    private Float maxValue;

    @Column(name = "trung_binh")
    private Float trungBinh;

    @Column(name = "don_vi")
    private String donVi;

    @ManyToOne
    @JoinColumn(name = "id_san_xuat_hang_ngay")
    @JsonBackReference
    private SanXuatHangNgayEntity sanXuatHangNgay;
}
