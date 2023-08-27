package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.SanXuatHangNgayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SanXuatHangNgayRepository  extends JpaRepository<SanXuatHangNgayEntity,Integer> {
//☺ tim kiem
    @Query("select a from SanXuatHangNgayEntity a where " +
            "a.maKichBan like %:b% and a.maThietBi like %:c% and a.loaiThietBi like %:d% " +
            "and a.dayChuyen like %:e% and a.maSanPham like %:f% and a.versionSanPham like %:g% " +
            "and a.status like %:k% " +
            "or a.maKichBan like %:b% and a.maThietBi like %:c% and a.loaiThietBi like %:d% " +
            "and a.dayChuyen like %:e% and a.maSanPham like %:f% and a.versionSanPham like %:g% " +
            "and a.ngayTao >= :h and a.status like %:k% " +
            "or a.maKichBan like %:b% and a.maThietBi like %:c% and a.loaiThietBi like %:d% " +
            "and a.dayChuyen like %:e% and a.maSanPham like %:f% and a.versionSanPham like %:g% " +
            "and a.timeUpdate >= :i and a.status like %:k% " +
            "or a.maKichBan like %:b% and a.maThietBi like %:c% and a.loaiThietBi like %:d% " +
            "and a.dayChuyen like %:e% and a.maSanPham like %:f% and a.versionSanPham like %:g% " +
            "and a.ngayTao >= :h and a.timeUpdate >= :i and a.status like %:k%")
    public List<SanXuatHangNgayEntity> timKiemSanXuatHangNgay(@Param("b")String maKichBan,
                                                              @Param("c")String maThietBi,
                                                              @Param("d")String loaiThietBi,
                                                              @Param("e")String dayChuyen,
                                                              @Param("f")String maSanPham,
                                                              @Param("g")String versionSanPham,
                                                              @Param("h")Date ngayTao,
                                                              @Param("i")Date timeUpdate,
                                                              @Param("k")String status);
}
