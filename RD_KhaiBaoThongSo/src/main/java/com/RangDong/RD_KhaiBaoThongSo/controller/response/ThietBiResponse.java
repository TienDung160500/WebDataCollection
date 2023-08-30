package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import com.RangDong.RD_KhaiBaoThongSo.repository.entity.ThongSoMayEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ThietBiResponse {
    private Integer idThietBi;
    private String maThietBi;
    private String loaiThietBi;
    private String dayChuyen;
    private Date ngayTao;
    private Date timeUpdate;
    private String updateBy;
    private String status;
    private List<ThongSoMayEntity> thongSoMayResponseList;

    public ThietBiResponse() {
    }
}
