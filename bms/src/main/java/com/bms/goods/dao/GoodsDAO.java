package com.bms.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bms.goods.dto.GoodsDTO;
import com.bms.goods.dto.ImageFileDTO;

public interface GoodsDAO {
	
	public List<GoodsDTO> selectGoodsList(String goodsStatus ) throws DataAccessException;
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	public List<GoodsDTO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;
	public GoodsDTO selectGoodsDetail(String goodsId) throws DataAccessException;
	public List<ImageFileDTO> selectGoodsDetailImage(String goodsId) throws DataAccessException;

}
