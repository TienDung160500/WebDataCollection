package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ThietBiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThietBiRepository extends JpaRepository<ThietBiEntity,Integer> {
    //-------------------------------    Thiết bị -------------------------
    //------------------------- Tìm kiếm theo mã thiết bị ------------------------------(ok)
    @Query("select thiet_bi from ThietBiEntity " +
            "thiet_bi  where thiet_bi.maThietBi like %:c%")
    public List<ThietBiEntity> getByListMaThietBi(@Param("c") String maThietBi);
    //------------------------ Tìm kiếm theo loại thiết bị -------------------(ok)
    @Query("SELECT thiet_bi FROM ThietBiEntity " +
            "thiet_bi WHERE thiet_bi.loaiThietBi like %:c%")
    public List<ThietBiEntity> getByLoaiThietBi(@Param("c") String loaiThietBi);
    //------------------------ Tìm kiếm theo dây chuyền -----------------------(ok)
    @Query("SELECT thiet_bi FROM ThietBiEntity " +
            "thiet_bi WHERE thiet_bi.dayChuyen like %:c%")
    public List<ThietBiEntity> getByDayChuyen(@Param("c") String dayChuyen);
    //-------------------------Tìm kiếm theo ngày tạo ----------------------------(ok)
    @Query("select thiet_bi from ThietBiEntity " +
            "thiet_bi where thiet_bi.ngayTao like %:c%")
    public List<ThietBiEntity> getByNgayTao(@Param("c") String ngayTao);
    //-------------------------Tìm kiếm theo ngày cập nhật --------------------------(ok)
    @Query("select thiet_bi from ThietBiEntity " +
            "thiet_bi where thiet_bi.timeUpdate like %:c%")
    public List<ThietBiEntity> getByTimeUpdate(@Param("c") String timeUpdate);
    //-------------------------- Tìm kiếm theo người tạo -----------------------------(ok)
    @Query("select thiet_bi from ThietBiEntity " +
            "thiet_bi where thiet_bi.updateBy = :c")
    public List<ThietBiEntity> getByUpdateBy(@Param("c") String updateBy);
    //---------------------------Tìm kiếm theo status -----------------------(ok)
    @Query("select thiet_bi from ThietBiEntity " +
            "thiet_bi where thiet_bi.status = :c")
    public List<ThietBiEntity> getByStatus(@Param("c") String status);
}
