package com.RangDong.RD_KhaiBaoThongSo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage<T> {
    private String message;
}
