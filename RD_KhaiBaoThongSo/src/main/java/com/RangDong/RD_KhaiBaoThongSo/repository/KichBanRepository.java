package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.KichBanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface KichBanRepository extends  JpaRepository<KichBanEntity, Integer>{
//☺ Tim kiem kich ban
@Query("select kich_ban from KichBanEntity kich_ban where " +
        "kich_ban.maKichBan like %:b% and kich_ban.maThietBi like %:c% and kich_ban.loaiThietBi like %:d% " +
        "and kich_ban.dayChuyen like %:e% and kich_ban.maSanPham like %:f% and kich_ban.versionSanPham like %:g% " +
        "and kich_ban.updateBy like %:k% and kich_ban.status like %:m% " +
        "or kich_ban.maKichBan like %:b% and kich_ban.maThietBi like %:c% and kich_ban.loaiThietBi like %:d% " +
        "and kich_ban.dayChuyen like %:e% and kich_ban.maSanPham like %:f% and kich_ban.versionSanPham like %:g% " +
        "and kich_ban.ngayTao >= :h and kich_ban.updateBy like %:k% and kich_ban.status like %:m% " +
        "or kich_ban.maKichBan like %:b% and kich_ban.maThietBi like %:c% and kich_ban.loaiThietBi like %:d% " +
        "and kich_ban.dayChuyen like %:e% and kich_ban.maSanPham like %:f% and kich_ban.versionSanPham like %:g% " +
        "and kich_ban.timeUpdate >= :i and kich_ban.updateBy like %:k% and kich_ban.status like %:m% " +
        "or kich_ban.maKichBan like %:b% and kich_ban.maThietBi like %:c% and kich_ban.loaiThietBi like %:d% " +
        "and kich_ban.dayChuyen like %:e% and kich_ban.maSanPham like %:f% and kich_ban.versionSanPham like %:g% " +
        "and kich_ban.ngayTao >= :h and kich_ban.timeUpdate >= :i and kich_ban.updateBy like %:k% and kich_ban.status like %:m%")
    public List<KichBanEntity> timKiemKichBan(@Param("b")String maKichBan,
                                              @Param("c")String maThietBi,
                                              @Param("d")String loaiThietBi,
                                              @Param("e")String dayChuyen,
                                              @Param("f")String maSanPham,
                                              @Param("g")String versionSanPham,
                                              @Param("h")Date ngayTao,
                                              @Param("i")Date timeUpdate,
                                              @Param("k")String updateBy,
                                              @Param("m")String status);
//☺ Tim kiem theo ma kich ban
    public KichBanEntity findAllByMaKichBan(String maKichBan);
//! tim kiem theo idkichban
    public KichBanEntity findAllByIdKichBan(Integer idKichBan);
}
