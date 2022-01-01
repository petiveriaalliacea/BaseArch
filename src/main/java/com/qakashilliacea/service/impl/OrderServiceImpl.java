package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Order;
import com.qakashilliacea.entity.enums.OrderStatus;
import com.qakashilliacea.respository.OrderRepository;
import com.qakashilliacea.service.OrderService;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.OrderDto;
import com.qakashilliacea.web.dto.OrderInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public Long createOrder(OrderDto dto) {
        Order order = ObjectsMapper.convertToOrder(dto);
        order.setOrderStatus(OrderStatus.OPEN);
        return orderRepository.save(order).getId();
    }

    @Override
    public List<OrderInfoDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(ObjectsMapper::converToOrderInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderInfoDto changeStatus(String status, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        orderRepository.save(order);
        System.out.println("order saved");
        return ObjectsMapper.converToOrderInfoDto(order);
    }
}
