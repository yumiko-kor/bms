package com.bms.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bms.board.dto.BoardDTO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> getSearchBoard(Map<String, Object> searchInfo) throws Exception {
		return sqlSession.selectList("mapper.board.getSearchBoard",searchInfo);
	}

	@Override
	public int getAllBoardCount(Map<String, String> searchCountInfo) throws Exception {
		return sqlSession.selectOne("mapper.board.getAllBoardCount" , searchCountInfo);
	}

	@Override
	public BoardDTO getOneBoard(int boardId) throws Exception {
		return sqlSession.selectOne("mapper.board.getOneBoard",boardId);
	}

	@Override
	public void increaseReadCount(int boardId) throws Exception {
		sqlSession.update("mapper.board.increaseReadCount",boardId);
	}

	@Override
	public void insertBoard(BoardDTO bdto) throws Exception {
		sqlSession.insert("mapper.board.insertBoard",bdto);
	}

	@Override
	public void insertReplyBoard(BoardDTO bdto) throws Exception {
		sqlSession.insert("mapper.board.insertReplyBoard",bdto);
	}

	@Override
	public void updateBoard(BoardDTO bdto) throws Exception {
		sqlSession.update("mapper.board.updateBoard",bdto);
	}

	@Override
	public void deleteBoard(int boardId) throws Exception {
		sqlSession.delete("mapper.board.deleteBoard",boardId);
	}

	@Override
	public void updateBoardReplyStep(BoardDTO bdto) throws Exception {
		sqlSession.update("mapper.board.updateBoardReplyStep" , bdto);
	}

	@Override
	public BoardDTO validateUserCheck(BoardDTO bdto) throws Exception {
		return sqlSession.selectOne("mapper.board.validateUserCheck",bdto);
	}


}
