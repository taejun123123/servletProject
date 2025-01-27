package com.musaic.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.musaic.music.vo.MusicVO;
import com.musaic.main.dao.DAO;
import com.musaic.util.db.DB;
import com.webjjang.util.page.PageObject;

public class MusicDAO extends DAO {

    // 1. 리스트 처리
    public List<MusicVO> list(PageObject pageObject) throws Exception {
        List<MusicVO> list = null;
        try {
            con = DB.getConnection();
            String sql = getListSQL(pageObject);
            
            System.out.println(getListSQL(pageObject));
            
            pstmt = con.prepareStatement(sql);

            int idx = 0;
            idx = setSearchData(pageObject, pstmt, idx);
            pstmt.setLong(++idx, pageObject.getStartRow());
            pstmt.setLong(++idx, pageObject.getEndRow());

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    if (list == null)
                        list = new ArrayList<>();
                    MusicVO vo = new MusicVO();
                    vo.setMusicNo(rs.getLong("musicNo"));
                    vo.setAlbumNo(rs.getLong("albumNo"));
                    vo.setMusicTitle(rs.getString("musicTitle"));
                    vo.setMusicFile(rs.getString("musicFile"));
                    vo.setSongWriter(rs.getString("songWriter"));
                    vo.setLyricist(rs.getString("lyricist"));
                    vo.setSinger(rs.getString("singer"));
                    vo.setMusicStatus(rs.getString("musicStatus"));
                    vo.setHit(rs.getLong("hit"));
                    vo.setImage(rs.getString("image"));
                    list.add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DB.close(con, pstmt, rs);
        }

        return list;
    }

    public List<MusicVO> getMusicSortedByHit() throws SQLException {
        List<MusicVO> musicList = null;
        String sql = "SELECT m.musicNo, m.musicTitle, m.singer, m.musicStatus, m.hit, a.image " +
                "FROM music m " +
                "JOIN album a ON m.albumNo = a.albumNo " + " and musicStatus != '비공개'"
                + "ORDER BY m.hit DESC";

        try {
            con = DB.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    if (musicList == null)
                        musicList = new ArrayList<>();
                    MusicVO vo = new MusicVO();
                    vo.setMusicNo(rs.getLong("musicNo"));
                    vo.setMusicTitle(rs.getString("musicTitle"));
                    vo.setSinger(rs.getString("singer"));
                    vo.setMusicStatus(rs.getString("musicStatus"));
                    vo.setHit(rs.getLong("hit"));
                    vo.setImage(rs.getString("image"));
                    musicList.add(vo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(con, pstmt, rs);
        }

        return musicList;
    }

    // 1-2. 전체 데이터 개수 처리
    public Long getTotalRow(PageObject pageObject) throws Exception {
        Long totalRow = null;
        try {
            con = DB.getConnection();
            String sql = TOTALROW + getSearch(pageObject) + statusList(pageObject.getAcceptMode());
            pstmt = con.prepareStatement(sql);
            int idx = 0;
            idx = setSearchData(pageObject, pstmt, idx);
            rs = pstmt.executeQuery();

            if (rs != null && rs.next()) {
                totalRow = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("SQL 예외 발생: " + e.getMessage());
        } finally {
            DB.close(con, pstmt, rs);
        }

        return totalRow;
    }


    // 2. 음원 보기 처리
    public MusicVO view(Long musicNo) throws Exception {
        MusicVO vo = null;
        try {
            con = DB.getConnection();
            pstmt = con.prepareStatement(VIEW);
            pstmt.setLong(1, musicNo);
            rs = pstmt.executeQuery();

            if (rs != null && rs.next()) {
                vo = new MusicVO();
                vo.setMusicNo(rs.getLong("musicNo"));
                vo.setMusicTitle(rs.getString("musicTitle"));
                vo.setSongWriter(rs.getString("songWriter"));
                vo.setLyricist(rs.getString("lyricist"));
                vo.setSinger(rs.getString("singer"));
                vo.setLyric(rs.getString("lyric"));
                vo.setMusicFile(rs.getString("musicFile"));
                vo.setAlbumNo(rs.getLong("albumNo"));
                vo.setRelease_Date(rs.getDate("release_Date"));
                vo.setGenre(rs.getString("genre"));
                vo.setImage(rs.getString("image"));
                vo.setMusicStatus(rs.getString("musicStatus"));
                vo.setTitle(rs.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DB.close(con, pstmt, rs);
        }

        return vo;
    }

    // 3. 음원 등록 처리
    public int write(MusicVO vo) throws Exception {
        int result = 0;
        try {
            con = DB.getConnection();
            pstmt = con.prepareStatement(WRITE);
            pstmt.setString(1, vo.getMusicTitle());
            pstmt.setString(2, vo.getSinger());
            pstmt.setString(3, vo.getSongWriter());
            pstmt.setString(4, vo.getLyricist());
            pstmt.setString(5, vo.getLyric());
            pstmt.setString(6, vo.getMusicFile());
            pstmt.setLong(7, vo.getAlbumNo());
            pstmt.setLong(8, vo.getIncludedNo());
            result = pstmt.executeUpdate();
            System.out.println("음원 등록이 완료 되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("예외 발생 : 음원 등록 DB 처리 중 예외가 발생했습니다.");
        } finally {
            DB.close(con, pstmt);
        }

        return result;
    }

    // 4. 음원 수정 처리
    public int update(MusicVO vo) throws Exception {
        int result = 0;
        try {
            con = DB.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, vo.getMusicTitle());
            pstmt.setString(2, vo.getSinger());
            pstmt.setString(3, vo.getSongWriter());
            pstmt.setString(4, vo.getLyricist());
            pstmt.setString(5, vo.getLyric());
            pstmt.setString(6, vo.getMusicFile());
            pstmt.setLong(7, vo.getAlbumNo());
            pstmt.setLong(8, vo.getIncludedNo());
            pstmt.setLong(9, vo.getMusicNo());
            result = pstmt.executeUpdate();
            if (result == 0) {
                throw new Exception("예외 발생 : 음원 번호나 정보가 맞지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("예외 발생"))
                throw e;
            else
                throw new Exception("예외 발생 : 음원 수정 DB 처리 중 예외가 발생했습니다.");
        } finally {
            DB.close(con, pstmt);
        }

        return result;
    }

    // 5-1. 음원 삭제 처리
    public int changeStatus(MusicVO vo) throws Exception {
        int result = 0;
        try {
            con = DB.getConnection();
            pstmt = con.prepareStatement(CHANGESTATUS);
            pstmt.setString(1, vo.getMusicStatus());
            pstmt.setLong(2, vo.getMusicNo());
            result = pstmt.executeUpdate();
            if (result == 0) {
                throw new Exception("예외 발생 : 음원 번호가 맞지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("예외 발생"))
                throw e;
            else
                throw new Exception("예외 발생 : 음원 삭제 DB 처리 중 예외가 발생했습니다.");
        } finally {
            DB.close(con, pstmt);
        }

        return result;
    }

    // 6. 조회수 증가 처리
    public boolean increaseHit(Long musicNo) {
        String sql = "UPDATE music SET hit = hit + 1 WHERE musicNo = ?";
        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, musicNo);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 쿼리 문자열 선언
    final String LIST = " SELECT musicNo, albumNo, musicTitle, musicFile, songWriter, lyricist, singer,  musicStatus, hit, image "
    		+ " FROM "
    		+ " (  SELECT rownum AS rnum, musicNo, albumNo, musicTitle, musicFile, songWriter, lyricist, singer,  musicStatus, hit, image "
    		+ " FROM "
    		+ " (  SELECT m.musicNo, a.albumNo, m.musicTitle, m.musicFile, m.songWriter, m.lyricist, m.singer,  m.musicStatus, m.hit, a.image "
    		+ " FROM music m, album a "
    		+ " where m.albumNo = a.albumNo "
    		+ " and 1=1 ";

    final String TOTALROW = "SELECT COUNT(*) FROM music m JOIN album a ON m.albumNo = a.albumNo WHERE 1=1";

    final String VIEW = "SELECT m.musicNo, m.musicTitle, m.songWriter, m.lyricist, m.singer, m.lyric, m.musicFile, a.albumNo, a.release_Date, a.genre, a.image, m.musicStatus, a.title "
            + "FROM music m JOIN album a ON m.albumNo = a.albumNo WHERE m.musicNo = ?";

    final String WRITE = "INSERT INTO music (musicNo, musicTitle, singer, songWriter, lyricist, lyric, musicFile, albumNo, includedNo ) VALUES (music_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ? )";

    final String UPDATE = "UPDATE music SET musicTitle = ?, singer = ?, songWriter = ?, lyricist = ?, lyric = ?, musicFile = ?, albumNo = ?, includedNo = ? WHERE musicNo = ?";

    final String CHANGESTATUS = "UPDATE music SET MusicStatus = ? WHERE musicNo = ?";
    
    final String INCREASE = " UPDATE music SET hit = hit + 1 WHERE musicNo = ? ";
    
    private String getListSQL(PageObject pageObject) {
        String sql = LIST;
        
        sql += getSearch(pageObject);
       sql += statusList(pageObject.getAcceptMode());
        sql += " ORDER BY musicNo DESC "
                + " ) "
                + " ) WHERE rnum BETWEEN ? AND ? ";
        return sql;
        
    }
    private String statusList(int gradeNo) {
    	String sql = "";
    	 if (gradeNo != 9) {
 			sql += " and musicStatus != '비공개' ";
 		}
    	 return sql;
    }

    private String getSearch(PageObject pageObject) {
        StringBuilder sql = new StringBuilder();
        String key = pageObject.getKey();
        String word = pageObject.getWord();

        if (word != null && !word.isEmpty()) {
            sql.append(" AND (1=0 "); // 기본적으로 false로 설정
            if (key.contains("t"))
                sql.append(" OR UPPER(musicTitle) LIKE UPPER (?) ");
            if (key.contains("s"))
                sql.append(" OR UPPER(singer) LIKE UPPER (?) ");
            if (key.contains("l"))
                sql.append(" OR UPPER (lyric) LIKE UPPER (?) ");
            sql.append(")"); // AND (1=0 ... )
        }
        return sql.toString();
    }


    private int setSearchData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {
        String key = pageObject.getKey();
        String word = pageObject.getWord();

        if (word != null && !word.isEmpty()) {
            if (key.contains("t"))
                pstmt.setString(++idx, "%" + word + "%");
            if (key.contains("s"))
                pstmt.setString(++idx, "%" + word + "%");
            if (key.contains("l"))
                pstmt.setString(++idx, "%" + word + "%");
        }
        return idx;
    }

    
 // 담기 버튼 카운트 메서드
 	public boolean increaseHitCount(Long musicNo) throws SQLException {
 		try {
 			con = DB.getConnection();
 			pstmt = con.prepareStatement(INCREASE);
 			pstmt.setLong(1, musicNo);

 			int result = pstmt.executeUpdate();
 			System.out.println("Increase hit count result: " + result); // 로그 추가
 			return result > 0;
 		} catch (SQLException e) {
 			e.printStackTrace();
 			return false;
 		} finally {
 			DB.close(con, pstmt);
 		}
 	}
}

