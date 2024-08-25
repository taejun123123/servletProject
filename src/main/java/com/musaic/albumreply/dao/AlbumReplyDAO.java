package com.musaic.albumreply.dao;

import java.util.ArrayList;
import java.util.List;

import com.musaic.albumreply.vo.AlbumReplyVO;
import com.musaic.main.dao.DAO;
import com.musaic.util.db.DB;
import com.webjjang.util.page.ReplyPageObject;

public class AlbumReplyDAO extends DAO {

	// 필요한 객체 선언 - 상속
	// 접속 정보 - DB 사용 - connection을 가져오게 하는 메소드만 이용

	// 1-1. 리스트
	// albumController - (Execute) - albumListService - [albumDAO.list()]
	public List<AlbumReplyVO> list(ReplyPageObject pageObject) throws Exception {

		List<AlbumReplyVO> list = null;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
		
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(LIST);

			
			// 검색에 대한 데이터 세팅 - list()만 사용 
			pstmt.setLong(1, pageObject.getNo());
			pstmt.setLong(2, pageObject.getStartRow()); // 기본 값 = 1
			pstmt.setLong(3, pageObject.getEndRow());// 기본 값 = 10
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if (rs != null) {
				while (rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if (list == null)
						list = new ArrayList<AlbumReplyVO>();
					// rs -> vo
					AlbumReplyVO vo = new AlbumReplyVO();
					vo.setRno(rs.getLong("rno"));
					vo.setAlbumNo(rs.getLong("albumNo"));
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setRating(rs.getString("rating"));
					vo.setPhoto(rs.getString("photo"));
					

					// vo -> list
					list.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}

		// 결과 데이터를 리턴해준다.
		return list;
	}

	// 1-2 . 전체 댓글 데이터 개수 처리
	// albumController - (Execute) - albumListService - [albumDAO.getTotalRow()]
	public Long getTotalRow(ReplyPageObject pageObject) throws Exception {

		Long totalRow = null;

		try {
			// 1. 드라이버 확인
			// 2. DB 연결
			con = DB.getConnection();

			// 3. sql 아래에 미리 써놓음
			// 4. 실행 객체 & 데이터 세팅

			pstmt = con.prepareStatement(TOTALROW);
			pstmt.setLong(1, pageObject.getNo());
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 및 담기
			if (rs != null && rs.next()) {
				// rs -> rs
				totalRow = rs.getLong(1);

			} // end of if

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		return totalRow;

	}// end of view()

	// 2. 댓글 상세보기는 리스트에서 내용이 다 표시되어 필요하진 않다.

	// 3 . 댓글글등록 처리
	// albumReplyController - (Execute) - albumReplyWriteService - [albumReplyDAO.write(vo)]
	public int write(AlbumReplyVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setLong(1, vo.getAlbumNo());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getRating());
			// 5. 실행 - Update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("*** 댓글등록이 완료 되었습니다. ***");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" 예외 발생: 게시판 글등록 DB처리 중 예외가 발생했습니다. ");

		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}

		// 결과 데이터를 리턴해준다.
		return result;
	} // end of increase()

	// 4 . 글 수정 처리
	// albumController - (Execute) - albumListService - [albumDAO.list()]
	public int update(AlbumReplyVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getRating());
			pstmt.setLong(3, vo.getRno());
			// 5. 실행 - Update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 글번호나 비밀번호가 맞지 않습니다. 정보를 확인해 주세요");

			}

		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼 수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 게시판 글수정 DB 처리 중 예외가 발생했습니다.");

		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}

		// 결과 데이터를 리턴해준다.
		return result;
	} // end of increase()

	// 5 . 글 수정 처리
	// albumController - (Execute) - albumListService - [albumDAO.list()]
	public int delete(AlbumReplyVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETE);

			pstmt.setLong(1, vo.getRno());
			// 5. 실행 - Update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 댓글번호나 비밀번호가 맞지 않습니다. 정보를 확인해 주세요");

			}

		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼 수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 게시판 댓글 삭제 DB 처리 중 예외가 발생했습니다.");

		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해준다.
		return result;
	} // end of delete()

	// 실행항 쿼리를 정의해 놓은 변수 선언.
	
	// 리스트의 페이지 처리 절차 - 원본 -> 순서 번호 -> 해당 페이지 데이터만 가져온다.
	final String LIST = 
		""  + "select rno, albumNo, content, id, name, writeDate, rating, photo "
			+ " from ( "
				+ " select rownum rnum, rno, albumNo, content, id, name, writeDate, rating, photo "
					+ " from ( "
						+ " select  a.rno, a.albumNo, a.content, a.id, m.name, a.rating, " 
						+ " to_char(a.writeDate, 'yyyy-mm-dd HH24:MM:SS') writeDate, m.photo "
						+ " from album_reply a, member m " 
						+ " where albumNo = ? and (a.id = m.id) "
						+ " order by rno desc " 
					+ " ) "
				+ " ) where rnum between ? and ?";
	// 검색이 있는 경우 TOTALROW + search문 
	final String TOTALROW = "select count(*) from album_reply where albumNo = ? ";
		
	
	final String WRITE = " insert into album_reply( " + " rno, albumNo, content, id, rating) "
			+ " values(album_reply_seq.nextval, ?,?,?,?)";

	final String UPDATE = "update album_reply set " + "  content = ? , rating = ? " + " where rno = ?  ";
	final String DELETE = "delete from album_reply " + " where rno = ? ";

}
