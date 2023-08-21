package com.RangDong.RD_KhaiBaoThongSo.controller;

import com.RangDong.RD_KhaiBaoThongSo.controller.request.UserPostRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.UserPutRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.QuanLyThongSoResponse;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.ResponseMessage;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.UserResponse;
import com.RangDong.RD_KhaiBaoThongSo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    //----------------------- Template login - Chức năng xác thực tài khoản------(ok)
    @PostMapping("/login")
    public ResponseMessage getByName(@RequestBody UserPostRequest request){
        ResponseMessage result = this.userService.loginAuth(request);
        return result;
    }
    //----------------------- Template Quản lý thông số ----------------
    //------------- Hiển thị danh sách thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so")
    public List<QuanLyThongSoResponse> danhSachThongSo(){
        List<QuanLyThongSoResponse> responseList = this.userService.danhSachThongSo();
        log.info("success");
        return responseList;
    }
    //------------- Tìm kiếm theo mã thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so/ma-thong-so/{maThongSo}")
    public QuanLyThongSoResponse getByMaThongSo(@PathVariable String maThongSo){
        QuanLyThongSoResponse response = this.userService.getByMaThongSo(maThongSo);
        return response;
    }
    //------------- Tìm kiếm theo tên thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so/ten-thong-so/{tenThongSo}")
    public QuanLyThongSoResponse getByTenThongSo(@PathVariable String tenThongSo){
        QuanLyThongSoResponse response = this.userService.getByTenThongSo(tenThongSo);
        return response;
    }
    //------------- Tìm kiếm theo ngày tạo -----------------(ok)
    @GetMapping("/quan-ly-thong-so/ngay-tao/{ngayTao}")
    public List<QuanLyThongSoResponse> getByNgayTao(@PathVariable String ngayTao){
        List<QuanLyThongSoResponse> responseList = this.userService.getByNgayTao(ngayTao);
        return responseList;
    }
    //------------- Tìm kiếm theo ngày cập nhật -----------------(ok)
    @GetMapping("/quan-ly-thong-so/time-update/{timeUpdate}")
    public List<QuanLyThongSoResponse> getByTimeUpdate(
            @PathVariable String timeUpdate){
        List<QuanLyThongSoResponse> responseList = this.userService.getByTimeUpdate(timeUpdate);
        return responseList;
    }
    //------------- Tìm kiếm theo tài khoản khởi tạo thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so/update-by/{updateBy}")
    public List<QuanLyThongSoResponse> getByUpdateBy(@PathVariable String updateBy){
        List<QuanLyThongSoResponse> responseList = this.userService.getByUpdateBy(updateBy);
        return responseList;
    }
    //------------- Tìm kiếm theo trạng thái (status) -----------------(ok)
    @GetMapping("/quan-ly-thong-so/status/{status}")
    public List<QuanLyThongSoResponse> getByStatus (@PathVariable String status){
        List<QuanLyThongSoResponse> responseList = this.userService.getByStatus(status);
        return responseList;
    }
    //------------- Xoá thông số theo mã thông số -----------------
    @DeleteMapping("/quan-ly-thong-so/del-by-ma-thong-so/{maThongSo}")
    public String delByMaThongSo(@PathVariable String maThongSo){
        String result = this.userService.delByThongSo(maThongSo);
        return result;
    }
    //------------- Xoá nhiều thông số theo id thông số -----------------
    @DeleteMapping("/quan-ly-thong-so/del-thong-so")
    public String delThongSo(@RequestBody List<Integer> idThongSo){
        log.info("id: "+idThongSo);
        String result = this.userService.delThongSo(idThongSo);
        return result;
    }
}
