package com.musaic.member.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.util.db.DB;
import com.webjjang.util.page.PageObject;

public class MemberDAO extends DAO {
	public List<MemberVO> list(PageObject po) throws SQLException{
		List<MemberVO> list=null;
		
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 데이터 세팅
			pstmt=con.prepareStatement(getLIST(po));
			
			int idx=0;
			idx=setSearchData(idx, po, pstmt);
			pstmt.setLong(++idx, po.getStartRow());
			pstmt.setLong(++idx, po.getEndRow());
			
			//3. 결과객체 생성 및 결과데이터 전달
			rs=pstmt.executeQuery();
			
			if(rs!=null) {
				list=new ArrayList<MemberVO>();
				while(rs.next()) {
					MemberVO vo=new MemberVO();
					vo.setNo(rs.getLong("no"));
					vo.setGradeNo(rs.getLong("gradeno"));
					vo.setGradeName(rs.getString("gradename"));
					vo.setName(rs.getString("Name"));
					vo.setId(rs.getString("id"));
					vo.setEmail(rs.getString("email"));
					vo.setStatus(rs.getString("status"));
					
					list.add(vo);
				}
			}//end of if(rs!=null)
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}//end of try-catch
		System.out.println("MemberDAO.list()--------------------------");
		return list;
	}//end of list()
	
	public Long getTotalRow() throws SQLException {
		Long total=null;
		
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(TOTALROW);
			//3. 결과객체 생성 및 결과 데이터 전달
			rs=pstmt.executeQuery();
			
			if(rs!=null&&rs.next())
				total=rs.getLong(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}//end of try-catch
		
		return total;
	}
	
	public int write(MemberVO vo) throws SQLException {
		int result=0;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getAddress());
			pstmt.setString(9, vo.getPhoto());
			//3. 결과객체 생성 및 결과 데이터 전달
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}//end of try-catch
		
		return result;
	}//end of write()
	
	public LoginVO login(LoginVO vo) throws SQLException {
		LoginVO result=null;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(LOGIN);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			//3. 결과객체 생성 및 결과 데이터 전달
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()) {
				//id, pw, name, photo, gradeno, gradeName,newMsgCnt
				result=new LoginVO();
				result.setId(rs.getString("id"));
				result.setPw(rs.getString("pw"));
				result.setName(rs.getString("name"));
				result.setPhoto(rs.getString("photo"));
				result.setGradeNo(rs.getLong("gradeno"));
				result.setGradeName(rs.getString("gradename"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of login()
	
	// 등급, 상태 **제외** 업데이트
	public int update(MemberVO vo) throws SQLException {
		int result=0;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getBirth());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getPhoto());
			pstmt.setString(8, vo.getId());
			//3. 결과객체 생성 및 결과 데이터 전달
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of update()
	
	public String idCheck(String id) throws SQLException {
		String result=null;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(IDCHECK);
			pstmt.setString(1, id);
			//3. 결과객체 생성 및 결과 데이터 전달
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next())
				result=rs.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of idcheck()
	
	public String emailCheck(String email) throws SQLException {
		String result=null;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(EMAILCHECK);
			pstmt.setString(1, email);
			//3. 결과객체 생성 및 결과 데이터 전달
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next())
				result=rs.getString("email");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of emailcheck()
	
	public String findPw(MemberVO vo) throws SQLException {
		String result=null;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(FINDPW);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getEmail());
			//3. 결과객체 생성 및 결과 데이터 전달
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next())
				result=rs.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of findpw()
	
	public int changePw(MemberVO vo) throws SQLException {
		int result=0;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(CHANGEPW);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getId());
			//3. 결과객체 생성 및 결과 데이터 전달
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of findpw()
	
	public MemberVO view(String id) throws SQLException {
		MemberVO result=null;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(VIEW);
			pstmt.setString(1, id);
			//3. 결과객체 생성 및 결과 데이터 전달
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()) {
				//gender, address
				result=new MemberVO();
				result.setId(rs.getString("id"));
				result.setTel(rs.getString("tel"));
				result.setName(rs.getString("name"));
				result.setEmail(rs.getString("email"));
				result.setBirth(rs.getString("birth"));
				result.setPhoto(rs.getString("photo"));
				result.setGender(rs.getString("gender"));
				result.setAddress(rs.getString("address"));
				result.setGradeNo(rs.getLong("gradeno"));
				result.setGradeName(rs.getString("gradename"));
				//m.status, m.regdate, m.condate -> 관리자용
				result.setStatus(rs.getString("status"));
				result.setRegDate(rs.getString("regdate"));
				result.setConDate(rs.getString("condate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of login()
	
	public String findId(MemberVO vo) throws SQLException {
		String result=null;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(FINDID);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			//3. 결과객체 생성 및 결과 데이터 전달
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next())
				result=rs.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}//end of findid()
	
	public int delete(String id) throws SQLException {
		int result=0;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, id);
			//3. 결과객체 생성 및 결과 데이터 전달
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		return result;
	}//end of delete()
	
	public int changeGrade(MemberVO vo) throws SQLException {
		int result=0;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(CHANGEGRADE);
			pstmt.setLong(1, vo.getGradeNo());
			pstmt.setString(2, vo.getId());
			//3. 결과객체 생성 및 결과 데이터 전달
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		return result;
	}//end of changeGrade()
	
	public int changeStatus(MemberVO vo) throws SQLException {
		int result=0;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(CHANGESTATUS);
			pstmt.setString(1, vo.getStatus());
			pstmt.setString(2, vo.getId());
			//3. 결과객체 생성 및 결과 데이터 전달
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		return result;
	}//end of changeStatus()
	
	public int updateCondate(String id) throws SQLException {
		int result=0;
		try {
			//1. 연결객체 생성 및 연결
			con=DB.getConnection();
			//2. 실행객체 생성 및 연결
			pstmt=con.prepareStatement(UPDATECONDATE);
			pstmt.setString(1, id);
			//3. 결과객체 생성 및 결과 데이터 전달
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		return result;
	}//end of delete()
	
	
	private final String LIST=" select no, id, name, email, gradeno, gradename, status"
			+ " from ("
				+ "	select rownum rnum, no, id, name, email, gradeno, gradename, status"
				+ " from ("
					+ "	select m.no, m.id, m.name, m.email, m.gradeno, g.gradename, m.status"
					+ " from member m, grade g "
					+ " where (1=1) ";
	
	private final String getLIST(PageObject po) {
		String sql=LIST;
		
		String word=po.getWord();
		if(word!=null && !word.equals("")) sql+=getSearch(po);
		
		sql+=" and m.gradeno=g.gradeno order by no "
				+ " ) ) where rnum between ? and ?";
		
		System.out.println("MemberDAO.list()_sql: "+sql);
		return sql;
	}
	
	private final String getSearch(PageObject po) {
		String sql="";
		
		String key=po.getKey();
		String word=po.getWord();
		
		if(word!=null && !word.equals("")) {
			sql+=" and m."+key+" like ? ";			
		}
		return sql;
	}
	
	private final int setSearchData(int idx, PageObject po, PreparedStatement pstmt) throws SQLException {
		String word=po.getWord();
		
		if(word!=null && !word.equals("")) {
			pstmt.setString(++idx, "%"+word+"%");
		}
		
		return idx;
	}
	
	
	private final String WRITE="insert into member(no, id, pw, name, gender, "
			+ "birth, tel, email, address, photo)"
			+ " values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String TOTALROW="select count(*) from member";
	
	
	private final String LOGIN="select m.id, m.pw, m.name, m.photo, m.gradeno, g.gradename"
			+ " from member m, grade g where (m.gradeno=g.gradeno) and (id=? and pw=?) and status='정상'";
	
	
	private final String UPDATECONDATE="update member set condate=sysdate where id=? ";
	
	private final String UPDATE="update member set name=?, birth=?, gender=?, tel=?, "
			+ " email=?, address=?, photo=? where id=? ";
	
	private final String DELETE="update member set status='탈퇴' where id=? ";

	private final String CHANGEPW="update member set pw=? where id=? ";
	private final String CHANGEGRADE="update member set gradeno=? where id=? ";
	private final String CHANGESTATUS="update member set status=? where id=? ";

	
	private final String IDCHECK="select id from member where id=?";
	private final String FINDPW="select id from member where id=? and email=?";
	private final String FINDID="select id from member where name=? and email=?";
	private final String EMAILCHECK="select email from member where email=?";

	
	private final String VIEW="select m.id, m.name, m.gender, m.tel, m.email, m.address,"
			+ " m.gradeno, g.gradename, m.photo, to_char(m.birth, 'yyyy-mm-dd') birth, "
			+ " m.status, to_char(m.regdate, 'yyyy-mm-dd') regdate, m.condate from member m, grade g"
			+ " where m.gradeno=g.gradeno and id=?";
}