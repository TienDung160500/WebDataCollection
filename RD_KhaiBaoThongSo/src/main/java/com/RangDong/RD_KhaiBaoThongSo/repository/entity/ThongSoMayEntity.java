package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity

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
    @Column(name = "hang_tms")
    private Integer rows;
    @Column(name = "thong_so")
    private String thongSo;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "trang_thai")
    private String status;

    @Column(name = "phan_loai")
    private String phanLoai;

    @ManyToOne
    @JoinColumn(name = "id_thiet_bi")
    @JsonBackReference
    private ThietBiEntity thietBi;

    public Integer getIdThongSoThietBi() {
        return idThongSoThietBi;
    }

    public void setIdThongSoThietBi(Integer idThongSoThietBi) {
        this.idThongSoThietBi = idThongSoThietBi;
    }

    public String getMaThietBi() {
        return maThietBi;
    }

    public void setMaThietBi(String maThietBi) {
        this.maThietBi = maThietBi;
    }

    public String getLoaiThietBi() {
        return loaiThietBi;
    }

    public void setLoaiThietBi(String loaiThietBi) {
        this.loaiThietBi = loaiThietBi;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getThongSo() {
        return thongSo;
    }

    public void setThongSo(String thongSo) {
        this.thongSo = thongSo;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public ThietBiEntity getThietBi() {
        return thietBi;
    }

    public void setThietBi(ThietBiEntity thietBi) {
        this.thietBi = thietBi;
    }

}
