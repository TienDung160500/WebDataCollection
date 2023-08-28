package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.QuanLyThongSoEntity;
import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuanLyThongSoRepository extends JpaRepository<QuanLyThongSoEntity, Integer>{
    //----------------------- Template Quản lý thông số ----------------
    //☺ Tìm kiếm theo mã thông số
    public List<QuanLyThongSoEntity> findAllByMaThongSo(String maThongSo);
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity" +
            " quan_ly_thong_so WHERE quan_ly_thong_so.maThongSo = :m")
    public QuanLyThongSoEntity getByMaThongSo(@Param("m")String maThongSo);
    //☺ Sự kiện tìm kiếm
    @Query("select quan_ly_thong_so from QuanLyThongSoEntity " +
            "quan_ly_thong_so where " +
            "quan_ly_thong_so.maThongSo like %:a% " +
            "and quan_ly_thong_so.tenThongSo like %:b% " +
            "and quan_ly_thong_so.updateBy like %:e% " +
            "and quan_ly_thong_so.status like %:f% or " +
            "quan_ly_thong_so.maThongSo like %:a% " +
            "and quan_ly_thong_so.tenThongSo like %:b% " +
            "and quan_ly_thong_so.ngayTao >= :c " +
            "and quan_ly_thong_so.updateBy like %:e% " +
            "and quan_ly_thong_so.status like %:f% or " +
            "quan_ly_thong_so.maThongSo like %:a% " +
            "and quan_ly_thong_so.tenThongSo like %:b% " +
            "and quan_ly_thong_so.timeUpdate >= :d " +
            "and quan_ly_thong_so.updateBy like %:e% " +
            "and quan_ly_thong_so.status like %:f% or " +
            "quan_ly_thong_so.maThongSo like %:a% " +
            "and quan_ly_thong_so.tenThongSo like %:b% " +
            "and quan_ly_thong_so.ngayTao >= :c " +
            "and quan_ly_thong_so.timeUpdate >= :d " +
            "and quan_ly_thong_so.updateBy like %:e% " +
            "and quan_ly_thong_so.status like %:f%")
    public List<QuanLyThongSoEntity> timKiemThongSo(@Param("a")String maThongSo,
                                                    @Param("b")String tenThongSo,
                                                    @Param("c") Date ngayTao,
                                                    @Param("d") Date timeUpdate,
                                                    @Param("e")String updateBy,
                                                    @Param("f")String status);
}
