package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.OrderService;
import com.qakashilliacea.util.constants.Constants;
import com.qakashilliacea.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.PRIVATE_API_ENDPOINT + "/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity create(OrderDto dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @PostMapping("/changeStatus")
    public ResponseEntity changeStatus (@RequestParam String status, @RequestParam Long orderId) {
        return ResponseEntity.ok(orderService.changeStatus(status, orderId));
    }
}
