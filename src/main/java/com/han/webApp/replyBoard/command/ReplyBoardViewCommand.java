package com.han.webApp.replyBoard.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardViewCommand implements ReplyBoardServiceInterface {

   @Override
   public ModelAndView excute(HttpServletRequest request) {
      int num = Integer.parseInt(request.getParameter("num"));
	  ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
      ReplyBoardVO vo = dao.select(Integer.parseInt(request.getParameter("num")));
      
      dao.hitCount(num);//조회수 증가
      ModelAndView mav = new ModelAndView();
      mav.addObject("vo",vo);
      mav.setViewName("replyBoard/replyView");
      return mav;
   }

}