package com.twelve.microservice.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

//@Data
public class OrderRequestDto {
    private Long id;
    private List<OrderRequestItemDto> items;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public List<OrderRequestItemDto> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(List<OrderRequestItemDto> items) {
        this.items = items;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
