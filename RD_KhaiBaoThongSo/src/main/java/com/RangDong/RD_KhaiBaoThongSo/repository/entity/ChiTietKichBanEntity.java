package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "chi_tiet_kich_ban")
public class ChiTietKichBanEntity {
    @Id
    @Column(name = "id_chi_tiet_kich_ban")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChiTietKichBan;

    @Column(name = "ma_kich_ban")
    private String maKichBan;

    @Column(name = "hang_mkb")
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

    @Column(name = "phan_loai")
    private String phanLoai;

    @ManyToOne
    @JoinColumn(name = "id_kich_ban")
    @JsonBackReference
    private KichBanEntity kichBan;
}
