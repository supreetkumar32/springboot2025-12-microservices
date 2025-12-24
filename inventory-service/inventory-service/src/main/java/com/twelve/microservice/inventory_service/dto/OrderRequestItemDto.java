package com.twelve.microservice.inventory_service.dto;

import lombok.Data;

//@Data
public class OrderRequestItemDto {
    private Long productId;
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
