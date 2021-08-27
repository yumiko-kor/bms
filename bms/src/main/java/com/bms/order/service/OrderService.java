package com.bms.order.service;

import java.util.List;

import com.bms.goods.dto.GoodsDTO;
import com.bms.order.dto.OrderDTO;

public interface OrderService {
	
	public List<OrderDTO> listMyOrderGoods(OrderDTO orderDTO) throws Exception;
	public void addNewOrder(List<OrderDTO> myOrderList) throws Exception;
	public OrderDTO findMyOrder(String orderId) throws Exception;
	public GoodsDTO goodsDetail(int goodsId) throws Exception;
	
}
