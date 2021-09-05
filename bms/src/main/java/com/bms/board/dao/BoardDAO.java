package com.bms.board.dao;

import java.util.List;
import java.util.Map;


import com.bms.board.dto.BoardDTO;

public interface BoardDAO {

	public List<BoardDTO> getSearchBoard(Map<String, Object> searchInfo) throws Exception;
	public int getAllBoardCount(Map<String, String> searchCountInfo) throws Exception;
	public BoardDTO getOneBoard(int boardId) throws Exception;
	public void increaseReadCount(int readCount) throws Exception;
	public void insertBoard(BoardDTO bdto) throws Exception;
	public void insertReplyBoard(BoardDTO bdto) throws Exception;
	public void updateBoard(BoardDTO bdto) throws Exception;
	public void deleteBoard(int boardId) throws Exception;
	public void updateBoardReplyStep(BoardDTO bdto) throws Exception;
	public BoardDTO validateUserCheck(BoardDTO bdto) throws Exception;
	
}
