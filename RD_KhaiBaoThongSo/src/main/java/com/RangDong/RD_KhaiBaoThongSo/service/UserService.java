package com.RangDong.RD_KhaiBaoThongSo.service;

import com.RangDong.RD_KhaiBaoThongSo.controller.request.QuanLyThongSoPostRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.UserPostRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.UserPutRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.QuanLyThongSoResponse;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.ResponseMessage;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.UserResponse;
import com.RangDong.RD_KhaiBaoThongSo.repository.QuanLyThongSoRepository;
import com.RangDong.RD_KhaiBaoThongSo.repository.UserRepository;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.QuanLyThongSoEntity;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuanLyThongSoRepository quanLyThongSoRepository;

    //----------------------- Template login - Chức năng xác thực tài khoản
    public ResponseMessage loginAuth(UserPostRequest request) {
        UserEntity entity = this.userRepository.getByUserName(request.getUserName());
        boolean result = entity.getPassword().equals(request.getPassword());
        log.info("entity: " + entity);
        System.out.println("request: " + request);
        if (!result) {
            log.info("failed");
            return new ResponseMessage("Tài khoản hoặc mật khẩu bị sai");
        } else {
            log.info("success");
            // gán thời gian cho last_login trong DB
            entity.setLastLogin(request.getLastLogin());
            this.userRepository.save(entity);
            return new ResponseMessage("Đăng nhập thành công");
        }
    }

    //----------------------- Template Quản lý thông số ----------------
    //------------- Hiển thị danh sách thông số -----------------(ok)
    public List<QuanLyThongSoResponse> danhSachThongSo() {
        List<QuanLyThongSoEntity> entityList = this.quanLyThongSoRepository.findAll();
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSoEntity entity : entityList) {
            QuanLyThongSoResponse response = new QuanLyThongSoResponse();
            response.setIdThongSo(entity.getIdThongSo());
            response.setMaThongSo(entity.getMaThongSo());
            response.setTenThongSo(entity.getTenThongSo());
            response.setNgayTao(entity.getNgayTao());
            response.setTimeUpdate(entity.getTimeUpdate());
            response.setUpdateBy(entity.getUpdateBy());
            response.setStatus(entity.getStatus());
            responseList.add(response);
        }
        return responseList;
    }

    //------------- Tìm kiếm theo mã thông số -----------------(ok)
    public QuanLyThongSoResponse getByMaThongSo(String maThongSo) {
        QuanLyThongSoEntity entity = this.quanLyThongSoRepository.getByMaThongSo(maThongSo);
        if (entity == null) {
            return null;
        } else {
            QuanLyThongSoResponse response = new QuanLyThongSoResponse();
            response.setIdThongSo(entity.getIdThongSo());
            response.setMaThongSo(entity.getMaThongSo());
            response.setTenThongSo(entity.getTenThongSo());
            response.setNgayTao(entity.getNgayTao());
            response.setTimeUpdate(entity.getTimeUpdate());
            response.setUpdateBy(entity.getUpdateBy());
            response.setStatus(entity.getStatus());
            return response;
        }
    }

    //------------- Tìm kiếm theo tên thông số -----------------(ok)
    public QuanLyThongSoResponse getByTenThongSo(String tenThongSo) {
        QuanLyThongSoEntity entity = this.quanLyThongSoRepository.getByTenThongSo(tenThongSo);
        if (entity == null) {
            return null;
        } else {
            QuanLyThongSoResponse response = new QuanLyThongSoResponse();
            response.setIdThongSo(entity.getIdThongSo());
            response.setMaThongSo(entity.getMaThongSo());
            response.setTenThongSo(entity.getTenThongSo());
            response.setNgayTao(entity.getNgayTao());
            response.setTimeUpdate(entity.getTimeUpdate());
            response.setUpdateBy(entity.getUpdateBy());
            response.setStatus(entity.getStatus());
            return response;
        }
    }

    //------------- Tìm kiếm theo ngày tạo -----------------(ok)
    public List<QuanLyThongSoResponse> getByNgayTao(String ngayTao) {
        List<QuanLyThongSoEntity> entityList = this.quanLyThongSoRepository.getByNgayTao(ngayTao);
        if (entityList.isEmpty()) {
            return null;
        } else {
            List<QuanLyThongSoResponse> responseList = new ArrayList<>();
            for (QuanLyThongSoEntity entity : entityList) {
                QuanLyThongSoResponse response = new QuanLyThongSoResponse();
                response.setIdThongSo(entity.getIdThongSo());
                response.setMaThongSo(entity.getMaThongSo());
                response.setTenThongSo(entity.getTenThongSo());
                response.setNgayTao(entity.getNgayTao());
                response.setTimeUpdate(entity.getTimeUpdate());
                response.setUpdateBy(entity.getUpdateBy());
                response.setStatus(entity.getStatus());
                responseList.add(response);
            }
            return responseList;
        }
    }

    //------------- Tìm kiếm theo ngày cập nhật -----------------(ok)
    public List<QuanLyThongSoResponse> getByTimeUpdate(String timeUpdate) {
        List<QuanLyThongSoEntity> entityList = this.quanLyThongSoRepository.getByTimeUpdate(timeUpdate);
        if (entityList.isEmpty()) {
            return null;
        } else {
            log.info("timeUpdate");
            List<QuanLyThongSoResponse> responseList = new ArrayList<>();
            for (QuanLyThongSoEntity entity : entityList) {
                QuanLyThongSoResponse response = new QuanLyThongSoResponse();
                response.setIdThongSo(entity.getIdThongSo());
                response.setMaThongSo(entity.getMaThongSo());
                response.setTenThongSo(entity.getTenThongSo());
                response.setNgayTao(entity.getNgayTao());
                response.setTimeUpdate(entity.getTimeUpdate());
                response.setUpdateBy(entity.getUpdateBy());
                response.setStatus(entity.getStatus());
                responseList.add(response);
            }
            return responseList;
        }
    }

    //------------- Tìm kiếm theo tài khoản khởi tạo thông số -----------------(ok)
    public List<QuanLyThongSoResponse> getByUpdateBy(String updateBy) {
        List<QuanLyThongSoEntity> entityList = this.quanLyThongSoRepository.getByUpdateBy(updateBy);
        if (entityList.isEmpty()) {
            return null;
        } else {
            log.info("create by:");
            List<QuanLyThongSoResponse> responseList = new ArrayList<>();
            for (QuanLyThongSoEntity entity : entityList) {
                QuanLyThongSoResponse response = new QuanLyThongSoResponse();
                response.setIdThongSo(entity.getIdThongSo());
                response.setMaThongSo(entity.getMaThongSo());
                response.setTenThongSo(entity.getTenThongSo());
                response.setNgayTao(entity.getNgayTao());
                response.setTimeUpdate(entity.getTimeUpdate());
                response.setUpdateBy(entity.getUpdateBy());
                response.setStatus(entity.getStatus());
                responseList.add(response);
            }
            return responseList;
        }
    }

    //------------- Tìm kiếm theo trạng thái (status) -----------------(ok)
    public List<QuanLyThongSoResponse> getByStatus(String status) {
        List<QuanLyThongSoEntity> entityList = this.quanLyThongSoRepository.getByStatus(status);
        if (entityList.isEmpty()) {
            return null;
        } else {
            log.info("status:");
            List<QuanLyThongSoResponse> responseList = new ArrayList<>();
            for (QuanLyThongSoEntity entity : entityList) {
                QuanLyThongSoResponse response = new QuanLyThongSoResponse();
                response.setIdThongSo(entity.getIdThongSo());
                response.setMaThongSo(entity.getMaThongSo());
                response.setTenThongSo(entity.getTenThongSo());
                response.setNgayTao(entity.getNgayTao());
                response.setTimeUpdate(entity.getTimeUpdate());
                response.setUpdateBy(entity.getUpdateBy());
                response.setStatus(entity.getStatus());
                responseList.add(response);
            }
            return responseList;
        }
    }

    //------------- Xoá 1 thông số theo mã thông số -----------------(ok)
    public String delByThongSo(String maThongSo) {
        QuanLyThongSoEntity entity = this.quanLyThongSoRepository.getByMaThongSo(maThongSo);
        if (entity == null) {
            return "Không tìm thấy thông số";
        } else {
            this.quanLyThongSoRepository.delete(entity);
            return "Xoá thành công !";
        }
    }

    //------------- Xoá nhiều thông số theo id thông số -----------------
    public String delThongSo(List<Integer> listIdThongSo) {
        log.info("id: "+listIdThongSo);
        this.quanLyThongSoRepository.deleteAllById(listIdThongSo);
        return "Xoá thành công!";
    }
}