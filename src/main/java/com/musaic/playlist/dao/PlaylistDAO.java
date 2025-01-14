package com.musaic.playlist.dao;

import java.util.ArrayList;
import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.playlist.vo.PlaylistVO;
import com.musaic.util.db.DB;

public class PlaylistDAO extends DAO {
	public List<PlaylistVO> list(String id) throws Exception{
		List<PlaylistVO> list = null;
		try {
			// 드라이버 확인
			// 연결
			con = DB.getConnection();
			// sql - 아래 LSIT
			System.out.println("PlaylistDAO.list().LIST = " + LIST);
		/// 검색에 대한 데이터 세팅 - list()만 사용
			pstmt = con.prepareStatement(LIST);
			pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs!= null) {
			while(rs.next()) {
				// rs -> vo -> list
				// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
				if(list == null)
					list= new ArrayList<PlaylistVO>();
				PlaylistVO vo = new PlaylistVO();
				vo.setMusicNo(rs.getLong("musicNo"));
				vo.setPtime(rs.getString("ptime"));
				vo.setMusictitle(rs.getString("Musictitle"));
				vo.setSinger(rs.getString("singer"));
				vo.setImage(rs.getString("image"));
				vo.setMusicFile(rs.getString("musicFile"));
				vo.setPlaylistNo(rs.getLong("playlistNo"));
				list.add(vo);
			} //end of while
		} // end of if
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}
	// 2-2. 글보기 처리
		// BoardController - BoardListService - [BoardDAO.list()]
		public PlaylistVO view(Long musicno) throws Exception {

			//
			PlaylistVO vo = null;
			try {
				// 1. 드라이버 확인
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 LIST
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(VIEW);
				System.out.println("view" + VIEW);
				pstmt.setLong(1, musicno);
				// 5. 실행
				rs = pstmt.executeQuery();
				// 6. 표시 또는 담기
				if (rs != null && rs.next()) {
					// rs -> vo
					vo = new PlaylistVO();
					vo.setMusicNo(rs.getLong("musicNo"));
					vo.setSinger(rs.getString("singer"));
					vo.setPtime(rs.getString("ptime"));
					vo.setMusictitle(rs.getString("title"));
					vo.setImage(rs.getString("Image"));
					vo.setMusicFile(rs.getString("musicFile"));
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
		
		// 2-2. 글보기 처리
		// BoardController - BoardListService - [BoardDAO.list()]
		public int wrtie(PlaylistVO vo) throws Exception {
			
			//
			int result = 0;
			try {
				// 1. 드라이버 확인
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 LIST
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(WRITE);
				pstmt.setLong(1, vo.getMusicNo());
				pstmt.setLong(2, vo.getAlbumNo());
				pstmt.setString(3, vo.getId());
				// 5. 실행
				result = pstmt.executeUpdate();
				// 6. 표시 또는 담기				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("예외 발생: 음원 추가 DB 처리 중 예외가 발생했습니다.");
			} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			} // end of try ~ catch ~ finally
			// 결과 데이터를 리턴해 준다.
			return result;
		} // end of view()
				
		// 2-2. 글보기 처리
		// BoardController - BoardListService - [BoardDAO.list()]
		public int multiWrtie(PlaylistVO vo) throws Exception {
			
			//
			int result = 0;
			try {
				// 1. 드라이버 확인
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 LIST
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(MULTIWRITE);
				pstmt.setString(1, vo.getId());
				pstmt.setLong(2, vo.getAlbumNo());
				// 5. 실행
				result = pstmt.executeUpdate();
				// 6. 표시 또는 담기				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("예외 발생: 음원 추가 DB 처리 중 예외가 발생했습니다.");
			} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			} // end of try ~ catch ~ finally
			// 결과 데이터를 리턴해 준다.
			return result;
		} // end of view()
		
		public int delete(long[] playlistNos) throws Exception {
			// 결과를 저장할 수 있는 변수 선언.
			int result = 0;

			try {
				// 1. 드라이버 확인
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 UPDATE
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(DELETE + in(playlistNos));
				// 5. 실행 - update: executeUpdate() -> int 결과가 나옴
				result = pstmt.executeUpdate();
				// 6. 표시 또는 담기
				if (result == 0) { // 글번호가 존재하지 않거나 비번 틀림. -> 예외룰 처리한다.
					throw new Exception("예외 발생: 음원 지우기 중 DB 처리 중 예외가 발생했습니다. ");
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 특별한 예외는 그냥 전달한다.
				// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼 수 있는 예외로 만들어 전달한다.
				throw new Exception(" 곡 삭제가 안되었습니다 ");
			} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			}
			//
			return result;
		} // end of delete()
		
		public int musicdelete(PlaylistVO vo) throws Exception {
			// 결과를 저장할 수 있는 변수 선언.
			int result = 0;
			
			try {
				// 1. 드라이버 확인
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 UPDATE
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(MUSICDELETE);
				pstmt.setLong(1, vo.getMusicNo());
				pstmt.setString(2, vo.getId());
				// 5. 실행 - update: executeUpdate() -> int 결과가 나옴
				result = pstmt.executeUpdate();
				// 6. 표시 또는 담기
			} catch (Exception e) {
				e.printStackTrace();
				// 특별한 예외는 그냥 전달한다.
				// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼 수 있는 예외로 만들어 전달한다.
				throw new Exception(" 곡 삭제가 안되었습니다 ");
			} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			}
			//
			return result;
		} // end of delete()
		
		public int albumdelete(PlaylistVO vo) throws Exception {
			// 결과를 저장할 수 있는 변수 선언.
			int result = 0;
			
			try {
				// 1. 드라이버 확인
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 UPDATE
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(ALBUMDELETE);
				pstmt.setLong(1, vo.getAlbumNo());
				pstmt.setString(2, vo.getId());
				// 5. 실행 - update: executeUpdate() -> int 결과가 나옴
				result = pstmt.executeUpdate();
				// 6. 표시 또는 담기
			} catch (Exception e) {
				e.printStackTrace();
				// 특별한 예외는 그냥 전달한다.
				// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼 수 있는 예외로 만들어 전달한다.
				throw new Exception(" 곡 삭제가 안되었습니다 ");
			} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			}
			//
			return result;
		} // end of delete()
		
		 private String in(long[] longs) {
		      String sql ="in (";
		      for(int i=0;i<longs.length;i++) {
		          if(i==0) {
		             sql += longs[i];
		          } else {
		             sql += ","+longs[i];
		          }
		       }
		      sql += ")";
		      return sql;
		   }
		   

	final String LIST = "" + " select pl.playlistNo, m.musicNo, m.singer, pl.ptime, m.musictitle, m.musicFile ,a.image "
			+ "from music m, playList pl, album a where id=? and (m.musicNo=pl.musicNo) and (a.albumNo=pl.albumno) order by pl.playListNo"
			+ " ";
	final String VIEW = "" + " select m.musicNo, m.singer, pl.ptime, pl.title, al.image, m.musicFile from music m ,playList pl where 1=1 and (m.musicNo=pl.playListNo)and playListNo= ? ";

	final String WRITE = "" + " insert into Playlist" + " ( playListNo, musicNo, albumNo, id)"
			+ " values(playList_seq.nextval, ?, ?, ? )";
	
	final String MULTIWRITE = "" + "insert into Playlist (playListNo, musicNo, albumNo, id) "
			+ " select playList_seq.nextval, musicNo, albumNo, (select id from member where id = ? ) id from music where albumNo = ? and musicstatus = '공개' ";

	final String DELETE = "" + " delete from Playlist where playlistNo ";
	
	final String MUSICDELETE = "" + " delete from Playlist where musicNo=? and id=? ";
	
	final String ALBUMDELETE = "" + " delete from Playlist where albumNo=? and id=? ";
	
}
