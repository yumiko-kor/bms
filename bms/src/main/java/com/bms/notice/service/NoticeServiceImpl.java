package com.bms.notice.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bms.notice.dao.NoticeDAO;
import com.bms.notice.dto.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Inject
	public NoticeDAO noticeDAO;

	@Override
	public List<NoticeDTO> getSearchNotice(Map<String, Object> searchInfo) throws Exception{
		return noticeDAO.getSearchNotice(searchInfo);
	}
	 
	
	
	@Override
	public NoticeDTO getOneNotice(int noticeId) throws Exception{
		noticeDAO.increaseReadCount(noticeId);
		return noticeDAO.getOneNotice(noticeId);
	}
	
	
	
	@Override
	public void insertNotice(NoticeDTO ndto) throws Exception {
		noticeDAO.insertNotice(ndto);
	}

	
	
	@Override
	public boolean updateNotice(NoticeDTO ndto) throws Exception {
		boolean isSuccess = false;
		if (noticeDAO.validateUserCheck(ndto) != null) {
			isSuccess = true;
			noticeDAO.updateNotice(ndto);
		}
		return isSuccess;
	}

	
	
	@Override
	public boolean deleteNotice(NoticeDTO ndto) throws Exception {
		boolean isSuccess = false;
		if (noticeDAO.validateUserCheck(ndto) != null) {
			noticeDAO.deleteNotice(ndto.getNoticeId());
			isSuccess = true;
		}
		return isSuccess;
	}
	
	
	@Override
	public int getAllNoticeCount(Map<String, String> searchCountInfo) throws Exception {
		return noticeDAO.getAllNoticeCount(searchCountInfo);
	}




}
