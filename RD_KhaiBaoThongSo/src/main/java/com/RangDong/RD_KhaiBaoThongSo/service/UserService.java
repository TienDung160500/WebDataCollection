package com.RangDong.RD_KhaiBaoThongSo.service;

import com.RangDong.RD_KhaiBaoThongSo.controller.request.*;
import com.RangDong.RD_KhaiBaoThongSo.controller.response.*;
import com.RangDong.RD_KhaiBaoThongSo.repository.*;
import com.RangDong.RD_KhaiBaoThongSo.repository.entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// ! chưa làm được
// ? Chưa test với front-end
// * Ngăn cách
// ☺ đã Test với front-end
@Service
@Slf4j
@Deprecated
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuanLyThongSoRepository quanLyThongSoRepository;
    @Autowired
    private ThietBiRepository thietBiRepository;
    @Autowired
    private ThongSoMayRepository thongSoMayRepository;
    @Autowired
    private KichBanRepository kichBanRepository;
    @Autowired
    private ChiTietKichBanRepository chiTietKichBanRepository;
    @Autowired
    private SanXuatHangNgayRepository sanXuatHangNgayRepository;
    @Autowired
    private ChiTietSanXuatRepository chiTietSanXuatRepository;

    //☺ Template login - Chức năng xác thực tài khoản
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
    //------------------------------------------- * --------------------------------------------------------------------

    //-----------------------                  Template Quản lý thông số                       -------- ----------------

    //☺ Hàm set giá trị cho từng thuộc tính
    private static QuanLyThongSoResponse getQuanLyThongSoResponse(QuanLyThongSoEntity entity) {
        QuanLyThongSoResponse response = new QuanLyThongSoResponse();
        response.setIdThongSo(entity.getIdThongSo());
        response.setMaThongSo(entity.getMaThongSo());
        response.setTenThongSo(entity.getTenThongSo());
        response.setMoTa(entity.getMoTa());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getTimeUpdate());
        response.setUpdateBy(entity.getUpdateBy());
        response.setStatus(entity.getStatus());
        return response;
    }

    //☺ Hiển thị danh sách thông số
    public List<QuanLyThongSoResponse> danhSachThongSo() {
        List<QuanLyThongSoEntity> entityList = this.quanLyThongSoRepository.findAll();
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSoEntity entity : entityList) {
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //☺ Xoá 1 thông số theo mã thông số
    public void delByThongSo(String maThongSo) {
        List<QuanLyThongSoEntity> entity = this.quanLyThongSoRepository.findAllByMaThongSo(maThongSo);
        log.info("entity: " + entity);
        if (entity.isEmpty()) {
            log.info("Không tìm thấy thông số");
        } else {
            this.quanLyThongSoRepository.deleteAll(entity);
            log.info("Xoá thành công !");
        }
    }

    //? them moi thong so
    public String postThongSo(List<QuanLyThongSoRequest> requests) {
        for (QuanLyThongSoRequest request : requests) {
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
    //☺ xem chi tiet thong so
    public List<QuanLyThongSoResponse> getChiTietThongSo(String maThongSo){
        List<QuanLyThongSoEntity> entities = this.quanLyThongSoRepository.findAllByMaThongSo(maThongSo);
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSoEntity entity : entities){
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }
    //? cap nhat thong so
    public String putThongSo(QuanLyThongSoRequest request, String maThongSo) {
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

    //☺ su kien tim kiem
    public List<QuanLyThongSoResponse> timKiemThongSo(QuanLyThongSoRequest request) {
        var entities = this.quanLyThongSoRepository.timKiemThongSo(
                request.getMaThongSo(), request.getTenThongSo(), request.getNgayTao(), request.getTimeUpdate(),
                request.getUpdateBy(), request.getStatus());
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSoEntity entity : entities) {
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            log.info("response:   ", response);
            responseList.add(response);
        }
        return responseList;
    }

    //---------------------------------------------- * -----------------------------------------------------------------
    //--------------------------------------------          Thiết bị            ----------------------------------------
    //☺ Hàm set giá trị cho từng thuộc tính
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

    //☺ Hiển thị danh sách thiết bị
    public List<ThietBiResponse> danhSachThietBi() {
        List<ThietBiEntity> entities = this.thietBiRepository.findAll();
        List<ThietBiResponse> responseList = new ArrayList<>();
        for (ThietBiEntity entity : entities) {
            ThietBiResponse response = getThietBiResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    // Note ( Function test )
    public List<QuanLyThongSoResponse> getByMaThongSo(String maThongSo) {
        List<QuanLyThongSoEntity> entities = this.quanLyThongSoRepository.findAllByMaThongSo(maThongSo);
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSoEntity entity : entities) {
            QuanLyThongSoResponse response = new QuanLyThongSoResponse();
            response.setMaThongSo(entity.getMaThongSo());
            response.setTenThongSo(entity.getTenThongSo());
            responseList.add(response);
        }
        return responseList;
    }

    //☺ Tìm kiếm
    public List<ThietBiResponse> timKiemThietBi(ThietBiRequest request) {
        List<ThietBiEntity> entities = this.thietBiRepository.timKiemThietBi(request.getMaThietBi(), request.getLoaiThietBi(),
                request.getDayChuyen(), request.getNgayTao(), request.getTimeUpdate(), request.getUpdateBy(), request.getStatus());
        List<ThietBiResponse> responseList = new ArrayList<>();
        log.info("request: " + request);
        for (ThietBiEntity entity : entities) {
            ThietBiResponse response = getThietBiResponse(entity);
            log.info("tim kiem thiet bi: ", response);
            responseList.add(response);
        }
        return responseList;
    }
    //☺ del thiết bị -> xoá luôn cả thông số thiết bị
    public void delThongSoMay(String maThietBi){
        List<ThietBiEntity> entities = this.thietBiRepository.getByListMaThietBi(maThietBi);
        if(entities.isEmpty()){
            log.info("khong tim thay thiet bi");
        }else {
            List<ThongSoMayEntity> entityList = this.thongSoMayRepository.findAllByMaThietBi(maThietBi);
            this.thongSoMayRepository.deleteAll(entityList);
            this.thietBiRepository.deleteAll(entities);
            log.info("xoa thanh cong");
        }
    }

    //----------------------- Chức năng thêm mới thiết bị -----------------------------------------------

    //? Lấy thông tin loại thiết bị theo mã thiết bị từ table thiết bị
        // ? thêm mới thiết bị vào DB
    public String postThietBi(ThietBiRequest request) {
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

        //? thêm mới thông số thiết b vào DB
    public String postThongSoMay(List<ThongSoMayRequest> requestList) {
        Integer row = 0;
        for (ThongSoMayRequest request : requestList) {
            ThongSoMayEntity entity = new ThongSoMayEntity();
            entity.setIdThietBi(request.getIdThietBi());
            entity.setIdThongSo(request.getIdThongSo());
            entity.setMaThietBi(request.getMaThietBi());
            entity.setLoaiThietBi(request.getLoaiThietBi());
            entity.setRows(row + 1);
            entity.setThongSo(request.getThongSo());
            entity.setMoTa(request.getMoTa());
            entity.setStatus(request.getStatus());
            entity.setPhanLoai(request.getPhanLoai());
            this.thongSoMayRepository.save(entity);
        }
        return "cai dat thong so thiet bi thanh cong !";
    }
    //----------------------- Chức năng cập nhật thông số thiết bị -----------------------------------------------
    //? xem danh sách thông số thiết bị
    public List<ThongSoMayResponse> getDanhSachThongSoThietBi(String maThietBi){
        List<ThongSoMayEntity> entities = this.thongSoMayRepository.findAllByMaThietBi(maThietBi);
        List<ThongSoMayResponse> responseList = new ArrayList<>();
        for (ThongSoMayEntity entity:entities){
            ThongSoMayResponse response = new ThongSoMayResponse();
            response.setIdThongSoThietBi(entity.getIdThongSoThietBi());
            response.setThongSo(entity.getThongSo());
            response.setMo_ta(entity.getMoTa());
            response.setPhanLoai(entity.getPhanLoai());
            response.setStatus(entity.getStatus());
            responseList.add(response);
        }
        return responseList;
    }
    //? del thông số thiết bị
    public void delByIdThongSoThietBi(Integer idThongSoThietBi) {
        ThongSoMayEntity entity = this.thongSoMayRepository.findById(idThongSoThietBi).orElse(null);
        if (entity == null) {
            String result = "khong tim thay thong so";
            log.info(result);
        } else {
            this.thongSoMayRepository.delete(entity);
            String result = "xoa thong so may thanh cong";
            log.info(result);
        }
    }
    //? cập nhật thông số máy trong khi xem danh sách thông số máy
    public void putThongSoMay(List<ThongSoMayRequest> requestList){
        // tìm kiếm thông tin thông số theo id_thong_so_thiet_bi
        for (ThongSoMayRequest request: requestList){
            ThongSoMayEntity entity = this.thongSoMayRepository.findAllByIdThongSoThietBi(request.getIdThongSoThietBi());
            entity.setIdThongSo(request.getIdThongSo());
            entity.setThongSo(request.getThongSo());
            entity.setMoTa(request.getMoTa());
            entity.setPhanLoai(request.getPhanLoai());
            entity.setStatus(request.getStatus());
            this.thongSoMayRepository.save(entity);
        }
    }
    //! xem chi tiết thông số thiet bi
    //------------------------------------------------ * ---------------------------------------------------------------

    //---------------------------------------              Kịch bản                ------------------------------------

    //☺ Hàm set giá trị cho từng thuộc tính
    private static KichBanResponse getKichBanResponse(KichBanEntity entity) {
        KichBanResponse response = new KichBanResponse();
        response.setIdKichBan(entity.getIdKichBan());
        response.setMaKichBan(entity.getMaKichBan());
        response.setMaThietBi(entity.getMaThietBi());
        response.setLoaiThietBi(entity.getLoaiThietBi());
        response.setDayChuyen(entity.getDayChuyen());
        response.setMaSanPham(entity.getMaSanPham());
        response.setVersionSanPham(entity.getVersionSanPham());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getTimeUpdate());
        response.setUpdateBy(entity.getUpdateBy());
        response.setStatus(entity.getStatus());
        return response;
    }

    //☺ Hien thi danh sach kich ban
    public List<KichBanResponse> getDanhSachKichBan() {
        List<KichBanEntity> entities = this.kichBanRepository.findAll();
        List<KichBanResponse> responseList = new ArrayList<>();
        for (KichBanEntity entity : entities) {
            KichBanResponse response = getKichBanResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //☺ Tim kiem kich ban
    public List<KichBanResponse> timKiemKichBan(KichBanRequest request) {
        List<KichBanEntity> entities = this.kichBanRepository.timKiemKichBan(request.getMaKichBan(), request.getMaThietBi(),
                request.getLoaiThietBi(), request.getDayChuyen(), request.getMaSanPham(), request.getVersionSanPham(),
                request.getNgayTao(), request.getTimeUpdate(), request.getUpdateBy(), request.getStatus());
        log.info("" + request);
        List<KichBanResponse> responseList = new ArrayList<>();
        for (KichBanEntity entity : entities) {
            log.info("thanh cong !");
            KichBanResponse response = getKichBanResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }
    //? Them moi kich ban
            //? B1: Thêm mới kịch bản
    public String postKichBan(KichBanRequest request){
        log.info("Them moi kich ban");
        KichBanEntity entity = new KichBanEntity();
        entity.setMaKichBan(request.getMaKichBan());
        entity.setMaThietBi(request.getMaThietBi());
        entity.setLoaiThietBi(request.getLoaiThietBi());
        entity.setDayChuyen(request.getDayChuyen());
        entity.setMaSanPham(request.getMaSanPham());
        entity.setVersionSanPham(request.getVersionSanPham());
        entity.setNgayTao(request.getNgayTao());
        entity.setTimeUpdate(request.getTimeUpdate());
        entity.setUpdateBy(request.getUpdateBy());
        entity.setStatus(request.getStatus());
        this.kichBanRepository.save(entity);
        return "Them moi kich ban thanh cong";
    }
        //? B2: Thêm mới thông tin thông số kịch bản
public String postChiTietKichBan(List<ChiTietKichBanRequest> requests){
        for (ChiTietKichBanRequest request:requests){
            ChiTietKichBanEntity entity = new ChiTietKichBanEntity();
            entity.setIdKichBan(request.getIdKichBan());
            entity.setMaKichBan(request.getMaKichBan());
            entity.setRows(request.getRows());
            entity.setThongSo(request.getThongSo());
            entity.setMinValue(request.getMinValue());
            entity.setMaxValue(request.getMaxValue());
            entity.setTrungBinh(request.getTrungBinh());
            entity.setDonVi(request.getDonVi());
            entity.setPhanLoai(request.getPhanLoai());
            this.chiTietKichBanRepository.save(entity);
        }
        return "Them moi chi tiet kich ban thanh cong";
}
    //? xem danh sach thong so kich ban
    public List<ChiTietKichBanResponse> getAllByMaKichBan(String maKichBan){
        List<ChiTietKichBanEntity> entities = this.chiTietKichBanRepository.findAllByMaKichBan(maKichBan);
        List<ChiTietKichBanResponse> responseList = new ArrayList<>();
        log.info("xem danh sach kich ban");
        for (ChiTietKichBanEntity entity:entities){
            ChiTietKichBanResponse response = new ChiTietKichBanResponse();
            response.setIdChiTietKichBan(entity.getIdChiTietKichBan());
            response.setMaKichBan(entity.getMaKichBan());
            response.setRows(entity.getRows());
            response.setThongSo(entity.getThongSo());
            response.setMinValue(entity.getMinValue());
            response.setMaxValue(entity.getMaxValue());
            response.setTrungBinh(entity.getTrungBinh());
            response.setDonVi(entity.getDonVi());
            response.setPhanLoai(entity.getPhanLoai());
            responseList.add(response);
        }
        return responseList;
    }
    //? cap nhat thong so kich ban
    public String putChiTietKichBan(List<ChiTietKichBanRequest> requestList){
        for (ChiTietKichBanRequest request:requestList){
            ChiTietKichBanEntity entity = this.chiTietKichBanRepository.findAllByIdChiTietKichBan(request.getIdChiTietKichBan());
            entity.setThongSo(request.getThongSo());
            entity.setMinValue(request.getMinValue());
            entity.setMaxValue(request.getMaxValue());
            entity.setTrungBinh(request.getTrungBinh());
            entity.setDonVi(request.getDonVi());
            this.chiTietKichBanRepository.save(entity);
        }
        return "Cap nhat thong so kich ban thanh cong";
    }
    // ☺ xoa kich ban
    public void delKichBan(String maKichBan){
        List<KichBanEntity> entities = this.kichBanRepository.findAllByMaKichBan(maKichBan);
        if (entities.isEmpty()){
            log.info("khong tim thay kich ban");
        }else {
            // tim kiem thong tin chi tiet kich ban
            List<ChiTietKichBanEntity> entityList = this.chiTietKichBanRepository.findAllByMaKichBan(maKichBan);
            this.chiTietKichBanRepository.deleteAll(entityList);
            this.kichBanRepository.deleteAll(entities);
            log.info("xoa kich ban thanh cong");
        }
    }
    //? xoa thong so trong kich ban
    public void delByIdChiTietKichBan(Integer idChiTietKichBan){
        ChiTietKichBanEntity entities = this.chiTietKichBanRepository.findAllByIdChiTietKichBan(idChiTietKichBan);
        if (entities == null){
            log.info("khong tim thay thong so");
        }else {
            this.chiTietKichBanRepository.delete(entities);
            log.info("xoa thong so kich ban thanh cong");
        }
    }
    // ! xem chi tiet kich ban
    //----------------------------------------- * ----------------------------------------------------------------------
    //---------------------------                    San xuat hang ngay          ---------------------------------------
    // ☺ Ham set gia tri cho tung thuoc tinh
    public SanXuatHangNgayResponse getSanXuatHangNgayResponse(SanXuatHangNgayEntity entity) {
        SanXuatHangNgayResponse response = new SanXuatHangNgayResponse();
        response.setIdSanXuatHangNgay(entity.getIdSanXuatHangNgay());
        response.setMaKichBan(entity.getMaKichBan());
        response.setMaThietBi(entity.getMaThietBi());
        response.setLoaiThietBi(entity.getLoaiThietBi());
        response.setDayChuyen(entity.getDayChuyen());
        response.setMaSanPham(entity.getMaSanPham());
        response.setVersionSanPham(entity.getVersionSanPham());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getTimeUpdate());
        response.setStatus(entity.getStatus());
        return response;
    }

    // ? Hien thi danh sach san xuat hang ngay
    public List<SanXuatHangNgayResponse> getAllSanXuatHangNgay() {
        List<SanXuatHangNgayEntity> entities = this.sanXuatHangNgayRepository.findAll();
        List<SanXuatHangNgayResponse> responseList = new ArrayList<>();
        for (SanXuatHangNgayEntity entity : entities) {
            SanXuatHangNgayResponse response = getSanXuatHangNgayResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //? Tim kiem noi dung san xuat hang ngay
    public List<SanXuatHangNgayResponse> timKiemSanxuatHangNgay(SanXuatHangNgayRequest request) {
        List<SanXuatHangNgayEntity> entities = this.sanXuatHangNgayRepository.timKiemSanXuatHangNgay(request.getMaKichBan(), request.getMaThietBi(),
                request.getLoaiThietBi(), request.getDayChuyen(), request.getMaSanPham(), request.getVersionSanPham(),
                request.getNgayTao(), request.getTimeUpdate(), request.getStatus());
        log.info("" + request);
        List<SanXuatHangNgayResponse> responseList = new ArrayList<>();
        for (SanXuatHangNgayEntity entity : entities) {
            log.info("thanh cong");
            SanXuatHangNgayResponse response = getSanXuatHangNgayResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }
    // ? them moi kich ban san xuat
    public String postSanXuatHangNgay(SanXuatHangNgayRequest request){
        // them moi kich ban san xuat hang ngay
        log.info("Them moi kich ban"+ request);
        SanXuatHangNgayEntity entity = new SanXuatHangNgayEntity();
        entity.setMaKichBan(request.getMaKichBan());
        entity.setMaThietBi(request.getMaThietBi());
        entity.setLoaiThietBi(request.getLoaiThietBi());
        entity.setDayChuyen(request.getDayChuyen());
        entity.setMaSanPham(request.getMaSanPham());
        entity.setVersionSanPham(request.getVersionSanPham());
        entity.setNgayTao(request.getNgayTao());
        entity.setTimeUpdate(request.getTimeUpdate());
        entity.setStatus(request.getStatus());
        this.sanXuatHangNgayRepository.save(entity);
        // Note lay danh sach thong so theo ma kich ban tu table chi tiet kich ban
        List<ChiTietKichBanEntity> entities = this.chiTietKichBanRepository.findAllByMaKichBan(request.getMaKichBan());
        List<ChiTietSanXuatEntity> entityList = new ArrayList<>();
        // Note lưu thông tin thông số sản xuất hàng ngày
        for (ChiTietKichBanEntity entity1: entities){
            ChiTietSanXuatEntity entity2 = new ChiTietSanXuatEntity();
            entity2.setIdSanXuatHangNgay(entity.getIdSanXuatHangNgay());
            entity2.setMaKichBan(entity1.getMaKichBan());
            entity2.setRows(entity1.getRows());
            entity2.setThongSo(entity1.getThongSo());
            entity2.setMinValue(entity1.getMinValue());
            entity2.setMaxValue(entity1.getMaxValue());
            entity2.setTrungBinh(entity1.getTrungBinh());
            entity2.setDonVi(entity1.getDonVi());
            this.chiTietSanXuatRepository.save(entity2);
        }
        return "Them moi kich ban thanh cong";
    }
    //? xem danh sach thong so san xuat hang ngay
    public List<ChiTietSanXuatResponse> getAllsByMaKichBan(String maKichBan){
        List<ChiTietSanXuatEntity> entities = this.chiTietSanXuatRepository.findAllByMaKichBan(maKichBan);
        List<ChiTietSanXuatResponse> responseList = new ArrayList<>();
        log.info("xem danh sach kich ban");
        for (ChiTietSanXuatEntity entity:entities){
            ChiTietSanXuatResponse response = new ChiTietSanXuatResponse();
            response.setIdChiTietSanXuat(entity.getIdChiTietSanXuat());
            response.setMaKichBan(entity.getMaKichBan());
            response.setRows(entity.getRows());
            response.setThongSo(entity.getThongSo());
            response.setMinValue(entity.getMinValue());
            response.setMaxValue(entity.getMaxValue());
            response.setTrungBinh(entity.getTrungBinh());
            response.setDonVi(entity.getDonVi());
            responseList.add(response);
        }
        return responseList;
    }
    //? Cap nhat noi dung san xuat hang ngay (1)
    public String putChiTietSanXuat(List<ChiTietSanXuatRequest> requestList){
        for (ChiTietSanXuatRequest request :requestList){
            ChiTietSanXuatEntity entity = this.chiTietSanXuatRepository.findAllByIdChiTietSanXuat(request.getIdChiTietSanXuat());
            entity.setThongSo(request.getThongSo());
            entity.setMinValue(request.getMinValue());
            entity.setMaxValue(request.getMaxValue());
            entity.setTrungBinh(request.getTrungBinh());
            entity.setDonVi(request.getDonVi());
            this.chiTietSanXuatRepository.save(entity);
        }
        return "Cap nhat noi dung san xuat thanh cong";
    }
    // ? (1)xoa thong so trong noi dung san xuat hang ngay
    public void delByIdChiTietSanXuat(Integer idChiTietSanXuat){
        ChiTietSanXuatEntity entity = this.chiTietSanXuatRepository.findAllByIdChiTietSanXuat(idChiTietSanXuat);
        if (entity == null){
            log.info("khong tim thay thong so");
        }else {
            this.chiTietSanXuatRepository.delete(entity);
            log.info("xoa thong so thanh cong");
        }
    }
    // ! (1)them moi thong so trong noi dung san xuat hang ngay
    //! xem chi tiet noi dung 1 kich ban san xuat hang ngay
}