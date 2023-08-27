package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ThietBiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ThietBiRepository extends JpaRepository<ThietBiEntity,Integer> {
    //-------------------------------    Thiết bị -------------------------
    //☺ Tìm kiếm theo mã thiết bị
    @Query("select thiet_bi from ThietBiEntity " +
            "thiet_bi  where thiet_bi.maThietBi like %:c%")
    public List<ThietBiEntity> getByListMaThietBi(@Param("c") String maThietBi);
    //☺ sự kiện tìm kiếm
    @Query("select thiet_bi from ThietBiEntity thiet_bi where " +
            "thiet_bi.maThietBi like %:a% and thiet_bi.loaiThietBi like %:b% and thiet_bi.dayChuyen like %:c% and " +
            "thiet_bi.updateBy like %:f% and thiet_bi.status like %:g% " +
            "or thiet_bi.maThietBi like %:a% and thiet_bi.loaiThietBi like %:b% and thiet_bi.dayChuyen like %:c% and " +
            "thiet_bi.ngayTao >= :d and thiet_bi.updateBy like %:f% and thiet_bi.status like %:g% " +
            "or thiet_bi.maThietBi like %:a% and thiet_bi.loaiThietBi like %:b% and thiet_bi.dayChuyen like %:c% and " +
            "thiet_bi.timeUpdate >= :e and thiet_bi.updateBy like %:f% and thiet_bi.status like %:g% " +
            "or thiet_bi.maThietBi like %:a% and thiet_bi.loaiThietBi like %:b% and thiet_bi.dayChuyen like %:c% and " +
            "thiet_bi.ngayTao >= :d and thiet_bi.timeUpdate >= :e and thiet_bi.updateBy like %:f% and thiet_bi.status like %:g%")
    public List<ThietBiEntity> timKiemThietBi(@Param("a") String maThietBi,
                                              @Param("b") String loaiThietBi,
                                              @Param("c") String dayChuyen,
                                              @Param("d") Date ngayTao,
                                              @Param("e") Date timeUpdate,
                                              @Param("f") String updateBy,
                                              @Param("g") String status);
}
