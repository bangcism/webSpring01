package com.han.webApp.replyBoard.command;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;

public class ReplyBoardDelCommand {
	public ModelAndView excute(int num){
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
				int cnt = dao.delete(num);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("cnt",cnt);
		//mav.addObject("num",num);
		mav.setViewName("replyBoard/replyDelOk");
		return mav;
	}

}
