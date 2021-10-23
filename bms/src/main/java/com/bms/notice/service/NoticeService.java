package com.bms.notice.service;

import java.util.List;
import java.util.Map;

import com.bms.notice.dto.NoticeDTO;

public interface NoticeService {

	public List<NoticeDTO> getSearchNotice(Map<String, Object> searchInfo) throws Exception;
	public int getAllNoticeCount(Map<String, String> searchCountInfo) throws Exception;
	public NoticeDTO getOneNotice(int noticeId) throws Exception;
	public void insertNotice(NoticeDTO ndto) throws Exception;
	public boolean updateNotice(NoticeDTO ndto) throws Exception;
	public boolean deleteNotice(NoticeDTO ndto) throws Exception;
	
}
