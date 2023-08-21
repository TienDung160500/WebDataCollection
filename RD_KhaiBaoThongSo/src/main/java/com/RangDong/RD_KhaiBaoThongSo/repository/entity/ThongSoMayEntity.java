package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thong_so_may")
public class ThongSoMayEntity {
    @Id
    @Column(name = "id_thong_so_thiet_bi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idThongSoThietBi;

    @Column(name = "ma_thiet_bi")
    private String maThietBi;

    @Column(name = "loai_thiet_bi")
    private String loaiThietBi;

    @Column(name = "rows")
    private Integer rows;

    @Column(name = "thong_so")
    private String thongSo;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "status")
    private String status;

    @Column(name = "phan_loai")
    private String phanLoai;


}
