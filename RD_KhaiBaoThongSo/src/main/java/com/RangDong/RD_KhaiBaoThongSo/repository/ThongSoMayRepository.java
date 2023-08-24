package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.QuanLyThongSoEntity;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ThongSoMayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThongSoMayRepository extends JpaRepository<ThongSoMayEntity, Integer> {
    //====================================================================================================
    //----------------------- Chức năng thêm mới thiết bị -----------------------------------------------
  //--------------------------------- lấy dữ liệu theo tên thông số máy -----------------------
    @Query("select thong_so_may from ThongSoMayEntity " +
            "thong_so_may where thong_so_may.thongSo =:c")
    public ThongSoMayEntity getByTenThongSoMay(@Param("c")String tenThongSo);
    //--------------------- tim kiem theo ma thiet bi --------------------------
    @Query("select thong_so_may from ThongSoMayEntity " +
            "thong_so_may where thong_so_may.maThietBi = :c")
    public List<ThongSoMayEntity> getByMaThietBi(@Param("c") String maThietBi);
    //---------------------- Xem chi tiết thông số theo mã thiết bị ---------------------------------- ( chưa làm được)
    @Query("select thong_so_may.thongSo, thong_so_may.status,quan_ly_thong_so.ngayTao,quan_ly_thong_so.timeUpdate from " +
            "ThongSoMayEntity thong_so_may inner join QuanLyThongSoEntity quan_ly_thong_so on " +
            "thong_so_may.idThongSo = quan_ly_thong_so.idThongSo where thong_so_may.phanLoai like %:c%")
    public List<QuanLyThongSoEntity> getChiTietThongSoMay(@Param("c") String phanLoai);
}
