package com.musaic.eventreply.dao;

import java.util.ArrayList;
import java.util.List;

import com.musaic.eventreply.vo.EventReplyVO;
import com.musaic.main.dao.DAO;
import com.musaic.util.db.DB;
import com.webjjang.util.page.ReplyPageObject;

public class EventReplyDAO extends DAO{

	public List<EventReplyVO> list(ReplyPageObject pageObject) throws Exception {
		
		List<EventReplyVO> list = null;
		
		try {
			
			con = DB.getConnection();
			
			pstmt = con.prepareStatement(LIST);
			pstmt.setLong(1, pageObject.getNo());
			pstmt.setLong(2, pageObject.getStartRow());
			pstmt.setLong(3, pageObject.getEndRow());
			
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<EventReplyVO>();
					
					EventReplyVO vo = new EventReplyVO();
					vo.setRno(rs.getLong("rno"));
					vo.setNo(rs.getLong("no"));
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		
		return list;
	}

	public Long getTotalRow(ReplyPageObject pageObject) throws Exception {
		
		Long totalRow = null;
		
		try {
			
			con = DB.getConnection();
			
			pstmt = con.prepareStatement(TOTALROW);
			pstmt.setLong(1, pageObject.getNo());
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		
		return totalRow;
	}

	public int write(EventReplyVO vo) throws Exception {
	
		int result = 0;
	
	try {
		
		con = DB.getConnection();
		
		pstmt = con.prepareStatement(WRITE);
		pstmt.setLong(1, vo.getNo());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getId());

		result = pstmt.executeUpdate();
		
		System.out.println("댓글 등록 성공");
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw e;
	} finally {
		DB.close(con, pstmt);
	}
	
	
	return result;
}

	public int update(EventReplyVO vo) throws Exception {
		
		int result = 0;
	
	try {
		
		con = DB.getConnection();
		
		pstmt = con.prepareStatement(UPDATE);
		pstmt.setString(1, vo.getContent());
		pstmt.setLong(2, vo.getRno());

		result = pstmt.executeUpdate();
		
		System.out.println("댓글 수정 성공");
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw e;
	} finally {
		DB.close(con, pstmt);
	}
	
	
	return result;
}

public int delete(EventReplyVO vo) throws Exception {
		
		int result = 0;
	
	try {
		
		con = DB.getConnection();
		
		pstmt = con.prepareStatement(DELETE);
		pstmt.setLong(1, vo.getRno());

		result = pstmt.executeUpdate();
		
		System.out.println("댓글 삭제 성공");
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw e;
	} finally {
		DB.close(con, pstmt);
	}
	
	
	return result;
}

	final String LIST = ""
			+ " select rno, no, content, id, writeDate "
			+ " from ("
				+ " select rownum rnum, rno, no, content, id, writeDate "
				+ " from ( "
					+ " select e.rno, e.no, e.content, m.id, "
					+ " to_char(e.writeDate, 'yyyy-mm-dd') writeDate "
					+ " from event_reply e, member m "
					+ " where (e.id = m.id) "
					+ " and e.no = ? "
					+ " order by rno desc "
				+ " ) "
			+ " ) where rnum between ? and ? ";
	
	final String TOTALROW = " select count(*) from event_reply "
							+ " where no = ? ";
	final String WRITE =" insert into event_reply(rno, no, content, id) "
				+ "values(event_reply_seq.nextval, ?, ?, ?)";
	final String UPDATE =" update event_reply "
			+ " set content = ? where rno = ?";
	final String DELETE =" delete from event_reply "
			+ " where rno = ? ";
	
	
}
