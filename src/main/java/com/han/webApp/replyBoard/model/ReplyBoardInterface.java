package com.han.webApp.replyBoard.model;

import java.util.List;

public interface ReplyBoardInterface {

   //�۸��
   public List<ReplyBoardVO> replyList();
   //�۵��
   public void replyWrite(ReplyBoardVO vo); 
   //�۳���
   public ReplyBoardVO select(int num);
   //�ۼ���
   //��۴ޱ�
   //����
   
   //�ѷ��ڵ��
   public int getTotalRecord();
   
   
}