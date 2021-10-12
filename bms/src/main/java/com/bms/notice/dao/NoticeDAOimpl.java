package com.bms.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bms.notice.dto.NoticeDTO;

@Repository("NoticeDAO")
public class NoticeDAOimpl implements NoticeDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NoticeDTO> getSearchNotice(Map<String, Object> searchInfo) throws Exception {
		return sqlSession.selectList("mapper.notice.getSearchNotice",searchInfo);
	}

	@Override
	public int getAllNoticeCount(Map<String, String> searchCountInfo) throws Exception {
		return sqlSession.selectOne("mapper.notice.getAllNoticeCount" , searchCountInfo);
	}

	@Override
	public NoticeDTO getOneNotice(int noticeId) throws Exception {
		return sqlSession.selectOne("mapper.notice.getOneNotice",noticeId);
	}

	@Override
	public void increaseReadCount(int noticeId) throws Exception {
		sqlSession.update("mapper.notice.increaseReadCount",noticeId);
	}

	@Override
	public void insertNotice(NoticeDTO ndto) throws Exception {
		sqlSession.insert("mapper.notice.insertNotice",ndto);
	}


	@Override
	public void updateNotice(NoticeDTO ndto) throws Exception {
		sqlSession.update("mapper.notice.updateNotice",ndto);
	}

	@Override
	public void deleteNotice(int noticeId) throws Exception {
		sqlSession.delete("mapper.notice.deleteNotice",noticeId);
	}


	@Override
	public NoticeDTO validateUserCheck(NoticeDTO ndto) throws Exception {
		return sqlSession.selectOne("mapper.notice.validateUserCheck",ndto);
	}
	
}
