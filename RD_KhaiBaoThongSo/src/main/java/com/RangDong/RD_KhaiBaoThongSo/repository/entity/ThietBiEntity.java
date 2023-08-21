package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "thiet_bi")
public class ThietBiEntity {
    @Id
    @Column(name = "id_thiet_bi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idThietBi;

    @Column(name = "ma_thiet_bi")
    private String maThietBi;

    @Column(name = "loai_thiet_bi")
    private String loaiThietBi;

    @Column(name = "day_chuyen")
    private String dayChuyen;

    @Column(name = "ngay_hoat_dong")
    private Date ngayHoatDong;

    @Column(name = "create_by")
    private String createBy;
}
