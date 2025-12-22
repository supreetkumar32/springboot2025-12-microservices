package com.twelve.microservice.order_service.controller;

import com.twelve.microservice.order_service.dto.OrderRequestDto;
import com.twelve.microservice.order_service.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
//@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersService orderService;

    public OrdersController(OrdersService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/helloOrders")
    public String helloOrders() {
        System.out.println("inside the orders controller");
        return "Hello from Orders Service";
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest) {
       // log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);  // Returns 200 OK with the list of orders
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
       // log.info("Fetching order with ID: {} via controller", id);
        OrderRequestDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);  // Returns 200 OK with the order
    }
}
