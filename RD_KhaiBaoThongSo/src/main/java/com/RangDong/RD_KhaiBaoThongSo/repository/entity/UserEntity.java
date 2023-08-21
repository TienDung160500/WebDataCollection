package com.RangDong.RD_KhaiBaoThongSo.repository.entity;

import jakarta.persistence.*;
import lombok.CustomLog;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "last_login")
    private Date lastLogin;
}
