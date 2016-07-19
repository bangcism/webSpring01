package com.han.webApp.replyBoard.model;

import java.util.ArrayList;
import java.util.List;

import com.han.webApp.DBCP;

public class ReplyBoardDAO2 extends DBCP implements ReplyBoardInterface{

   public ReplyBoardDAO2(){}
   
   public static ReplyBoardDAO dao = new ReplyBoardDAO();
   public static ReplyBoardDAO getInstance(){
      return dao;
   }
   //글목록
   public List<ReplyBoardVO> replyList(){
      ArrayList<ReplyBoardVO> list = new ArrayList<ReplyBoardVO>();
      try{
         conn = getConnection();
         String sql = "select num, username, subject, hit, writedate,lev from replyBoard order by ref desc, step asc";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while(rs.next()){
            ReplyBoardVO vo = new ReplyBoardVO();
            vo.setNum(rs.getInt(1));
            vo.setUsername(rs.getString(2));
            vo.setSubject(rs.getString(3));
            vo.setHit(rs.getInt(4));
            vo.setWritedate(rs.getString(5));
            vo.setLev(rs.getInt(6));
            list.add(vo);
         }
      }catch(Exception e){System.out.println("레코드 선택 에러 !!!! = "+e.getMessage());}
      finally{dbClose();}
   
      return list;
   }
   //글등록
   public void replyWrite(ReplyBoardVO vo){
      try{
         conn = getConnection();
         String sql = "insert into replyboard(num, username, subject"
               + ",content,userip,ref) values("
               + "replyBoardSq.nextVal,?,?,?,?,replyBoardSq.currVal)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getUsername());
         pstmt.setString(2, vo.getSubject());
         pstmt.setString(3, vo.getContent());
         pstmt.setString(4, vo.getUserip());
         vo.setCnt(pstmt.executeUpdate());
      }catch(Exception e){System.out.println("레코드추가 실패= "+e.getMessage());}
      finally{dbClose();}
   } 
   
   //총레코드수
   public int getTotalRecord(){
      int cnt=0;
      try{
         conn = getConnection();
         String sql = "select count(num) from replyBoard";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if(rs.next()){cnt = rs.getInt(1);}
      }catch(Exception e){e.printStackTrace();}
      finally{dbClose();}
      return cnt;
   }
   //레코드 선택(글내용보기)
   public ReplyBoardVO select(int num){
      ReplyBoardVO vo = new ReplyBoardVO();
      try{
         conn = getConnection();
         //조회수 카운트
         String sql2 = "update replyBoard set hit = hit+1 where num=?";
         pstmt = conn.prepareStatement(sql2);
         pstmt.setInt(1, num);
         pstmt.executeUpdate();
         
         String sql ="select num, username, subject, content, hit, writedate, userip from replyBoard where num=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, num);
         rs = pstmt.executeQuery();
         if(rs.next()){
            vo.setNum(rs.getInt(1));
            vo.setUsername(rs.getString(2));
            vo.setSubject(rs.getString(3));
            vo.setContent(rs.getString(4));
            vo.setHit(rs.getInt(5));
            vo.setWritedate(rs.getString(6));
            vo.setUserip(rs.getString(7));
         }
      }catch(Exception e){e.printStackTrace();}
      finally{dbClose();}
      return vo;
   }
   //레코드수정
   public void update(ReplyBoardVO vo){
	   try{
		   conn = getConnection();
		   String sql = "update replyBoard set username=? subject=?"
				   +"content=? where num=?";
		   pstmt= conn.prepareStatement(sql);
		   pstmt.setString(1, vo.getUsername());
		   pstmt.setString(2, vo.getSubject());
		   pstmt.setString(3, vo.getContent());
		   pstmt.setInt(4, vo.getNum());
		   vo.setCnt(pstmt.executeUpdate());
		   
	   }catch(Exception e){e.printStackTrace();}
	   finally{dbClose();}
   }
   //답변글저장
   public void replyInsert(ReplyBoardVO vo){
	   conn=getConnection();//db연결
	  int updateCnt=0; //업데이트 결과
	  int insertCnt=0;//인설트 결과
	   try{
		   //자동커밋취소
		   conn.setAutoCommit(false);
		   //원글의 ref,lev,step
		   String sql = "select ref,lev,step from replyBoard where num=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1,vo.getNum());
		   rs=pstmt.executeQuery();
		   rs.next();
		   int ref=rs.getInt(1);
		   int lev = rs.getInt(2);
		   int step = rs.getInt(3);
		   //step증가
		   sql = " update replyBoard set step = step+1 where ref=? and step>?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, ref);
		   pstmt.setInt(2, step);
		  updateCnt= pstmt.executeUpdate();
		   //답변글 저장
		   sql = "insert into replyBoard(num,username, subject, content,"
				   +"userip, ref, lev, step)"
				   +"value(replyBoardSq.nextVal,?,?,?,?,?,?,?)";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1,vo.getUsername());
		   pstmt.setString(2,vo.getSubject());
		   pstmt.setString(3,vo.getContent());
		   pstmt.setString(4,vo.getUserip());
		   pstmt.setInt(5, ref);
		   pstmt.setInt(6, lev+1);
		   pstmt.setInt(7, step+1);
		   insertCnt = pstmt.executeUpdate();
		   vo.setCnt(insertCnt);
		   
		   conn.commit();
	   }catch(Exception e){
	   try{
		   conn.rollback();
		   
	   }catch(Exception i){i.getMessage();}
	   {System.out.println(e.getMessage());}
   }
	   finally{
	   try{
	   conn.setAutoCommit(true);
   }catch(Exception i){i.getMessage();}
	   dbClose();
}
}
   //레코드 삭제
   public int delete(int num){
	  int cnt = 0;
	   try{
		   conn = getConnection();
		   //삭제할 레코드의 lev구하기
		   
		   String sql = "select lev, ref from replyBoard where num=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, num);
		   rs = pstmt.executeQuery();
		   if(rs.next()){
			   if(rs.getInt(1)==0){//원글
				   sql = "delete from replyBoard where ref=?";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setInt(1,rs.getInt(2));
				   pstmt.executeUpdate();
				   cnt = pstmt.executeUpdate();
			
			   }else{//답글
				   sql = "update replyBoard set username=?, subject=?, content=?"
						   +"where num=?";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setString(1, "삭제된 글이당ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
				   pstmt.setString(2,"무기명");
				   pstmt.setString(3,"..");
				   pstmt.setInt(4,num);
				   cnt = pstmt.executeUpdate();
				   
			   }
			   
		   }
		   
	   }catch(Exception e){e. printStackTrace();}
	   finally{dbClose();}
	   return cnt;
	   }
   }
