package com.qakashilliacea.web.dto;

import com.qakashilliacea.entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderInfoDto {
    private Long productId;
    private Long userId;
    private OrderStatus orderStatus;
    private LocalDateTime creationDate;
}
