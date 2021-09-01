package com.bms.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.board.dao.BoardDAO;
import com.bms.board.dto.BoardDTO;

@Service("boardService")
@Transactional(propagation=Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardDTO> getSearchBoard(Map<String, Object> searchInfo) throws Exception{
		return boardDAO.getSearchBoard(searchInfo);
	}
	 
	
	
	@Override
	public BoardDTO getOneBoard(int boardId) throws Exception{
		boardDAO.increaseReadCount(boardId);
		return boardDAO.getOneBoard(boardId);
	}
	
	
	
	@Override
	public void insertBoard(BoardDTO bdto) throws Exception {
		boardDAO.insertBoard(bdto);
	}

	
	
	@Override
	public boolean updateBoard(BoardDTO bdto) throws Exception {
		boolean isSuccess = false;
		if (boardDAO.validateUserCheck(bdto) != null) {
			isSuccess = true;
			boardDAO.updateBoard(bdto);
		}
		return isSuccess;
	}

	
	
	@Override
	public boolean deleteBoard(BoardDTO bdto) throws Exception {
		boolean isSuccess = false;
		if (boardDAO.validateUserCheck(bdto) != null) {
			boardDAO.deleteBoard(bdto.getBoardId());
			isSuccess = true;
		}
		return isSuccess;
	}



	@Override
	public int getAllBoardCount(Map<String, String> searchCountInfo) throws Exception {
		return boardDAO.getAllBoardCount(searchCountInfo);
	}

	
	
	@Override
	public void insertReplyBoard(BoardDTO bdto) throws Exception {
		boardDAO.updateBoardReplyStep(bdto);
		boardDAO.insertReplyBoard(bdto);
	}

}
