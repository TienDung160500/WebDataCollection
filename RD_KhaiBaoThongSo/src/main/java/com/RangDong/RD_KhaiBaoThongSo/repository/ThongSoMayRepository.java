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
    //----------------------- Chức năng thêm mới thiết bị -----------------------------------------------
  //☺ lấy dữ liệu theo tên thông số máy
    @Query("select thong_so_may from ThongSoMayEntity " +
            "thong_so_may where thong_so_may.thongSo =:c")
    public ThongSoMayEntity getByTenThongSoMay(@Param("c")String tenThongSo);
    //☺ tim kiem theo ma thiet bi
    @Query("select thong_so_may from ThongSoMayEntity " +
            "thong_so_may where thong_so_may.maThietBi = :c")
    public List<ThongSoMayEntity> getByMaThietBi(@Param("c") String maThietBi);
    //☺ Xem chi tiết thông số theo mã thiết bị

    public List<ThongSoMayEntity> findAllByMaThietBi(String maThietBi);
    //☺ Lấy danh sách thông số theo danh sách id thông số thiết bị
    public ThongSoMayEntity findAllByIdThongSoThietBi(Integer idThongSoThietBi);

}
