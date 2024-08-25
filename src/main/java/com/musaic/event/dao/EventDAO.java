package com.musaic.event.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.musaic.event.vo.EventVO;
import com.musaic.main.dao.DAO;

import com.musaic.util.db.DB;
import com.webjjang.util.page.PageObject;

public class EventDAO extends DAO {

	// 필요한 객체 선언 - 상속
	// 접속 정보 - DB 사용 - connection을 가져오게 하는 메서드만 이용

	// 1-1. 리스트 처리
	// EventController - (Execute) - EventListService - [EventDAO.list()]
	public List<EventVO> list(PageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		List<EventVO> list = null;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			// pstmt = con.prepareStatement(LIST);
			pstmt = con.prepareStatement(getListSQL(pageObject));
			System.out.println("getListSQL = " + getListSQL(pageObject));
			// 검색에 대한 데이터 세팅 - list()만 사용
			int idx = 0; // pstmt의 순서번호 사용. 먼저 1 증가하고 사용한다.
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow()); // 기본 값 = 1
			pstmt.setLong(++idx, pageObject.getEndRow()); // 기본 값 = 10
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if (rs != null) {
				while (rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if (list == null)
						list = new ArrayList<EventVO>();
					// rs -> vo
					EventVO vo = new EventVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setAnocDate(rs.getString("anocDate"));
					vo.setImage(rs.getString("image"));

					// vo -> list
					list.add(vo);
				} // end of while
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		// 결과 데이터를 리턴해 준다.
		return list;
	} // end of list()
		// 1-2. 전체 데이터 개수 처리
		// EventController - (Execute) - EventListService - [EventDAO.getTotalRow()]

	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		Long totalRow = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 TOTALROW
			System.out.println("EventDAO.getTotalRow().sql=" + TOTALROW + getSearch(pageObject) + getPeriod(pageObject) );
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(TOTALROW + getSearch(pageObject) + getPeriod(pageObject));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		// 결과 데이터를 리턴해 준다.
		return totalRow;
	} // end of getTotalRow()

	// 2. 이벤트보기 처리
		// EventController - (Execute) - EventListService - [EventDAO.view()]
		public EventVO view(Long no) throws Exception {
			// 결과를 저장할 수 있는 변수 선언.
			EventVO vo = null;
			try {
				// 1. 드라이버 확인 - DB
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 LIST
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(VIEW);
				pstmt.setLong(1, no);
				// 5. 실행
				rs = pstmt.executeQuery();
				// 6. 표시 또는 담기
				if (rs != null && rs.next()) {
					// rs -> vo
					vo = new EventVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setImage(rs.getString("image"));
					vo.setContent(rs.getString("content"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setWriteDate(rs.getString("writeDate"));

				} // end of if
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				// 7. 닫기
				DB.close(con, pstmt, rs);
			} // end of try ~ catch ~ finally

			// 결과 데이터를 리턴해 준다.
			return vo;
		} // end of view()

	// 3. 등록 처리
	// EventController - (Execute) - EventWriteService - [EventDAO.write()]
	public int write(EventVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getImage());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getStartDate());
			pstmt.setString(5, vo.getEndDate());
			pstmt.setString(6, vo.getAnocDate());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("*** 등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 이벤트 게시판 등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of write()

    
	// 4. 이벤트 수정 처리
	// EventController - (Execute) - EventUpdateService - [EventDAO.update()]
	public int update(EventVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			pstmt.setString(5, vo.getAnocDate());
			pstmt.setLong(6, vo.getNo());

			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 번호가 맞지 않거나 본인 글이 아닙니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 이벤트 게시판 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of update()

	// 5. 이벤트 삭제 처리
	// EventController - (Execute) - EventDeleteService - [EventDAO.delete()]
	public int delete(EventVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않거나 비번 틀림. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 번호가 맞지 않거나 본인 글이 아닙니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 게시판 글삭제 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of delete()
	
	// 6. 이미지 바꾸기 처리
		// EventController - (Execute) - EventChangeService - [EventDAO.changeImage()]
		public int changeImage(EventVO vo) throws Exception {
			// 결과를 저장할 수 있는 변수 선언.
			int result = 0;

			try {
				// 1. 드라이버 확인 - DB
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 UPDATE
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(CHANGEIMAGE);
				pstmt.setString(1, vo.getImage());
				pstmt.setLong(2, vo.getNo());
				// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
				result = pstmt.executeUpdate();
				// 6. 표시 또는 담기
				if (result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
					throw new Exception("예외 발생 : 글번호나 비밀번호가 맞지 않습니다. 정보를 확인해 주세요.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 특별한 예외는 그냥 전달한다.
				if (e.getMessage().indexOf("예외 발생") >= 0)
					throw e;
				// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
				else
					throw new Exception("예외 발생 : 이미지 바꾸기 DB 처리 중 예외가 발생했습니다.");
			} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			}

			// 결과 데이터를 리턴해 준다.
			return result;
		} // end of update()


	// 리스트의 페이지 처리 절차 - 원본 -> 순서 번호 -> 해당 페이지 데이터만 가져온다.
	final String LIST = "" 
			+ " select no, title, anocDate, image " 
			+ " from ( "
				+ " select rownum rnum, no, title, " 
				+ " anocDate, image " 
				+ " from ( "
					+ " select no, title, " 
					+ " to_char(anocDate, 'yyyy-mm-dd') anocDate, "
					+ " image  "
					+ " from event "
					+ " where (1 = 1) "
			// where 1=1 and (일반조건) and (조인조건)
	// 여기에 검색이 있어야 합니다.
	;
	// 검색이 있는 경우 TOTALROW + search문
	final String TOTALROW = " select count(*) from event where (1 = 1) ";

	// LIST에 검색을 처리해서 만들지는 sql문 작성 메서드
	private String getListSQL(PageObject pageObject) {
		String sql = LIST;
		// 검색 쿼리 추가
		sql += getSearch(pageObject);
		sql += getPeriod(pageObject);
		sql += " order by writeDate desc, no desc" 
				+ " ) " 
				+ " ) where rnum between ? and ? ";
		return sql;
	}

	// 리스트의 검색만 처리하는 쿼리 - where
	// list(), getTotalRow() 에서 사용한다. list는 where 반드시 넣는다. 이미 있다.
	// getTotalRow() where가 없다. 그래서 검색 있는 경우 where 추가해야한다.
	private String getSearch(PageObject pageObject) {
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			// where 붙이기 처리
			sql += " and ( 1=0 ";
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if (key.indexOf("t") >= 0) sql += " or title like ? ";
			if (key.indexOf("c") >= 0) sql += " or content like ? ";
			sql += " ) ";
		}
		return sql;

	}
	
	private String getPeriod(PageObject pageObject) {
	    String sql = "";
	    String period = pageObject.getPeriod();
	    System.out.println("DAO=" + period);
	    sql += " and ( 1 = 1 ";
	    
	    // period에 따라서 기간을 검색한다.
	    if (period.equals("pre_old")) // 현재 공지
	        sql += " and (sysdate between startDate and endDate "
	            + " or sysdate > endDate) ";
	    else if (period.equals("pre")) // 현재 공지
	        sql += " and sysdate between startDate and endDate ";
	    else if (period.equals("old")) // 지난 공지
	        sql += " and sysdate > endDate ";
	    else if (period.equals("res")) // 예약 공지
	        sql += " and sysdate < startDate ";
	    else // 모든 공지
	        sql += ""; 

	    sql += ")";
	    return sql;
	}


	
	// 검색 쿼리의 ? 데이터를 세팅하는 메서드
	private int setSearchData(PageObject pageObject,
			PreparedStatement pstmt, int idx) throws SQLException {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if (key.indexOf("t") >= 0) pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("c") >= 0) pstmt.setString(++idx, "%" + word + "%");
		}
		return idx;
	}

	final String VIEW = "SELECT no, title, image, content, "
	        + " TO_CHAR(startDate, 'yyyy-MM-dd HH24:mi') startDate, "
	        + " TO_CHAR(endDate, 'yyyy-MM-dd HH24:mi') endDate, "
	        + " TO_CHAR(writeDate, 'yyyy-MM-dd') writeDate "
	        + " FROM event "
	        + " WHERE no = ?";


	final String WRITE = "insert into event " 
            + " (no, title, image, content, startDate, endDate, anocDate) "
            + " values(event_seq.nextval, ?, ?, ?, ?, ?, ?)";


	final String UPDATE = " update event " 
			+ " set title = ?, content = ?, startDate = ?, endDate = ?, anocDate = ?, updateDate = sysDate " 
			+ " where no = ? ";

	final String DELETE = "delete from event " 
			+ " where no = ? ";
	
	final String CHANGEIMAGE = "update event " + " set image= ? " + " where no = ? ";


}