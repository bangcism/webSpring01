package com.han.webApp.replyBoard.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.han.webApp.DBCP;
import com.han.webApp.Constant;

public class ReplyBoardDAO extends DBCP {


   
   public static ReplyBoardDAO dao = new ReplyBoardDAO();
   public static ReplyBoardDAO getInstance(){
      return dao;
   }
   public JdbcTemplate template;
protected PreparedStatement ps;
	
	
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public ReplyBoardDAO(){
		template = Constant.template;
	}
   //越鯉系
   public List<ReplyBoardVO> replyList(){
     String sql = "select num, username, subject, hit, writedate,lev from replyBoard order by ref desc, step asc";
	return (ArrayList<ReplyBoardVO>)template.query(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));



   }
   //越去系
   public int replyWrite(final ReplyBoardVO vo){
	   String sql = "insert into replyboard(num, username, subject"
               + ",content,userip,ref) values("
               + "replyBoardSq.nextVal,?,?,?,?,replyBoardSq.currVal)";
	   return template.update(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement tstmt) throws SQLException {
			pstmt.setString(1, vo.getUsername());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(1, vo.getUserip());
			
		}
	});
	   
	   /* try{
         getConnection();
         String sql = "insert into replyboard(num, username, subject"
               + ",content,userip,ref) values("
               + "replyBoardSq.nextVal,?,?,?,?,replyBoardSq.currVal)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getUsername());
         pstmt.setString(2, vo.getSubject());
         pstmt.setString(3, vo.getContent());
         pstmt.setString(4, vo.getUserip());
         vo.setCnt(pstmt.executeUpdate());
      }catch(Exception e){System.out.println("傾坪球蓄亜 叔鳶= "+e.getMessage());}
      finally{dbClose();} */
   } 

   //恥傾坪球呪
   public int getTotalRecord(){
    String sql = "select count(num)cnt from replyBoard";
    ReplyBoardVO vo=template.queryForObject(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
   return vo.getCnt();
   }
    //傾坪球 識澱(越鎧遂左奄)
   
   //繕噺呪装亜
   public void hitCount(final int num){
	   String sql = "update replyBoard set hit = hit+1 where num=?";
	   template.update(sql, new PreparedStatementSetter(){
		   @Override
		   public void setValues(PreparedStatement ps) throws SQLException{
			   ps.setInt(1, num);
		   }
	   });
   }
  //傾坪球 識澱
   public ReplyBoardVO select(final int num){
	   String sql ="select num, username, subject, content, hit, writedate, userip from replyBoard where num=?";
      return template.queryForObject (sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
       /* try{
         conn = getConnection();
         //繕噺呪 朝錘闘
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
   */
   }
   //傾坪球呪舛
   public int update(final ReplyBoardVO vo){
	  
	   String sql = "update replyBoard set username=? subject=?"
			   +"content=? where num=?";
	   return template.update(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setString(1, vo.getUsername());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setInt(4, vo.getNum());
			
			
		}
	});
   }
   //岩痕越煽舌
   public int replyInsert(final ReplyBoardVO vo){
	   //据越税 舛左 条奄
	   String sql = "select ref,lev,step from replyBoard where num=?";
	  final ReplyBoardVO vo2 = template.queryForObject(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
	   //什怒装亜
	   sql = " update replyBoard set step = step+1 where ref=? and step>?";
	  template.update(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, vo2.getRef());
			ps.setInt(1, vo2.getStep());
			
		}
	});
	  //岩痕越 煽舌
	  sql = "insert into replyBoard(num,username, subject, content,"
			   +"userip, ref, lev, step)"
			   +"value(replyBoardSq.nextVal,?,?,?,?,?,?,?)";
	  return template.update(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setString(1, vo.getUsername());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getUserip());
			ps.setInt(5, vo2.getRef());
			ps.setInt(6, vo2.getLev()+1);
			ps.setInt(7, vo2.getStep()+1);
			
		}
	});	  
	   
	   /*
	   conn=getConnection();//db尻衣
	  int updateCnt=0; //穣汽戚闘 衣引
	  int insertCnt=0;//昔竺闘 衣引
	   try{
		   //切疑朕行昼社
		   conn.setAutoCommit(false);
		   //据越税 ref,lev,step
		   String sql = "select ref,lev,step from replyBoard where num=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1,vo.getNum());
		   rs=pstmt.executeQuery();
		   rs.next();
		   int ref=rs.getInt(1);
		   int lev = rs.getInt(2);
		   int step = rs.getInt(3);
		   //step装亜
		   sql = " update replyBoard set step = step+1 where ref=? and step>?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, ref);
		   pstmt.setInt(2, step);
		  updateCnt= pstmt.executeUpdate();
		   //岩痕越 煽舌
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
*/
}
   //傾坪球 肢薦
   public int delete(final int num){
	   //据越 暁澗 岩痕越税 舛左条奄
	   String sql = "select lev, ref from replyBoard where num=?";
	   final ReplyBoardVO vo=template.queryForObject(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
	  int cnt=0;//叔楳衣引
	   if(vo.getLev()==0){//据越
		   sql = "select lev, ref from replyBoard where num=?";
		   cnt = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				ps.setInt(1, vo.getRef());
				
				
			}
		});
	   }else{//岩痕越
		   sql = "update replyBoard set username=?, subject=?, content=?"
				   +"where num=?";
		   cnt = template.update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, "");
				ps.setString(2, "肢薦吉越脊艦陥");
				ps.setString(3, "肢薦吉越...");
				ps.setInt(4, num);
				
			}
		});
	   }
	   return cnt;
   }
}
	   /*
	   
	  int cnt = 0;
	   try{
		   conn = getConnection();
		   //肢薦拝 傾坪球税 lev姥馬奄
		   
		   String sql = "select lev, ref from replyBoard where num=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, num);
		   rs = pstmt.executeQuery();
		   if(rs.next()){
			   if(rs.getInt(1)==0){//据越
				   sql = "delete from replyBoard where ref=?";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setInt(1,rs.getInt(2));
				   pstmt.executeUpdate();
				   cnt = pstmt.executeUpdate();
			
			   }else{//岩越
				   sql = "update replyBoard set username=?, subject=?, content=?"
						   +"where num=?";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setString(1, "肢薦吉 越戚雁しししししししししし");
				   pstmt.setString(2,"巷奄誤");
				   pstmt.setString(3,"..");
				   pstmt.setInt(4,num);
				   cnt = pstmt.executeUpdate();
				   
			   }
			   
		   }
		   
	   }catch(Exception e){e. printStackTrace();}
	   finally{dbClose();}
	   return cnt;
	   }
   */
   
