package com.han.webApp.replyBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.command.ReplyBoardDelCommand;
import com.han.webApp.replyBoard.command.ReplyBoardEditCommand;
import com.han.webApp.replyBoard.command.ReplyBoardEditOkCommand;
import com.han.webApp.replyBoard.command.ReplyBoardListCommand;
import com.han.webApp.replyBoard.command.ReplyBoardRewriteOkCommand;
import com.han.webApp.replyBoard.command.ReplyBoardViewCommand;
import com.han.webApp.replyBoard.command.ReplyBoardWriteOkCommand;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

@Controller
public class ReplyBoardController {

   public ReplyBoardController() {}

   //ReplyBoardList
   @RequestMapping("/replyBoard/replyList")
   public ModelAndView replyList(HttpServletRequest request){
      ReplyBoardListCommand com = new ReplyBoardListCommand();
      ModelAndView mav = com.excute(request);
      return mav;
   }
   
   //글쓰기 폼
   @RequestMapping(value="/replyBoard/write", method=RequestMethod.GET)
   public String replyWrite(){
      return "replyBoard/replyForm";
   }
   
   //글저장
   @RequestMapping(value="/replyBoard/replyWriteOk", method=RequestMethod.POST)
   public ModelAndView replyWriteOk(HttpServletRequest r){
      ReplyBoardWriteOkCommand command = new ReplyBoardWriteOkCommand();
      return command.excute(r);
   }
   //글내용보기
   @RequestMapping("/replyBoard/View")
   public ModelAndView replyView(HttpServletRequest r){
	   ReplyBoardViewCommand command = new ReplyBoardViewCommand();
	   return command.excute(r);
	   
   }
   //글수정폼
   @RequestMapping("/replyBoard/replyEdit")
   public ModelAndView replyEdit(@RequestParam("num")int num){
	   ReplyBoardEditCommand command = new ReplyBoardEditCommand();
	   return command.excute(num);
	  
   }
   //글 수정
   @RequestMapping("/replyBoard/replyEditOk")
   public ModelAndView replyEditOk(ReplyBoardVO vo){
	   ReplyBoardEditOkCommand command = new ReplyBoardEditOkCommand();
	   return command.excute(vo);
	   
}
   //답변글쓰기 폼
   @RequestMapping("/replyBoard/replyWrite")
   public ModelAndView replyWrite(@RequestParam("num")int num){
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("num",num);
	   mav.setViewName("replyBoard/replyWrite");
	   return mav;
	   
	  
   }
   //답변글 저장
   @RequestMapping("replyBoard/replyReWriteOk")
   public ModelAndView replyReWriteOk(HttpServletRequest r,ReplyBoardVO vo){
	   ReplyBoardRewriteOkCommand command = new ReplyBoardRewriteOkCommand();
	   vo.setUserip(r.getRemoteAddr());
			   return command.excute(vo);
   }
   //글삭제
   @RequestMapping("/replyBoard/replyDel")
   public ModelAndView replyDel(@RequestParam("num")int num){
	   ReplyBoardDelCommand command = new ReplyBoardDelCommand(); 
	   return command.excute(num);
   }
}