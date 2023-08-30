package com.RangDong.RD_KhaiBaoThongSo.controller;

import com.RangDong.RD_KhaiBaoThongSo.controller.request.*;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.*;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.KichBanEntity;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.SanXuatHangNgayEntity;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ThietBiEntity;
import com.RangDong.RD_KhaiBaoThongSo.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// ! chưa làm được
// ? Chưa test với front-end
// * Ngăn cách
// ☺ đã Test với front-end
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    //☺ Template login - Chức năng xác thực tài khoản
    @PostMapping("/login")
    public ResponseMessage getByName(@RequestBody UserPostRequest request) {
        ResponseMessage result = this.userService.loginAuth(request);
        return result;
    }
    //------------------------------------------------ * ---------------------------------------------------------------

    //---------------------------         Template Quản lý thông số      -----------------------------------------------

    //☺ Hiển thị danh sách thông số
    @GetMapping("/quan-ly-thong-so")
    public List<QuanLyThongSoResponse> danhSachThongSo() {
        List<QuanLyThongSoResponse> responseList = this.userService.danhSachThongSo();
        log.info("success");
        return responseList;
    }
// Note test
    @GetMapping("/test/{maThongSo}")
    public ThietBiResponse getByMaThongSo(@PathVariable Integer maThongSo){
        ThietBiResponse responseList = this.userService.getByMaThietBi(maThongSo);
        return responseList;
    }

    //☺ Xoá thông số theo mã thông số
    @DeleteMapping("/quan-ly-thong-so/del-by-ma-thong-so/{maThongSo}")
    public void delByMaThongSo(@PathVariable String maThongSo) {
        this.userService.delByThongSo(maThongSo);
    }
    //? Thêm mới thông số
    @PostMapping("/quan-ly-thong-so/them-moi-thong-so")
    public String postThongSo(@RequestBody List<QuanLyThongSoRequest> requests) {
        String result = this.userService.postThongSo(requests);
        return result;
    }
    //☺ xem chi tiet thong so
    @GetMapping("/quan-ly-thong-so/chi-tiet-thong-so/{maThongSo}")
    public List<QuanLyThongSoResponse> getChiTietThongSo(@PathVariable String maThongSo){
        List<QuanLyThongSoResponse> responseList = this.userService.getChiTietThongSo(maThongSo);
        return responseList;
    }

    //? cập nhật thông số
    @PutMapping("/quan-ly-thong-so/cap-nhat-thong-so/{maThongSo}")
    public String putThongSo(@PathVariable String maThongSo,
                             @RequestBody QuanLyThongSoRequest request) {
        String result = this.userService.putThongSo(request, maThongSo);
        return result;
    }
    //☺ su kien tim kiem
    @PostMapping("/quan-ly-thong-so/tim-kiem")
    public List<QuanLyThongSoResponse> timKiemThongSo(@RequestBody QuanLyThongSoRequest request){
        List<QuanLyThongSoResponse> responseList = this.userService.timKiemThongSo(request);
        return responseList;
    }
    //------------------------------------------------ * ---------------------------------------------------------------

    //--------------------------------             Thiết bị               ---------------------------------------------

    //☺ Hiển thị danh  sách thiết bị
    @GetMapping("/thiet-bi")
    public List<ThietBiResponse> danhSachThietBi() {
        List<ThietBiResponse> responseList = this.userService.danhSachThietBi();
        return responseList;
    }
    //☺ Tìm kiếm
    @PostMapping("/thiet-bi/tim-kiem")
    public List<ThietBiResponse> getThietBiByStatus(@RequestBody ThietBiRequest request) {
        List<ThietBiResponse> responseList = this.userService.timKiemThietBi(request);
        return responseList;
    }
    //----------------------- Chức năng thêm mới thiết bị -----------------------------------------------
    //? thêm mới thiết bị vào DB
    @PostMapping("/thiet-bi/them-moi-thiet-bi")
    public String postThietBi (@RequestBody ThietBiRequest request){
        String result = this.userService.postThietBi(request);
        return result;
    }
    //☺ del thông số thiết bị ->xoá luôn cả thông số thiết bị
    @DeleteMapping("/thiet-bi/del-thiet-bi/{maThietBi}")
    public void delThietBi(@PathVariable String maThietBi){
        this.userService.delThongSoMay(maThietBi);
    }

    //? thêm mới thông số thiết bị vào DB
    @PostMapping("/thiet-bi/them-moi-thong-so-thiet-bi")
    public String postThongSoMay(@RequestBody List<ThongSoMayRequest> requestList){
        String result = this.userService.postThongSoMay(requestList);
        return  result;
    }
    //? xem danh sách thông số thiết bị
    @GetMapping("/thiet-bi/danh-sach-thong-so-thiet-bi/{maThietBi}")
    public List<ThongSoMayResponse> getDanhSachThongSoMay(@PathVariable String maThietBi){
        List<ThongSoMayResponse> responseList = this.userService.getDanhSachThongSoThietBi(maThietBi);
        return responseList;
    }
    //? del thông số thiết bị
    @DeleteMapping("/thiet-bi/del-thong-so-may/{idThongSoThietBi}")
    public void delByIdThongSoThietBi(@PathVariable Integer idThongSoThietBi){
        this.userService.delByIdThongSoThietBi(idThongSoThietBi);
    }
    //?Cập nhật thông số máy
    @PutMapping("/thiet-bi/cap-nhat")
    public void putThongSoMay(@RequestBody List<ThongSoMayRequest> requestList){
        this.userService.putThongSoMay(requestList);
    }
    //?xem chi tiết thông số thiet bi
    @GetMapping("/thiet-bi/chi-tiet-thiet-bi/{idThietBi}")
    public ThietBiResponse getAllByMaThongSo(@PathVariable Integer idThietBi){
        ThietBiResponse responseList = this.userService.getAllByIdThietBi(idThietBi);
        return responseList;
    }
    //------------------------------------------------ * ---------------------------------------------------------------

    //---------------------------------------              Kich ban                ------------------------------------

    //☺ Hien thi danh sach kich ban
    @GetMapping("/kich-ban")
    public List<KichBanResponse> getAllKichBan(){
        List<KichBanResponse> responseList = this.userService.getDanhSachKichBan();
        return responseList;
    }
    //☺ Tim kiem kich ban
    @PostMapping("/kich-ban/tim-kiem")
    public List<KichBanResponse> timKiemKichBan (@RequestBody KichBanRequest request){
        List<KichBanResponse> responseList = this.userService.timKiemKichBan(request);
        return responseList;
    }
    //? Them moi kich ban
    @PostMapping("/kich-ban/them-moi-kich-ban")
    public String postKichBan(@RequestBody KichBanRequest request){
        String result = this.userService.postKichBan(request);
        return result;
    }
    //?Them moi thong tin thong so kich ban
    @PostMapping("/kich-ban/them-moi-thong-so-kich-ban")
    public String postChiTietKichBan(@RequestBody List<ChiTietKichBanRequest> requestList){
        String result = this.userService.postChiTietKichBan(requestList);
        return result;
    }
    //?Xem danh sach thong so kich ban
    @GetMapping("/kich-ban/thong-so-kich-ban/{maKichBan}")
    public List<ChiTietKichBanResponse> getAllByMaKichBan(@PathVariable String maKichBan){
        List<ChiTietKichBanResponse> responseList = this.userService.getAllByMaKichBan(maKichBan);
        return responseList;
    }
    //? cap nhat thong so kich ban
    @PutMapping("/kich-ban/cap-nhat-thong-so-kich-ban")
    public String putChiTietKichBan(@RequestBody List<ChiTietKichBanRequest> requestList){
        String result = this.userService.putChiTietKichBan(requestList);
        return result;
    }
    //☺ xoa kich ban
    @DeleteMapping("/kich-ban/del-kich-ban/{maKichBan}")
    public void delKichBan (@PathVariable String maKichBan){
        this.userService.delKichBan(maKichBan);
    }
    //? xoa thong so trong kich ban
    @DeleteMapping("/kich-ban/del-thong-so-kich-ban/{idChiTietKichBan}")
    public  void delByIdChiTietKichBan(@PathVariable Integer idChiTietKichBan){
        this.userService.delByIdChiTietKichBan(idChiTietKichBan);
    }
    //?  xem chi tiet kich ban
    @GetMapping("/kich-ban/chi-tiet-kich-ban/{maKichBan}")
    public KichBanResponse chiTietKichBan(@PathVariable String maKichBan){
        KichBanResponse responseList = this.userService.chiTietKichBan(maKichBan);
        return responseList;
    }
    //-------------------------------------------------- * -------------------------------------------------------------

    //---------------------------                San xuat hang ngay              ---------------------------------------

    // ? Hien thi danh sach san xuat hang ngay
    @GetMapping("/san-xuat-hang-ngay")
    public List<SanXuatHangNgayResponse> getAllSanXuatHangNgay (){
        List<SanXuatHangNgayResponse> responseList = this.userService.getAllSanXuatHangNgay();
        return responseList;
    }
    //? Tim kiem noi dung san xuat hang ngay (ok)
    @PostMapping("/san-xuat-hang-ngay/tim-kiem")
    public List<SanXuatHangNgayResponse> timKiemSanXuatHangNgay (@RequestBody SanXuatHangNgayRequest request){
        List<SanXuatHangNgayResponse> responseList = this.userService.timKiemSanxuatHangNgay(request);
        return responseList;
    }
    // ? them moi kich ban san xuat
    @PostMapping("/san-xuat-hang-ngay/them-moi-kich-ban")
    public String postSanXuatHangNgay(@RequestBody SanXuatHangNgayRequest request){
        String result = this.userService.postSanXuatHangNgay(request);
        return  result;
    }
    //?Xem danh sach thong so san xuat hang ngay
    @GetMapping("/san-xuat-hang-ngay/chi-tiet-san-xuat/{maKichBan}")
    public List<ChiTietSanXuatResponse> getAllsByMaKichBan(@PathVariable String maKichBan){
        List<ChiTietSanXuatResponse> responseList = this.userService.getAllsByMaKichBan(maKichBan);
        return responseList;
    }
    // ?Chinh sua noi dung san xuat hang ngay (1)
    @PutMapping("/san-xuat-hang-ngay/cap-nhat")
    public String putChiTietSanXuat(@RequestBody List<ChiTietSanXuatRequest> requestList){
        String result = this.userService.putChiTietSanXuat(requestList);
        return result;
    }
    // ?(1)xoa thong so trong noi dung san xuat hang ngay
    @DeleteMapping("/san-xuat-hang-ngay/del-thong-so/{idChiTietSanXuat}")
    public void delByIdChiTietSanXuat(@PathVariable Integer idChiTietSanXuat){
        this.userService.delByIdChiTietSanXuat(idChiTietSanXuat);
    }
    // ?xem chi tiet noi dung 1 kich ban san xuat hang ngay
    @GetMapping("/san-xuat-hang-ngay/chi-tiet/{maKichBan}")
    public SanXuatHangNgayResponse chiTietSanXuat(@PathVariable String maKichBan){
        SanXuatHangNgayResponse response = this.userService.chiTietSanXuat(maKichBan);
        return response;
    }
}
