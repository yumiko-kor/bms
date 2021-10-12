package com.bms.notice.dao;

import java.util.List;
import java.util.Map;

import com.bms.notice.dto.NoticeDTO;

public interface NoticeDAO {

	public List<NoticeDTO> getSearchNotice(Map<String, Object> searchInfo) throws Exception;
	public int getAllNoticeCount(Map<String, String> searchCountInfo) throws Exception;
	public NoticeDTO getOneNotice(int noticeId) throws Exception;
	public void increaseReadCount(int readCount) throws Exception;
	public void insertNotice(NoticeDTO ndto) throws Exception;
	public void updateNotice(NoticeDTO ndto) throws Exception;
	public void deleteNotice(int noticeId) throws Exception;
	public NoticeDTO validateUserCheck(NoticeDTO ndto) throws Exception;

}
