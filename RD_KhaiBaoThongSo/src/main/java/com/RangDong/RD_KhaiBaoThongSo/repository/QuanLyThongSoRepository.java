package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.QuanLyThongSoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuanLyThongSoRepository extends JpaRepository<QuanLyThongSoEntity, Integer>{
    //----------------------- Template Quản lý thông số ----------------
    //------------- Tìm kiếm theo mã thông số ----------------- (ok)
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity" +
            " quan_ly_thong_so WHERE quan_ly_thong_so.maThongSo like (%:m%)")
    public List<QuanLyThongSoEntity> getByListMaThongSo(@Param("m")String maThongSo);
    List<QuanLyThongSoEntity> findAllByMaThongSo(String maThongSo);
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity" +
            " quan_ly_thong_so WHERE quan_ly_thong_so.maThongSo like (%:m%)")
    public QuanLyThongSoEntity getByMaThongSo(@Param("m")String maThongSo);
    //------------- Tìm kiếm theo tên thông số -----------------(ok)
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity" +
            " quan_ly_thong_so WHERE quan_ly_thong_so.tenThongSo like %:m% ")
    public List<QuanLyThongSoEntity> getByTenThongSo(@Param("m") String tenThongSo);
    //------------- Tìm kiếm theo ngày tạo -----------------(ok)
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity" +
            " quan_ly_thong_so WHERE quan_ly_thong_so.ngayTao like %:m%")
    public List<QuanLyThongSoEntity> getByNgayTao(@Param("m")String ngayTao);
    //------------- Tìm kiếm theo ngày cập nhật -----------------(ok)
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity" +
            " quan_ly_thong_so WHERE quan_ly_thong_so.timeUpdate like %:m%")
    public List<QuanLyThongSoEntity> getByTimeUpdate(@Param("m")String timeUpdate);
    //------------- Tìm kiếm theo tài khoản khởi tạo thông số -----------------(ok)
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity" +
            " quan_ly_thong_so WHERE quan_ly_thong_so.updateBy = :m")
    public List<QuanLyThongSoEntity> getByUpdateBy(@Param("m") String updateBy);
    //------------- Tìm kiếm theo trạng thái (status) -----------------(ok)
    @Query("SELECT quan_ly_thong_so FROM QuanLyThongSoEntity " +
            " quan_ly_thong_so WHERE quan_ly_thong_so.status = :m")
    public List<QuanLyThongSoEntity> getByStatus(@Param("m")String status);
    //----------------------------- Sự kiện tìm kiếm --------------------------------------------
    @Query("select quan_ly_thong_so from QuanLyThongSoEntity " +
            "quan_ly_thong_so where quan_ly_thong_so.maThongSo like '%:a%' " +
            "and quan_ly_thong_so.tenThongSo like '%:b%' " +
            "and quan_ly_thong_so.ngayTao like '%:c%' " +
            "and quan_ly_thong_so.timeUpdate like '%:d%' " +
            "and quan_ly_thong_so.updateBy like '%:e%' " +
            "and quan_ly_thong_so.status like '%:f%'")
    public List<QuanLyThongSoEntity> timKiemThongSo(@Param("a") String maThongSo,
                                                    @Param("b") String tenThongSo,
                                                    @Param("c") Date ngayTao,
                                                    @Param("d") Date timeUpdate,
                                                    @Param("e") String updateBy,
                                                    @Param("f") String status);

}
