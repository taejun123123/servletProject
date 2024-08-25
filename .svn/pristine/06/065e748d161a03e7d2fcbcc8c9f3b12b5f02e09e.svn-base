package com.musaic.cartalbum.dao;

import java.util.ArrayList;
import java.util.List;
import com.musaic.cartalbum.vo.CartAlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.util.db.DB;
import com.webjjang.util.page.PageObject;

public class CartAlbumDAO extends DAO {

    // list() - 장바구니에 있는 앨범 목록을 가져오는 메서드
    public List<CartAlbumVO> list(PageObject pageObject) throws Exception {
        List<CartAlbumVO> list = new ArrayList<>();

        try {
            // 1. 드라이버 확인 - DB
            // 2. 연결
            con = DB.getConnection();
            // 3. sql - LIST 쿼리 사용
            // 4. 실행 객체 & 데이터 세팅
            pstmt = con.prepareStatement(LIST);
            pstmt.setString(1, pageObject.getAccepter()); // memberId 설정
            pstmt.setLong(2, pageObject.getStartRow());   // 시작 행 설정
            pstmt.setLong(3, pageObject.getEndRow());     // 끝 행 설정

            rs = pstmt.executeQuery();

            // 5. 결과 처리 - 결과 집합에서 데이터를 가져와 리스트에 추가
            while (rs.next()) {
                CartAlbumVO vo = new CartAlbumVO();
                vo.setCartNo(rs.getLong("cartNo"));
                vo.setImage(rs.getString("image"));
                vo.setTitle(rs.getString("title"));
                vo.setPrice(rs.getLong("price"));
                vo.setAlbumCnt(rs.getLong("albumCnt"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("앨범 장바구니 리스트를 가져오는 중 오류가 발생했습니다.");
        } finally {
            DB.close(con, pstmt, rs); // 6. 자원 반환
        }

        return list; // 7. 결과 반환
    }

    
    // getTotalRow() - 전체 데이터 개수 가져오는 메서드
    public Long getTotalRow(PageObject pageObject) throws Exception {
        Long totalRow = null;

        try {
            // 1. 드라이버 확인
            // 2. DB 연결
            con = DB.getConnection();
            // 3. sql 준비
            pstmt = con.prepareStatement(TOTALROW);
            pstmt.setString(1, pageObject.getAccepter());
            // 4. 실행
            rs = pstmt.executeQuery();
            // 5. 결과 처리
            if (rs != null && rs.next()) {
                totalRow = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DB.close(con, pstmt, rs);
        }

        return totalRow;
    }

    // 페이지네이션 정보 설정
    public void setPagination(PageObject pageObject) throws Exception {
        Long totalRow = getTotalRow(pageObject);
        if (totalRow != null) {
            pageObject.setTotalRow(totalRow);
        }
    }
    
    // 리스트의 검색만 처리하는 쿼리 - where 
    private String getListSQL(PageObject pageObject) {
        String sql = LIST;
        sql += getSearch(pageObject, false); 
        sql += " order by cartNo asc) where rnum between ? and ?";
        return sql;
    }

    // LIST에 검색을 처리해서 만들어지는 sql문 작성 메서드
    // 리스트의 검색만 처리하는 쿼리 - where
    // list(), getTotalRow()에서 사용한다. list는 where 반드시 넣는다. 이미 있다.
    // getTotalRow() where가 없다. 그래서 검색 있는 경우 where 추가 해야한다.
    private String getSearch(PageObject pageObject, boolean isWhere) {
        String sql = "";
        String key = pageObject.getKey();
        String word = pageObject.getWord();
        if (word != null && !word.equals("")) {
            // where 붙이기 처리
            if (isWhere) sql += " and (";
            else sql += " and (";
            // key 안에 포함된 문자로 검색을 처리한다.
            if (key.indexOf("c") >= 0) sql += " cartNo like ? or";
            if (key.indexOf("t") >= 0) sql += " title like ? or";
            if (key.indexOf("i") >= 0) sql += " image like ? or";
            sql = sql.substring(0, sql.length() - 2); // 마지막 or 제거
            sql += ")";
        }
        return sql;
    }

    
    // write() - 새로운 앨범 장바구니 항목을 추가하는 메서드
    public int write(CartAlbumVO vo) throws Exception {
        int result = 0;

        try {
            // 1. 드라이버 확인 - DB
            // 2. 연결
            con = DB.getConnection();
            // 3. sql - WRITE 쿼리 사용
            // 4. 실행 객체 & 데이터 세팅
            pstmt = con.prepareStatement(WRITE);
            pstmt.setLong(1, vo.getAlbumNo()); // 앨범 번호 설정
            pstmt.setLong(2, vo.getAlbumCnt()); // 앨범 수량 설정
            pstmt.setString(3, vo.getMemberid()); // 회원 ID 설정
            result = pstmt.executeUpdate(); // 5. 쿼리 실행
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("앨범 장바구니 담기 중 오류가 발생했습니다.");
        } finally {
            DB.close(con, pstmt); // 6. 자원 반환
        }

        return result; // 7. 결과 반환
    }

    // update() - 기존 앨범 장바구니 항목을 수정하는 메서드
    public int update(CartAlbumVO vo) throws Exception {
        int result = 0;

        try {
            // 1. 드라이버 확인 - DB
            // 2. 연결
            con = DB.getConnection();
            // 3. sql - UPDATE 쿼리 사용
            // 4. 실행 객체 & 데이터 세팅
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setLong(1, vo.getAlbumCnt()); // 앨범 수량 설정
            pstmt.setLong(2, vo.getCartNo()); // 조건 추가 - 카트 번호 설정
            result = pstmt.executeUpdate(); // 5. 쿼리 실행
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("앨범 장바구니 수정을 가져오는 중 오류가 발생했습니다.");
        } finally {
            DB.close(con, pstmt); // 6. 자원 반환
        }

        return result; // 7. 결과 반환
    }
    

    // delete() - 특정 앨범 장바구니 항목을 삭제하는 메서드
    public int delete(String no) throws Exception {
        int result = 0;

        try {
            // 1. 드라이버 확인 - DB
            // 2. 연결
            con = DB.getConnection();
            // 3. sql - DELETE 쿼리 사용
            // 4. 실행 객체 & 데이터 세팅
            pstmt = con.prepareStatement(DELETE);
            pstmt.setString(1, no); // 카트 번호 설정
            result = pstmt.executeUpdate(); // 5. 쿼리 실행
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("앨범 장바구니 삭제 중 오류가 발생했습니다.");
        } finally {
            DB.close(con, pstmt); // 6. 자원 반환
        }

        return result; // 7. 결과 반환
    }

    // minus() - 특정 앨범 장바구니 항목의 수량을 감소시키는 메서드
    public int minus(Long no) throws Exception {
        int result = 0;

        try {
            // 1. 드라이버 확인 - DB
            // 2. 연결
            con = DB.getConnection();
            // 3. sql - MINUS 쿼리 사용
            // 4. 실행 객체 & 데이터 세팅
            pstmt = con.prepareStatement(MINUS);
            pstmt.setLong(1, no); // 카트 번호 설정
            result = pstmt.executeUpdate(); // 5. 쿼리 실행
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("앨범 장바구니 수량 감소 중 오류가 발생했습니다.");
        } finally {
            DB.close(con, pstmt); // 6. 자원 반환
        }

        return result; // 7. 결과 반환
    }

    // add() - 특정 앨범 장바구니 항목의 수량을 증가시키는 메서드
    public int add(Long no) throws Exception {
        int result = 0;

        try {
            // 1. 드라이버 확인 - DB
            // 2. 연결
            con = DB.getConnection();
            // 3. sql - ADD 쿼리 사용
            // 4. 실행 객체 & 데이터 세팅
            pstmt = con.prepareStatement(ADD);
            pstmt.setLong(1, no); // 카트 번호 설정
            result = pstmt.executeUpdate(); // 5. 쿼리 실행
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("앨범 장바구니 수량 증가 중 오류가 발생했습니다.");
        } finally {
            DB.close(con, pstmt); // 6. 자원 반환
        }

        return result; // 7. 결과 반환
    }

    // checkalbumNo() - 앨범 번호를 체크하여 장바구니 항목을 조회하는 메서드
    public CartAlbumVO checkalbumNo(CartAlbumVO vo) throws Exception {
        
        try {
            // 1. 드라이버 확인 - DB
            // 2. 연결
            con = DB.getConnection();
            // 3. sql - CHECKALBUMNO 쿼리 사용
            // 4. 실행 객체 & 데이터 세팅
            pstmt = con.prepareStatement(CHECKALBUMNO);
            pstmt.setLong(1, vo.getAlbumNo()); // 앨범 번호 설정
            pstmt.setString(2, vo.getMemberid());
            
            // 5. 실행
            rs = pstmt.executeQuery();
            // 6. 표시 또는 담기 - 결과 집합에서 데이터를 가져와 VO에 설정
            vo = new CartAlbumVO();
            if (rs != null && rs.next()) {
                vo.setAlbumNo(rs.getLong("albumNo"));
                vo.setCartNo(rs.getLong("cartNo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DB.close(con, pstmt, rs); // 7. 자원 반환
        }

        return vo; // 8. 결과 반환
    }
    

    // SQL 쿼리 상수 정의
    private final String LIST = 
            "SELECT cartNo, image, title, price, albumCnt " +
            "FROM ( " +
            "    SELECT ca.cartNo, a.image, a.title, a.price, ca.albumCnt, ROWNUM as rnum " + 
            "    FROM album a, cartAlbum ca " +
            "    WHERE ca.albumNo = a.albumNo and ca.memberid = ? " +
            "    ORDER BY ca.cartNo ASC " +
            ") " +
            "WHERE rnum BETWEEN ? AND ?";

     final String TOTALROW = 
            "SELECT count(*) FROM cartAlbum WHERE memberid = ?";

    final String WRITE = 
        "INSERT INTO cartAlbum (cartNo, albumNo, albumCnt, memberid) VALUES (cartAlbum_seq.nextval, ?, ?, ?)";

    final String UPDATE = 
        "UPDATE cartAlbum SET albumCnt = albumCnt + ? WHERE cartNo = ?";
  
    final String DELETE = 
        "DELETE FROM cartAlbum WHERE cartNo = ?";

    final String MINUS = 
        "UPDATE cartAlbum SET albumCnt = albumCnt - 1 WHERE cartNo = ?";

    final String ADD = 
        "UPDATE cartAlbum SET albumCnt = albumCnt + 1 WHERE cartNo = ?";
    
    final String CHECKALBUMNO = 
    		" select albumNo,cartNo from cartAlbum "
    				+ " where albumNo = ? and memberid = ? ";
}
