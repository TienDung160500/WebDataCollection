package com.RangDong.RD_KhaiBaoThongSo.service;

import com.RangDong.RD_KhaiBaoThongSo.controller.request.QuanLyThongSoRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.ThietBiRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.ThongSoMayRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.request.UserPostRequest;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.QuanLyThongSoResponse;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.ResponseMessage;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.ThietBiResponse;
import com.RangDong.RD_KhaiBaoThongSo.repository.QuanLyThongSoRepository;
import com.RangDong.RD_KhaiBaoThongSo.repository.ThietBiRepository;
import com.RangDong.RD_KhaiBaoThongSo.repository.ThongSoMayRepository;
import com.RangDong.RD_KhaiBaoThongSo.repository.UserRepository;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.QuanLyThongSoEntity;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ThietBiEntity;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ThongSoMayEntity;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuanLyThongSoRepository quanLyThongSoRepository;
    @Autowired
    private ThietBiRepository thietBiRepository;
    @Autowired
    private ThongSoMayRepository thongSoMayRepository;

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
    //==================================================================================================================
    //----------------------- Template Quản lý thông số ----------------
    //------------------------- Hàm set giá trị cho từng thuộc tính -----------------------
    private static QuanLyThongSoResponse getQuanLyThongSoResponse(QuanLyThongSoEntity entity){
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

    //------------- Hiển thị danh sách thông số -----------------(ok)
    public List<QuanLyThongSoResponse> danhSachThongSo() {
        List<QuanLyThongSoEntity> entityList = this.quanLyThongSoRepository.findAll();
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSoEntity entity : entityList) {
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //------------- Tìm kiếm theo mã thông số -----------------(ok)
    public List<QuanLyThongSoResponse> getByMaThongSo(String maThongSo) {
        List<QuanLyThongSoEntity> entities = this.quanLyThongSoRepository.findAllByMaThongSo(maThongSo);
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()) {
            return null;
        } else {
            log.info("tim theo ma thong so");
            for (QuanLyThongSoEntity entity:entities) {
                QuanLyThongSoResponse response =getQuanLyThongSoResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }

    //------------- Tìm kiếm theo tên thông số -----------------(ok)
    public List<QuanLyThongSoResponse> getByTenThongSo(String tenThongSo) {
        List<QuanLyThongSoEntity> entities = this.quanLyThongSoRepository.getByTenThongSo(tenThongSo);
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()) {
            return null;
        } else {
            for (QuanLyThongSoEntity entity : entities) {
                QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
                responseList.add(response);
            }
            return responseList;
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
                QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
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
                QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
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
                QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
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
                QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }

    //------------- Xoá 1 thông số theo mã thông số -----------------(ok)
    public void delByThongSo(String maThongSo) {
        List<QuanLyThongSoEntity> entity = this.quanLyThongSoRepository.getByListMaThongSo(maThongSo);
        log.info("entity: "+entity);
        if (entity.isEmpty()) {
            log.info( "Không tìm thấy thông số");
        } else {
            this.quanLyThongSoRepository.deleteAll(entity);
            log.info("Xoá thành công !");
        }
    }
    //------------- them moi thong so -----------------------(ok)
    public String postThongSo(List<QuanLyThongSoRequest> requests){
        for(QuanLyThongSoRequest request: requests){
            QuanLyThongSoEntity entity = new QuanLyThongSoEntity();
            entity.setMaThongSo(request.getMaThongSo());
            entity.setTenThongSo(request.getTenThongSo());
            entity.setMoTa(request.getMoTa());
            entity.setNgayTao(request.getNgayTao());
            entity.setTimeUpdate(request.getTimeUpdate());
            entity.setUpdateBy(request.getUpdateBy());
            entity.setStatus("deactivate");
            this.quanLyThongSoRepository.save(entity);
        }
        log.info("Them moi thanh cong");
    return "Thêm mới thành công";
    }
    //----------------- cap nhat thong so ----------------(ok)
    public  String putThongSo(QuanLyThongSoRequest request, String maThongSo){
        QuanLyThongSoEntity entity = this.quanLyThongSoRepository.getByMaThongSo(maThongSo);
        entity.setMaThongSo(request.getMaThongSo());
        entity.setTenThongSo(request.getTenThongSo());
        entity.setMoTa(request.getMoTa());
        entity.setTimeUpdate(request.getTimeUpdate());
        entity.setUpdateBy(request.getUpdateBy());
        entity.setStatus(request.getStatus());
        this.quanLyThongSoRepository.save(entity);
        log.info("Cap nhat thanh cong !");
        return "Cap nhat thanh cong !";
    }

    //------------------------------- su kien tim kiem -----------------------------------
    public List<QuanLyThongSoResponse> timKiemThongSo(QuanLyThongSoRequest request){
        var entities = this.quanLyThongSoRepository.timKiemThongSo(
                request.getMaThongSo(), request.getTenThongSo(), request.getNgayTao(),request.getTimeUpdate(),
                request.getUpdateBy(), request.getStatus());
        log.info("Dang tim kiem  ----------"+ request);
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSoEntity entity : entities){
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            log.info("response:   ", response);
            responseList.add(response);
        }
        return responseList;
    }

    //==================================================================================================================
    //--------------------------------------------          Thiết bị            ----------------------------------------
    //--------------------------------------- Hàm set giá trị cho từng thuộc tính -----------------------
    private static ThietBiResponse getThietBiResponse(ThietBiEntity entity) {
        ThietBiResponse response = new ThietBiResponse();
        response.setIdThietBi(entity.getIdThietBi());
        response.setMaThietBi(entity.getMaThietBi());
        response.setLoaiThietBi(entity.getLoaiThietBi());
        response.setDayChuyen(entity.getDayChuyen());
        response.setStatus(entity.getStatus());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getTimeUpdate());
        response.setUpdateBy(entity.getUpdateBy());
        return response;
    }
    //------------------- Hiển thị danh sách thiết bị   -------------------------------(ok)
    public List<ThietBiResponse> danhSachThietBi(){
        List<ThietBiEntity> entities = this.thietBiRepository.findAll();
        List<ThietBiResponse> responseList = new ArrayList<>();
        for (ThietBiEntity entity: entities){
            ThietBiResponse response = getThietBiResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }
    //------------------------- Tìm kiếm theo mã thiết bị ------------------------------(ok)
    public List<ThietBiResponse> getThietBiByMaThietBi(String maThietBi){
        List<ThietBiEntity> entities = this.thietBiRepository.getByListMaThietBi(maThietBi);
        List<ThietBiResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()){
            return null;
        }else {
            for (ThietBiEntity entity: entities){
                ThietBiResponse response = getThietBiResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }
    //--------------------------Tìm kiếm theo loại thiết bị --------------------------------(ok)
    public List<ThietBiResponse> getThietBiByLoaiThietBi(String loaiThietBi){
        List<ThietBiEntity> entities = this.thietBiRepository.getByLoaiThietBi(loaiThietBi);
        List<ThietBiResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()){
            return null;
        }else {
            for (ThietBiEntity entity: entities){
                ThietBiResponse response = getThietBiResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }
    //------------------------- Tìm kiếm theo dây chuyền --------------------------------------(ok)
    public List<ThietBiResponse> getThietBiByDayChuyen(String dayChuyen){
        List<ThietBiEntity> entities = this.thietBiRepository.getByDayChuyen(dayChuyen);
        List<ThietBiResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()){
            return null;
        }else {
            for (ThietBiEntity entity: entities){
                ThietBiResponse response = getThietBiResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }
    //------------------------- Tìm kiếm theo ngày tạo -------------------------------------------(ok)
    public List<ThietBiResponse> getThietBiByNgayTao(String ngayTao){
        List<ThietBiEntity> entities = this.thietBiRepository.getByNgayTao(ngayTao);
        List<ThietBiResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()){
            return null;
        }else {
            for (ThietBiEntity entity: entities){
                ThietBiResponse response = getThietBiResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }
    //---------------------------- Tìm kiếm theo ngày cập nhật ----------------------------(ok)
    public List<ThietBiResponse> getThietBiByTimeUpdate(String timeUpdate){
        List<ThietBiEntity> entities = this.thietBiRepository.getByTimeUpdate(timeUpdate);
        List<ThietBiResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()){
            return null;
        }else {
            for (ThietBiEntity entity:entities){
                ThietBiResponse response = getThietBiResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }
    //---------------------------Tìm kiếm theo tài khoản ------------------------------------(ok)
    public List<ThietBiResponse> getThietBiByUpdateBy(String updateBy){
        List<ThietBiEntity> entities = this.thietBiRepository.getByUpdateBy(updateBy);
        List<ThietBiResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()){
            return null;
        }else {
            for (ThietBiEntity entity:entities){
                ThietBiResponse response = getThietBiResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }
    //--------------------------------Tìm kiếm theo status -------------------------------------(ok)
    public List<ThietBiResponse> getThietBiByStatus(String status){
        List<ThietBiEntity> entities = this.thietBiRepository.getByStatus(status);
        List<ThietBiResponse> responseList = new ArrayList<>();
        if (entities.isEmpty()){
            return null;
        }else {
            for (ThietBiEntity entity:entities){
                ThietBiResponse response = getThietBiResponse(entity);
                responseList.add(response);
            }
            return responseList;
        }
    }

    //----------------------- Chức năng thêm mới thiết bị -----------------------------------------------
    //-------- Lấy thông tin thông số từ table quản lý thông số -------------------------------
    //---------- Lấy thông tin loại thiết bị theo mã thiết bị từ table thiết bị --------------------
    // --------------------------- thêm mới thiết bị vào DB ----------------------------------(ok)
    public String postThietBi(ThietBiRequest request){
        log.info("them moi thiet bi");
        ThietBiEntity entity = new ThietBiEntity();
        entity.setMaThietBi(request.getMaThietBi());
        entity.setLoaiThietBi(request.getLoaiThietBi());
        entity.setDayChuyen(request.getDayChuyen());
        entity.setNgayTao(request.getNgayTao());
        entity.setTimeUpdate(request.getTimeUpdate());
        entity.setUpdateBy(entity.getUpdateBy());
        entity.setStatus(request.getStatus());
        this.thietBiRepository.save(entity);
        return "them moi thiet bi thanh cong !";
    }
    //---------------------------- thêm mới thông số thiết b vào DB --------------------------------
    public String postThongSoMay(List<ThongSoMayRequest> requestList){
        Integer row = 0;
        for (ThongSoMayRequest request:requestList){
            ThongSoMayEntity entity = new ThongSoMayEntity();
            entity.setIdThietBi(request.getIdThietBi());
            entity.setIdThongSo(request.getIdThongSo());
            entity.setMaThietBi(request.getMaThietBi());
            entity.setLoaiThietBi(request.getLoaiThietBi());
            entity.setRows(row+1);
            entity.setThongSo(request.getThongSo());
            entity.setMoTa(request.getMoTa());
            entity.setStatus(request.getStatus());
            entity.setPhanLoai(request.getPhanLoai());
            this.thongSoMayRepository.save(entity);
        }
        return "cai dat thong so thiet bi thanh cong !";
    }
    //---------------------------- del thông số thiết bị --------------------------------(chưa làm được)
    public void delByIdThongSoThietBi(Integer idThongSoThietBi){
        ThongSoMayEntity entity = this.thongSoMayRepository.findById(idThongSoThietBi).orElse(null);
        if (entity == null){
            String result = "khong tim thay thong so";
            log.info(result);
        }else {
            this.thongSoMayRepository.delete(entity);
            String result = "xoa thong so may thanh cong";
            log.info(result);
        }
    }
    //-------------------------- cập nhật thông số máy ------------------------ ( chưa làm được )
    //-------------------------- xem chi tiết thông số --------------------------(chưa làm được)
    public List<QuanLyThongSoResponse> getChiTietThongSoMay(String phanLoai){
        List<QuanLyThongSoEntity> entities = this.thongSoMayRepository.getChiTietThongSoMay(phanLoai);
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        log.info("hello");
        for (QuanLyThongSoEntity entity : entities){
            QuanLyThongSoResponse response = new QuanLyThongSoResponse();
            response.setTenThongSo(entity.getTenThongSo());
            response.setStatus(entity.getStatus());
            response.setNgayTao(entity.getNgayTao());
            response.setTimeUpdate(entity.getTimeUpdate());
            responseList.add(response);
        }
        return responseList;
    }
}