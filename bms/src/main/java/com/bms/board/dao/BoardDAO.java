package com.bms.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bms.board.dto.BoardDTO;

public interface BoardDAO {

	public List<BoardDTO> getSearchBoard(Map<String, Object> searchInfo) throws DataAccessException;
	public int getAllBoardCount(Map<String, String> searchCountInfo) throws DataAccessException;
	public BoardDTO getOneBoard(int boardId) throws DataAccessException;
	public void increaseReadCount(int readCount) throws DataAccessException;
	public void insertBoard(BoardDTO bdto) throws DataAccessException;
	public void insertReplyBoard(BoardDTO bdto) throws DataAccessException;
	public void updateBoard(BoardDTO bdto) throws DataAccessException;
	public void deleteBoard(int boardId) throws DataAccessException;
	public void updateBoardReplyStep(BoardDTO bdto) throws DataAccessException;
	public BoardDTO validateUserCheck(BoardDTO bdto) throws DataAccessException;
	
}
