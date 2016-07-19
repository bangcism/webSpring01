package com.han.webApp.replyBoard.command;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardEditOkCommand {
	public ModelAndView excute(ReplyBoardVO vo){
		ReplyBoardDAO dao= ReplyBoardDAO.getInstance();
		int cnt = dao.update(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num",vo.getCnt());
		mav.addObject("result",cnt);
		mav.setViewName("replyBoard/replyEditOk");
		return mav;
		
	}
}
