package com.musaic.notice.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.notice.vo.NoticeVO;
import com.musaic.util.db.DB;import com.opensymphony.module.sitemesh.Page;
import com.webjjang.util.page.PageObject;

public class NoticeDAO extends DAO{

	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		List<NoticeVO> list = null;
		
		try {
			// DB 연결
			con = DB.getConnection();
			// sql 
			pstmt = con.prepareStatement(getListSQL(pageObject));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow()); // 기본 값 = 1
			pstmt.setLong(++idx, pageObject.getEndRow()); // 기본 값 = 10
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 표시 또는 담기
			if(rs != null) {
				while(rs.next()) {
					// rs -> vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 함
					if(list == null) list = new ArrayList<NoticeVO>();
					// rs -> vo
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setWriteDate(rs.getString("writeDate"));
					
					list.add(vo);
				} // end of while()
			} // end of if()
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try~catch~finally
		
		return list;
		
	} // end of list()
	
	public Long getTotalRow (PageObject pageObject) throws Exception{
		Long totalRow = null;
		
		try {
			con = DB.getConnection();
			
			pstmt = con.prepareStatement(TOTALROW + getSearch(pageObject) + getPeriod(pageObject));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			// 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
		
		return totalRow;
	} // end of getTotalRow()
	
	public NoticeVO view(Long no) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		NoticeVO vo = null;
		
		try {
			//연결
			con = DB.getConnection();
			// sql - 아래 VIEW
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			// 실행
			rs = pstmt.executeQuery();
			// 표시 또는 담기
			if(rs != null && rs.next()) {
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setUpdateDate(rs.getString("updateDate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try~catch~finally
		
		return vo;
	} // end of view()
	
	public int write(NoticeVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.

		int result = 0;
		
		try {
			//연결
			con = DB.getConnection();
			// sql - 아래 WRITE
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			// 실행
			result = pstmt.executeUpdate();
			
			System.out.println("공지 등록 성공!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt);
		} // end of try~catch~finally
		
		return result;
	} // end of write()
	
	public int update(NoticeVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.

		int result = 0;
		
		try {
			//연결
			con = DB.getConnection();
			// sql - 아래 UPDATE
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			pstmt.setLong(5, vo.getNo());
			// 실행
			result = pstmt.executeUpdate();
			
			System.out.println("공지 수정 성공!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt);
		} // end of try~catch~finally
		
		return result;
	} // end of update()
	
	public int delete(Long no) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.

		int result = 0;
		
		try {
			//연결
			con = DB.getConnection();
			// sql - 아래 DELETE
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			// 실행
			result = pstmt.executeUpdate();
			
			System.out.println("공지 수정 성공!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt);
		} // end of try~catch~finally
		
		return result;
	} // end of delete()
	
	// 실행할 쿼리를 정의해 놓은 변수 선언.
	final String LIST = ""
			+ " select no, title, startDate, endDate, writeDate "
			+ " from ( "
				+ " select rownum rnum, no, title, startDate, endDate, writeDate "
				+ " from ( "
					+ "select no, title, "
					+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
					+ " to_char(endDate, 'yyyy-mm-dd') endDate, "
					+ " to_char(writeDate, 'yyyy-mm-dd') writeDate "
					+ " from notice "
					+ " where (1 = 1) "
					;
					//검색부분

		// 검색이 있는 경우 TOTALROW + search문
		final String TOTALROW = "select count(*) from notice where ( 1 = 1 ) ";
		
		// LIST에 검색을 처리해서 만들지는 sql문 작성 메서드
		private String getListSQL(PageObject pageObject) {
			String sql = LIST;
			sql += getSearch(pageObject);
			sql += getPeriod(pageObject);
			sql += " order by updateDate desc, no desc"
					+ " ) "
					+ " ) where rnum between ? and ? ";
			return sql;
		} // end of getListSQL()
		
		// 리스트의 검색만 처리하는 쿼리 - where
		private String getSearch(PageObject pageObject) {
			String sql = "";
			String key = pageObject.getKey();
			String word = pageObject.getWord();
			if(word != null && !word.equals("")) {
				sql += " and ( 1=0 ";
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
				if(key.indexOf("t") >= 0) sql += " or title like ? ";
				if(key.indexOf("c") >= 0) sql += " or content like ? ";
				sql += ")";
			}
			return sql;
		} // end of getSearch()
		
		// 리스트의 기간 검색만 처리하는 쿼리 - where
		private String getPeriod(PageObject pageObject) {
			String sql = "";
			String period = pageObject.getPeriod();
			sql += " and ( 1 = 1 ";
			// period에 따라서 기간을 검색한다.
			if(period.equals("pre")) // 현재 공지
				sql += " and trunc(sysdate) between trunc(startDate ) and trunc(endDate) ";
			else if(period.equals("old")) // 지난 공지
				sql += " and trunc(sysdate) > trunc(endDate) ";
			else if(period.equals("res")) // 예약 공지
				sql += " and trunc(sysdate) < trunc(startDate) ";
			else sql+= ""; // 모든 공지
			sql += ")";
			return sql;
			
		} // end of getPeriod()
		
		// 검색 쿼리의 ? 데이터를 세팅하는 메서드
		private int setSearchData(PageObject pageObject, 
				PreparedStatement pstmt, int idx) throws SQLException {
			String key = pageObject.getKey();
			String word = pageObject.getWord();
			if(word != null && !word.equals("")) {
				// key안에 t가 포함되어 있으면 title로 검색을 한다.
				if(key.indexOf("t") >= 0) pstmt.setString(++idx, "%" + word + "%");
				if(key.indexOf("c") >= 0) pstmt.setString(++idx, "%" + word + "%");
			}
			return idx;
		} // end of setSearchData()
		
	final String VIEW = " select no, title, content, "
			+ " to_char(startDate,'yyyy-mm-dd') startDate, "
			+ " to_char(endDate,'yyyy-mm-dd') endDate, "
			+ " to_char(writeDate,'yyyy-mm-dd') writeDate, "
			+ " to_char(updateDate,'yyyy-mm-dd') updateDate "
			+ " from notice "
			+ " where no = ? "
			;
	final String WRITE = " insert into notice "
			+ " (no, title, content, startDate, endDate) "
			+ " values(notice_seq.nextval, ?, ?, ?, ?)";
	final String UPDATE = " update notice "
			+ " set title = ?, content = ?, startDate = ?, endDate = ? "
			+ " where no = ?";
	final String DELETE = " delete from notice "
			+ " where no = ?";
	
} // end of class
