package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "chi_tiet_lich_su_update")
public class ChiTietLichSuUpdateEntity {
    @Id
    @Column(name = "id_chi_tiet_lich_su_update")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChiTietLichSuUpdate;

    @Column(name = "ma_kich_ban")
    private String maKichBan;

    @Column(name = "rows")
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

}
