package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.data.annotation.Reference;

import java.util.Date;
import java.util.List;

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

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "time_update")
    private Date timeUpdate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "thietBiEntity")
    private List<ThongSoMayEntity> thongSoMayEntities;
}
