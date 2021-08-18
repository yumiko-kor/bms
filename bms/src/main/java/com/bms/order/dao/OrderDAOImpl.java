package com.bms.order.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bms.order.dto.OrderDTO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<OrderDTO> listMyOrderGoods(OrderDTO orderDTO) throws DataAccessException{
		return sqlSession.selectList("mapper.order.selectMyOrderList",orderDTO);
	}
	
	public void insertNewOrder(List<OrderDTO> myOrderList) throws DataAccessException{
		int orderId = selectOrderID();
		for(int i=0; i<myOrderList.size(); i++){
			OrderDTO orderDTO = (OrderDTO)myOrderList.get(i);
			orderDTO.setOrderId(orderId);
			sqlSession.insert("mapper.order.insertNewOrder",orderDTO);
		}
		
	}	
	
	public OrderDTO findMyOrder(String orderId) throws DataAccessException{
		return sqlSession.selectOne("mapper.order.selectMyOrder",orderId);
	}
	
	private int selectOrderID() throws DataAccessException{
		return sqlSession.selectOne("mapper.order.selectOrderID");
	}
	
}

