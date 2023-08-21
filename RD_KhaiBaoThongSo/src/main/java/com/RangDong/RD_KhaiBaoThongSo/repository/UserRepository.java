package com.RangDong.RD_KhaiBaoThongSo.repository;

import com.RangDong.RD_KhaiBaoThongSo.controller.response.UserResponse;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Query("SELECT user FROM UserEntity user ")
    public List<UserEntity> selectAll();

    @Query("SELECT user FROM UserEntity user WHERE user.userName=:c")
    public UserEntity getByUserName(@Param("c") String userName);

    @Query("SELECT user.userName From UserEntity user")
    public List<String> getAllUserName();
}
