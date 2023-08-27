package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ChiTietKichBanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietKichBanRepository extends JpaRepository<ChiTietKichBanEntity,Integer> {
    //☺Xem danh sach thong so kich ban
    public List<ChiTietKichBanEntity> findAllByMaKichBan(String maKichBan);
    //☺ Tim kiem theo id_chi_tiet_kich_ban
    public ChiTietKichBanEntity findAllByIdChiTietKichBan(Integer idChiTietKichBan);

}
