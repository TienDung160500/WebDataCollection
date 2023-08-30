package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "quan_ly_thong_so")
public class QuanLyThongSoEntity {
    @Id
    @Column(name = "id_thong_so")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idThongSo;
    @Column(name = "ma_thong_so")
    private String maThongSo;
    @Column(name = "ten_thong_so")
    private String tenThongSo;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "time_update")
    private Date timeUpdate;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "status")
    private String status;
}
