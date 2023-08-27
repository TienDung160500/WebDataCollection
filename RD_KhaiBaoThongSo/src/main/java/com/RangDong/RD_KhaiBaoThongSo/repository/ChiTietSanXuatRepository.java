package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ChiTietSanXuatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietSanXuatRepository extends JpaRepository<ChiTietSanXuatEntity, Integer> {
    //! Xem danh sach thong so san xuat hang ngay
    public List<ChiTietSanXuatEntity> findAllByMaKichBan(String maKichban);
    //! Tim kiem theo id chi tiet hang ngay
    public ChiTietSanXuatEntity findAllByIdChiTietSanXuat(Integer idChiTietSanXuat);
}
