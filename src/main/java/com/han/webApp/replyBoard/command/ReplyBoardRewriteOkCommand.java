package com.han.webApp.replyBoard.command;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardRewriteOkCommand {
	public ModelAndView excute(ReplyBoardVO vo){
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		int result = dao.replyInsert(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",vo.getCnt());
		mav.setViewName("replyBoard/replyWriteOk");
		return mav;
		
	}
}
