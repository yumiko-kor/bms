package com.bms.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bms.order.dao.OrderDAO;
import com.bms.order.dto.OrderDTO;

public interface OrderService {
	
	public List<OrderDTO> listMyOrderGoods(OrderDTO orderDTO) throws Exception;
	public void addNewOrder(List<OrderDTO> myOrderList) throws Exception;
	public OrderDTO findMyOrder(String orderId) throws Exception;
	public Map<String, Object> goodsDeliveryPrice() throws Exception;
	
}
