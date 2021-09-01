package com.bms.board.service;

import java.util.List;
import java.util.Map;

import com.bms.board.dto.BoardDTO;

public interface BoardService {

	public List<BoardDTO> getSearchBoard(Map<String, Object> searchInfo) throws Exception;
	public int getAllBoardCount(Map<String, String> searchCountInfo) throws Exception;
	public BoardDTO getOneBoard(int boardId) throws Exception;
	public void insertBoard(BoardDTO bdto) throws Exception;
	public void insertReplyBoard(BoardDTO bdto) throws Exception;
	public boolean updateBoard(BoardDTO bdto) throws Exception;
	public boolean deleteBoard(BoardDTO bdto) throws Exception;
	
}
