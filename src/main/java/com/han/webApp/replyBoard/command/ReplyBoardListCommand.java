package com.han.webApp.replyBoard.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardListCommand implements ReplyBoardServiceInterface	{
	
	@Override
	public ModelAndView excute(HttpServletRequest request){
		//���ڵ� ����
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		List<ReplyBoardVO> list = dao.replyList();
		//�ѷ��ڵ��
		int totalRecord = dao.getTotalRecord();
		//�ѷ��ڵ�� - (����������-1) * ���������� ��·��ڵ��)-->������������ȣ
	ModelAndView mav = new ModelAndView();
	mav.addObject("list",list);
	mav.addObject("startNumber",totalRecord);
	mav.setViewName("replyBoard/replyList");
	return mav;
	}

	
	}


