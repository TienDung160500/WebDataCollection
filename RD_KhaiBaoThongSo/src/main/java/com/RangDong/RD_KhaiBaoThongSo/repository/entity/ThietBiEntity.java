package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.data.annotation.Reference;

import java.util.Date;
import java.util.List;

@Entity
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "thietBi",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ThongSoMayEntity> thongSoMayEntities;

    public Integer getIdThietBi() {
        return idThietBi;
    }

    public void setIdThietBi(Integer idThietBi) {
        this.idThietBi = idThietBi;
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

    public String getDayChuyen() {
        return dayChuyen;
    }

    public void setDayChuyen(String dayChuyen) {
        this.dayChuyen = dayChuyen;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ThongSoMayEntity> getThongSoMayEntities() {
        return thongSoMayEntities;
    }

    public void setThongSoMayEntities(List<ThongSoMayEntity> thongSoMayEntities) {
        this.thongSoMayEntities = thongSoMayEntities;
    }
}
