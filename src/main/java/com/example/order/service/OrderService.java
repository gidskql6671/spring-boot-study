package com.example.order.service;

import com.example.order.domain.Order;

public interface OrderService {
	
	Order createOrder(Long memberId, String itemName, int itemPrice);
}
