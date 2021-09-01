package com.bms.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bms.board.dto.BoardDTO;
import com.bms.board.service.BoardService;

@Controller("boardController")
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/boardList.do")
	public String boardList(@RequestParam(name = "onePageViewCount"  , defaultValue = "10")    int onePageViewCount,
							@RequestParam(name = "currentPageNumber" , defaultValue = "1")     int currentPageNumber,
							@RequestParam(name = "searchKeyword"     , defaultValue = "total") String searchKeyword,
							@RequestParam(name = "searchWord"        , defaultValue = "")      String searchWord,
							Model model) throws Exception {
		
		// 페이지의 시작 게시글 인덱스
		int startBoardIdx =  (currentPageNumber -1) * onePageViewCount;
		
		// 관련 정보 MAP 생성 ( 한페이지에 보여줄 게시글 숫자 , 시작페이지의 인덱스 , 검색 키워드 , 검색어 ) 
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("onePageViewCount", onePageViewCount);
		searchInfo.put("startBoardIdx", startBoardIdx);
		searchInfo.put("searchKeyword", searchKeyword);
		searchInfo.put("searchWord", searchWord);
		List<BoardDTO> boardList = boardService.getSearchBoard(searchInfo);
		
		// 게시글의 전체 개수를 반환하는 관련정보 MAP 생성 ( 검색 키워드 , 검색어 ) 
		Map<String, String> searchCountInfo = new HashMap<String, String>();
		searchCountInfo.put("searchKeyword", searchKeyword);
		searchCountInfo.put("searchWord", searchWord);
		
		// 전체페이지 개수 = 전체게시글 수 / 한페이지에서 보여지는 글수
		int totalBoardCount = boardService.getAllBoardCount(searchCountInfo);
		int addPage = totalBoardCount % onePageViewCount == 0 ? 0 : 1; 		// 나머지가 0이면 추가 x , 나머지가 0이 아니면 +1 페이지 처리
		int totalPageCount = totalBoardCount / onePageViewCount + addPage;
		
		
		// 시작페이지
		int startPage = 1;
		
		if (currentPageNumber % 10 == 0) startPage = (currentPageNumber / 10 - 1) * 10 + 1;
		else 							 startPage = (currentPageNumber / 10) * 10 + 1;							
		
		/*
		 
			[ 예시 ]  
			
			currentPage가 10페이면 시작페이지는 1  	<>		currentPage가 2페이지면  시작페이지는 1  
			currentPage가 20페이면 시작페이지는 11  	<>		currentPage가 12페이지면 시작페이지는 11  
			currentPage가 30페이면 시작페이지는 21 	<>		currentPage가 22페이지면 시작페이지는 21  
			
		*/
		
	
		
		// 끝페이지
		int endPage = startPage + 9;
			
		// 끝페이지가 전체 페이지 개수보다 크다면 
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		// 게시물이 한페이지에 보여지는 것보다 작다면
		if (onePageViewCount > totalBoardCount) {
			startPage = 1;
			endPage = 0;
		}
		
				
		model.addAttribute("startPage" , startPage);
		model.addAttribute("endPage" , endPage);
		model.addAttribute("totalBoardCount" , totalBoardCount);
		model.addAttribute("onePageViewCount" , onePageViewCount);
		model.addAttribute("currentPageNumber" , currentPageNumber);
		model.addAttribute("searchKeyword" , searchKeyword);
		model.addAttribute("searchWord" , searchWord);
		model.addAttribute("boardList",boardList);		
		
		System.out.println("====================================");
		System.out.println("startPage : " + startPage);
		System.out.println("endPage : " + endPage);
		System.out.println("totalBoardCount : " + totalBoardCount);
		System.out.println("onePageViewCount : " + onePageViewCount);
		System.out.println("currentPageNumber : " + currentPageNumber);
		System.out.println("searchKeyword : " + searchKeyword);
		System.out.println("searchWord : " + searchWord);
		System.out.println("====================================\n");
		
		return "board/boardList.do";
		
	}
	
	
	
	@RequestMapping(value = "/boardWrite.do" , method = RequestMethod.GET)
	public String boardWrite() throws Exception{
		return "board/bWrite";
	}
	
	
	
	@RequestMapping(value = "/boardWrite.do" , method = RequestMethod.POST)
	public String boardWrite(BoardDTO bdto) throws Exception{
		boardService.insertBoard(bdto);
		return "redirect:/board/boardList";	
	}
	
	
	
	@RequestMapping(value = "/boardReplyWrite.do" , method = RequestMethod.GET)
	public String boardReplyWrite(@RequestParam("boardId") int boardId , Model model) throws Exception{
		model.addAttribute("bdto", boardService.getOneBoard(boardId));
		return "board/bReply";
	}
	
	
	
	@RequestMapping(value = "/boardReplyWrite.do" , method = RequestMethod.POST)
	public String boardReplyWrite(BoardDTO bdto) throws Exception{
		boardService.insertReplyBoard(bdto);
		return "redirect:/board/boardList";	
	}
	
	
	
	@RequestMapping(value = "/boardInfo.do")
	public String boardInfo(@RequestParam("boardId") int boardId , Model model) throws Exception{
		
		BoardDTO bdto = boardService.getOneBoard(boardId);
		model.addAttribute("bdto",bdto);
		
		return "board/bInfo";
		
	}
	
	
	
	@RequestMapping(value = "/boardUpdate.do" , method = RequestMethod.GET)
	public String boardUpdate(@RequestParam("boardId") int boardId  , Model model) throws Exception{
		
		BoardDTO bdto = boardService.getOneBoard(boardId);
		model.addAttribute("bdto", bdto);
		
		return "board/bUpdate";
		
	}
	
	
	
	@RequestMapping(value = "/boardUpdate.do" , method = RequestMethod.POST)
	public ResponseEntity<Object> boardUpdate(BoardDTO bdto , Model model , HttpServletRequest request) throws Exception{
		
		String message = "";
		
		if (boardService.updateBoard(bdto)) {
			message = "<script>";
			message += "alert('수정 되었습니다.');";
			message += "location.href='"+ request.getContextPath() +"/board/boardList';"; // javascript의 ${pageContext.request.contextPath}의 역할과 같다.
			message += "</script>";
		}
		else {
		   message ="<script>"; 
		   message += "alert('비밀번호를 확인해주세요.');";
		   message += "history.go(-1);";
		   message += "</script>";
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message , responseHeaders , HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "/boardDelete.do" , method = RequestMethod.GET)
	public String boardDelete(@RequestParam("boardId") int boardId , Model model ) throws Exception{
		
		BoardDTO bdto = boardService.getOneBoard(boardId);
		model.addAttribute("bdto", bdto);
		
		return "board/bDelete";
		
	}
	
	
	
	@RequestMapping(value = "/boardDelete.do" , method = RequestMethod.POST)
	public ResponseEntity<Object> boardDelete(Model model , BoardDTO bdto , HttpServletRequest request) throws Exception{
		
		String message = "";
		
		if (boardService.deleteBoard(bdto)) {
			message = "<script>";
			message += "alert('삭제 되었습니다.');";
			message += "location.href='"+ request.getContextPath() +"/board/boardList';"; // javascript의 ${pageContext.request.contextPath}의 역할과 같다.
			message += "</script>";
		}
		else {
		   message ="<script>"; 
		   message += "alert('비밀번호를 확인해주세요.');";
		   message += "history.go(-1);";
		   message += "</script>";
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message , responseHeaders , HttpStatus.OK);
		
	}		
	
}
