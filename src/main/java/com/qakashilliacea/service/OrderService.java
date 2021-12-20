package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.OrderDto;
import com.qakashilliacea.web.dto.OrderInfoDto;

import java.util.List;

public interface OrderService {
    Long createOrder (OrderDto dto);
    List<OrderInfoDto> getAllOrders();
    OrderInfoDto changeStatus(String status, Long oderId);
}
