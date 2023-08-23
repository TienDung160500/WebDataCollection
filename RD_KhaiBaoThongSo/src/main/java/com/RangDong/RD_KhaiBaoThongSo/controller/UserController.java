package com.RangDong.RD_KhaiBaoThongSo.controller;

import com.RangDong.RD_KhaiBaoThongSo.controller.request.QuanLyThongSoRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.ThietBiRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.ThongSoMayRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.UserPostRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.QuanLyThongSoResponse;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.ResponseMessage;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.ThietBiResponse;
import com.RangDong.RD_KhaiBaoThongSo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    //----------------------- Template login - Chức năng xác thực tài khoản------(ok)
    @PostMapping("/login")
    public ResponseMessage getByName(@RequestBody UserPostRequest request) {
        ResponseMessage result = this.userService.loginAuth(request);
        return result;
    }

    //==================================================================================================================
    //----------------------------------                              --------------------------------------------------
    //---------------------------         Template Quản lý thông số      -----------------------------------------------
    //--------------------------------------                        ----------------------------------------------------
    //------------ Hiển thị danh sách thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so")
    public List<QuanLyThongSoResponse> danhSachThongSo() {
        List<QuanLyThongSoResponse> responseList = this.userService.danhSachThongSo();
        log.info("success");
        return responseList;
    }

    //------------- Tìm kiếm theo mã thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so/ma-thong-so/{maThongSo}")
    public List<QuanLyThongSoResponse> getByMaThongSo(@PathVariable String maThongSo) {
        List<QuanLyThongSoResponse> response = this.userService.getByMaThongSo(maThongSo);
        return response;
    }

    //------------- Tìm kiếm theo tên thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so/ten-thong-so/{tenThongSo}")
    public List<QuanLyThongSoResponse> getByTenThongSo(@PathVariable String tenThongSo) {
        List<QuanLyThongSoResponse> response = this.userService.getByTenThongSo(tenThongSo);
        return response;
    }

    //------------- Tìm kiếm theo ngày tạo -----------------(ok)
    @GetMapping("/quan-ly-thong-so/ngay-tao/{ngayTao}")
    public List<QuanLyThongSoResponse> getByNgayTao(@PathVariable String ngayTao) {
        List<QuanLyThongSoResponse> responseList = this.userService.getByNgayTao(ngayTao);
        return responseList;
    }

    //------------- Tìm kiếm theo ngày cập nhật -----------------(ok)
    @GetMapping("/quan-ly-thong-so/time-update/{timeUpdate}")
    public List<QuanLyThongSoResponse> getByTimeUpdate(
            @PathVariable String timeUpdate) {
        List<QuanLyThongSoResponse> responseList = this.userService.getByTimeUpdate(timeUpdate);
        return responseList;
    }

    //------------- Tìm kiếm theo tài khoản khởi tạo thông số -----------------(ok)
    @GetMapping("/quan-ly-thong-so/update-by/{updateBy}")
    public List<QuanLyThongSoResponse> getByUpdateBy(@PathVariable String updateBy) {
        List<QuanLyThongSoResponse> responseList = this.userService.getByUpdateBy(updateBy);
        return responseList;
    }

    //------------- Tìm kiếm theo trạng thái (status) -----------------(ok)
    @GetMapping("/quan-ly-thong-so/status/{status}")
    public List<QuanLyThongSoResponse> getByStatus(@PathVariable String status) {
        List<QuanLyThongSoResponse> responseList = this.userService.getByStatus(status);
        return responseList;
    }

    //------------- Xoá thông số theo mã thông số -----------------
    @DeleteMapping("/quan-ly-thong-so/del-by-ma-thong-so/{maThongSo}")
    public void delByMaThongSo(@PathVariable String maThongSo) {
        this.userService.delByThongSo(maThongSo);
    }

    //------------- Xoá nhiều thông số theo id thông số -----------------(chua lam duoc)
    //------------------------ Thêm mới thông số ------------------(ok)
    @PostMapping("/quan-ly-thong-so/them-moi-thong-so")
    public String postThongSo(@RequestBody List<QuanLyThongSoRequest> requests) {
        String result = this.userService.postThongSo(requests);
        return result;
    }

    //----------------------- cập nhật thông số ------------------------(ok)
    @PutMapping("/quan-ly-thong-so/cap-nhat-thong-so/{maThongSo}")
    public String putThongSo(@PathVariable String maThongSo,
                             @RequestBody QuanLyThongSoRequest request) {
        String result = this.userService.putThongSo(request, maThongSo);
        return result;
    }

    //=================================================================================================================
    //----------------------------------------                   ------------------------------------------------------
    //--------------------------------             Thiết bị               ---------------------------------------------
    //-----------------------------------------                  ------------------------------------------------------
    //------------------------- Hiển thị danh  sách thiết bị ------------------------------(ok)
    @GetMapping("/thiet-bi")
    public List<ThietBiResponse> danhSachThietBi() {
        List<ThietBiResponse> responseList = this.userService.danhSachThietBi();
        return responseList;
    }
    //------------------------- Tìm kiếm theo mã thiết bị ------------------------------(ok)
    @GetMapping("/thiet-bi/ma-thiet-bi/{maThietBi}")
    public List<ThietBiResponse> getThietBiByMaThietBi(@PathVariable String maThietBi) {
        List<ThietBiResponse> responseList = this.userService.getThietBiByMaThietBi(maThietBi);
        return responseList;
    }
    //------------------------- Tìm kiếm theo loại thiết bị ------------------------------(ok)
    @GetMapping("/thiet-bi/loai-thiet-bi/{loaiThietBi}")
    public List<ThietBiResponse> getThietBiByLoaiThietBi(@PathVariable String loaiThietBi) {
        List<ThietBiResponse> responseList = this.userService.getThietBiByLoaiThietBi(loaiThietBi);
        log.info("loai-Thiet-bi");
        return responseList;
    }
    //------------------------- Tìm kiếm theo dây chuyền ------------------------------(ok)
    @GetMapping("/thiet-bi/day-chuyen/{dayChuyen}")
    public List<ThietBiResponse> getThietBiByDayChuyen(@PathVariable String dayChuyen) {
        List<ThietBiResponse> responseList = this.userService.getThietBiByDayChuyen(dayChuyen);
        return responseList;
    }
    //------------------------- Tìm kiếm theo ngày tạo ------------------------------(ok)
    @GetMapping("/thiet-bi/ngay-tao/{ngayTao}")
    public List<ThietBiResponse> getThietBiNgayTao(@PathVariable String ngayTao) {
        List<ThietBiResponse> responseList = this.userService.getThietBiByNgayTao(ngayTao);
        log.info("ngay-tao");
        return responseList;
    }
    //------------------------- Tìm kiếm theo ngày cập nhật ------------------------------(ok)
    @GetMapping("/thiet-bi/time-update/{timeUpdate}")
    public List<ThietBiResponse> getThietBiTimeUpdate(@PathVariable String timeUpdate) {
        List<ThietBiResponse> responseList = this.userService.getThietBiByTimeUpdate(timeUpdate);
        log.info("ngay-cap-nhat");
        return responseList;
    }
    //------------------------- Tìm kiếm theo người tạo ------------------------------(ok)
    @GetMapping("/thiet-bi/update-by/{updateBy}")
    public List<ThietBiResponse> getThietBiByUpdateBy(@PathVariable String updateBy) {
        List<ThietBiResponse> responseList = this.userService.getThietBiByUpdateBy(updateBy);
        log.info("user");
        return responseList;
    }
    //------------------------- Tìm kiếm theo status ------------------------------(ok)
    @GetMapping("/thiet-bi/status/{status}")
    public List<ThietBiResponse> getThietBiByStatus(@PathVariable String status) {
        List<ThietBiResponse> responseList = this.userService.getThietBiByStatus(status);
        log.info("user");
        return responseList;
    }
    //----------------------------        Thêm mới, cập nhật thông số thiết bị         ---------------------------------
    //---------------------- Lấy danh sách tên thông số -----------------------------------------(ok)
    // --------------------------- thêm mới thiết bị vào DB ----------------------------------(ok)
    @PostMapping("/thiet-bi/them-moi-thiet-bi")
    public String postThietBi (@RequestBody ThietBiRequest request){
        String result = this.userService.postThietBi(request);
        return result;
    }
    //---------------------------- thêm mới thông số thiết b vào DB --------------------------------
    @PostMapping("/thiet-bi/them-moi-thong-so-thiet-bi")
    public String postThongSoMay(@RequestBody List<ThongSoMayRequest> requestList){
        String result = this.userService.postThongSoMay(requestList);
        return  result;
    }

}
