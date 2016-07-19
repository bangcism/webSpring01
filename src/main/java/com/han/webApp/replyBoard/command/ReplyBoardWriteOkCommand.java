package com.han.webApp.replyBoard.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardWriteOkCommand implements ReplyBoardServiceInterface {

   @Override
   public ModelAndView excute(HttpServletRequest r) {
      
      ReplyBoardVO vo = new ReplyBoardVO(r.getParameter("username"),r.getParameter("subject"),
            r.getParameter("content"), r.getRemoteAddr()); //������
      ReplyBoardDAO dao = new ReplyBoardDAO();
      int cnt = dao.replyWrite(vo);
      //////
      ModelAndView mav = new ModelAndView();
      mav.addObject("cnt", cnt);  //ModelAndView���� ��ü ����
      mav.setViewName("replyBoard/replyWriteOk"); //ModleAndView���� ������ ����
      return mav;
   }

}