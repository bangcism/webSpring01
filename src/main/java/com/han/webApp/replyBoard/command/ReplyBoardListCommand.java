package com.han.webApp.replyBoard.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardListCommand implements ReplyBoardServiceInterface	{
	
	@Override
	public ModelAndView excute(HttpServletRequest request){
		//레코드 선택
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		List<ReplyBoardVO> list = dao.replyList();
		//총레코드수
		int totalRecord = dao.getTotalRecord();
		//총레코드수 - (현재페이지-1) * 한페이지당 출력레코드수)-->시작페이지번호
	ModelAndView mav = new ModelAndView();
	mav.addObject("list",list);
	mav.addObject("startNumber",totalRecord);
	mav.setViewName("replyBoard/replyList");
	return mav;
	}

	
	}


