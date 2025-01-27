package com.musaic.pay.dao;

import java.util.ArrayList;
import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.pay.vo.OrderVO;
import com.musaic.pay.vo.PayVO;
import com.musaic.util.db.DB;

public class PayDAO extends DAO{
/* 결제 DAO 흐름 
 * 1.결제할때 장바구니 데이터를 DB에서 끄내서 payWrtieForm에 뿌려야됨 + 결제한 내용을 DB에 저장해야됨 
 * 2.결제 끝난 후 사용자에게 리스트를 보여주기 위해 LIST 사용 
 * 3.결제 상세보기에서는 결제한 내용을 상세하게 보여줘야하고 + 결제 수정 + 결제 취소를 여기서 처리해야됨 
 * 4.관리자 리스트에서는 사용자들의 리스트를 보여주고 + 사용자들의 상태를 변경해야한다.  
 * 우선순위 1-2-4-3   
 **/
	// 1-1. 장바구니 데이터 배열 처리 
	   public int payClick(PayVO vo) throws Exception {
	        int result = 0;

	        try {
	            // 데이터베이스 연결
	            con = DB.getConnection();
	            System.out.println(getPayClick(vo.getCartNos()));
	            // SQL 쿼리 준비
	            pstmt = con.prepareStatement(getPayClick(vo.getCartNos()));
	            pstmt.setLong(1,vo.getPayNoUpdate());
	            
	            // 쿼리 파라미터 설정
	 
	            // 쿼리 실행 및 결과 집합 얻기
	            result = pstmt.executeUpdate();

	            // 결과 집합이 존재하면 객체 생성 및 데이터 설정
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("앨범 장바구니 보기를 가져오는 중 오류가 발생했습니다.");
	        } finally {
	            // 사용한 자원 정리
	            DB.close(con, pstmt, rs);
	        }

	        // 결과 객체 반환
	        return result;
	    } // end of payClick()
	   
	// 1-2. 장바구니 데이터 리스트  
	   public List<PayVO> itemlist(long[] cartNos) throws Exception {
		   List<PayVO> list = null;	

	        try {
	            // 데이터베이스 연결
	            con = DB.getConnection();
	            System.out.println(getPayListCart(cartNos));
	            // SQL 쿼리 준비
	            pstmt = con.prepareStatement(getPayListCart(cartNos));
	            
	            // 쿼리 파라미터 설정
	 
	            // 쿼리 실행 및 결과 집합 얻기
	            rs = pstmt.executeQuery();

	    		if(rs != null) {
					while(rs.next()) {
						// rs - > vo -> list
						// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
						if(list == null) list = new ArrayList<PayVO>();
						// rs -> vo
						PayVO vo = new PayVO();
						vo.setCartImage(rs.getString("image"));
						vo.setCartTitle(rs.getString("title"));
						vo.setPrice(rs.getLong("price"));
						vo.setAlbumCnt(rs.getInt("albumCnt"));
						vo.setCartNo(rs.getLong("cartNo"));
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
		} // end of cartlist()

	// 1-2. 장바구니 리스트 
	// PayController - (Execute) - PayListService - [PayDAO.list()]
	public List<PayVO> cartlist(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		List<PayVO> list = null;	
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			int idx = 0;
			pstmt = con.prepareStatement(CARTLIST);
			pstmt.setString(++idx, vo.getMemberId());
			pstmt.setLong(++idx, vo.getPayNos());
			System.out.println("cartlist(PayVO vo)="+CARTLIST);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				while(rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if(list == null) list = new ArrayList<PayVO>();
					// rs -> vo
					vo = new PayVO();
					vo.setCartImage(rs.getString("image"));
					vo.setCartTitle(rs.getString("title"));
					vo.setPrice(rs.getLong("price"));
					vo.setAlbumCnt(rs.getInt("cnt"));
					vo.setGoodsNo(rs.getLong("goodsNo"));
					vo.setCartDataNo(rs.getLong("cartAlbumDataNo"));
					vo.setPayNos(rs.getLong("payNo"));
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
	} // end of cartlist()
	
	
	// 1-3. 결제 수단 처리
	// PayController - (Execute) - PayWriteService - [PayDAO.write()]
	public int write(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getPayWrite(vo));
			System.out.println("getPayWrite(vo):"+getPayWrite(vo));
			int idx = 0;
			pstmt.setLong(++idx, vo.getPayNoUpdate());
			pstmt.setLong(++idx, vo.getCartPrice());
			pstmt.setString(++idx, vo.getAddress());
			pstmt.setString(++idx, vo.getName());
			pstmt.setString(++idx, vo.getPhone());
			pstmt.setString(++idx, vo.getMemberId());
			if(vo.getCardBank() != null) {
			pstmt.setString(++idx, vo.getCardBank());
			pstmt.setString(++idx, vo.getCardType());
			pstmt.setString(++idx, vo.getCardNum());
			}
			if(vo.getPayTel() != null) {
			pstmt.setString(++idx, vo.getPayTel());
			pstmt.setString(++idx, vo.getPhonePay());
			}
			if(vo.getBankType() != null) {
			pstmt.setString(++idx, vo.getBankType());
			pstmt.setString(++idx, vo.getBankNum());
			}

			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("*** 결제 정보 등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 결제 정보 등록 중 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of write()
   // 1-4 결제 순번 넣기 	
	// PayController - (Execute) - PayWriteService - [PayDAO.write()]
	public Long getNo() throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		 Long no = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(NO);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null && rs.next()) {
				no = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		// 결과 데이터를 리턴해 준다.
		return no;
	} // end of getTotalRow()
	// 1-5. 결제  처리
	// PayController - (Execute) - PayUpdateService - [PayDAO.payupdate()]
	public int payupdate(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(PAYNOUPDATE +in(vo.getPn())); //+in()
			System.out.println("payupdate="+PAYNOUPDATE +in(vo.getPn()));
			pstmt.setLong(1, vo.getPayNoUpdate());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 결제 번호 맞지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if(e.getMessage().indexOf("예외 발생") >= 0) throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else throw new Exception("예외 발생 : 배송지 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of payupdate()

	   // 1-4 결제 순번 넣기 	
		// PayController - (Execute) - PayWriteService - [PayDAO.getPayNoSelect()]
		public Long getPayNoSelect() throws Exception{
			// 결과를 저장할 수 있는 변수 선언.
			 Long no = null;
			try {
				// 1. 드라이버 확인 - DB
				// 2. 연결
				con = DB.getConnection();
				// 3. sql - 아래 LIST
				// 4. 실행 객체 & 데이터 세팅
				pstmt = con.prepareStatement(PAYSELECT);
				// 5. 실행
				rs = pstmt.executeQuery();
				// 6. 표시 또는 담기
				if(rs != null && rs.next()) {
					no = rs.getLong(1);
				} // end of if
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				// 7. 닫기
				DB.close(con, pstmt, rs);
			} // end of try ~ catch ~ finally

			// 결과 데이터를 리턴해 준다.
			return no;
		} // end of getPayNoSelect()
	
	
	// 2. 결제 리스트 처리
	// PayController - (Execute) - PayListService - [PayDAO.list()]
	public List<PayVO> list() throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		List<PayVO> list = null;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(LIST);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				while(rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if(list == null) list = new ArrayList<PayVO>();
					// rs -> vo
					PayVO vo = new PayVO();
					vo.setPayNo(rs.getLong("payNo"));
					vo.setName(rs.getString("name"));
					vo.setAddress(rs.getString("address"));
					vo.setPhone(rs.getString("phone"));
					vo.setCartPrice(rs.getLong("totalprices"));
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
	
	
	// 3. 결제 상세 보기 처리
	// PayController - (Execute) - PayViewService - [PayDAO.view()]
	public PayVO view(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
	
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(VIEW);
			System.out.println("view(String id) = "+VIEW);
			pstmt.setString(1, vo.getMemberId());
			pstmt.setLong(2, vo.getPayNo());
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null && rs.next()) {
				// rs -> vo
				vo = new PayVO();
				vo.setPayNo(rs.getLong("payNo"));
				vo.setCartPrice(rs.getLong("totalprices"));
				vo.setCancel(rs.getString("cancel"));
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setPhone(rs.getString("phone"));
				vo.setStatus(rs.getString("status"));
				vo.setPayToday(rs.getString("payToday"));
				vo.setCardBank(rs.getString("CARDBANK"));
				vo.setCardType(rs.getString("CARDTYPE"));
				vo.setCardNum(rs.getString("CARDNUM"));
				vo.setPayTel(rs.getString("PAYTEL"));            
				vo.setPhonePay(rs.getString("PHONEPAY"));            
				vo.setBankType(rs.getString("BANKTYPE"));            
				vo.setBankNum(rs.getString("BANKNUM"));            
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("예외 발생 :결제 상세 보기 DB 처리 중 오류 발생");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		// 결과 데이터를 리턴해 준다.
		return vo;
	} // end of view()
	
	
	// 4. 결제 수정 처리
	// PayController - (Execute) - PayUpdateService - [PayDAO.update()]
	public int update(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getAddress());
			pstmt.setString(2, vo.getName());
			pstmt.setLong(3, vo.getPayNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 결제 번호와 배송지가 맞지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if(e.getMessage().indexOf("예외 발생") >= 0) throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else throw new Exception("예외 발생 : 배송지 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of update()
	
	
	// 5. 결제 취소 상태 처리 
	// PayController - (Execute) - PayDeleteService - [PayDAO.delete()]
	public int delete(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, vo.getCancel());
			pstmt.setString(2, vo.getStatus());
			pstmt.setLong(3, vo.getPayNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) { // 글번호가 존재하지 않거나 비번 틀림. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 취소 사유가 실패하였습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if(e.getMessage().indexOf("예외 발생") >= 0) throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else throw new Exception("예외 발생 : 취소 사유 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of delete()

	// 6. 관리자 리스트
	// PayController - (Execute) - PayAdminListService - [PayDAO.adminList()]
	public List<PayVO> adminList() throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		List<PayVO> list = null;	
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(ADMINLIST);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				while(rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if(list == null) list = new ArrayList<PayVO>();
					// rs -> vo
				    PayVO vo = new PayVO();
					vo.setPayNo(rs.getLong("payNo"));
					vo.setCancel(rs.getString("cancel"));
					vo.setName(rs.getString("name"));
					vo.setAddress(rs.getString("address"));
					vo.setPhone(rs.getString("phone"));
					vo.setStatus(rs.getString("status"));
					vo.setPayToday(rs.getString("payToday"));
					vo.setCartPrice(rs.getLong("totalPrices"));
					vo.setCardBank(rs.getString("CARDBANK"));
					vo.setCardType(rs.getString("CARDTYPE"));
					vo.setCardNum(rs.getString("CARDNUM"));
					vo.setPayTel(rs.getString("PAYTEL"));            
					vo.setPhonePay(rs.getString("PHONEPAY"));            
					vo.setBankType(rs.getString("BANKTYPE"));            
					vo.setBankNum(rs.getString("BANKNUM"));      
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
	} // end of adminList()

	// 6-2 관리자 상태 변경 
	// PayController - (Execute) - PayDeleteService - [PayDAO.changeStatus()]
	public int changeStatus(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(CHANGESTATUS);
			pstmt.setString(1, vo.getStatus());
			pstmt.setLong(2, vo.getPayNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) { // 글번호가 존재하지 않거나 비번 틀림. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 상태 변경이 실패하였습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if(e.getMessage().indexOf("예외 발생") >= 0) throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else throw new Exception("예외 발생 : 상태 변경 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of changeStatus()

	// 6-3 라디오 선택할 때 마다 보여지는 리스트 변경 
	// PayController - (Execute) - PayDeleteService - [PayDAO.radioChage()]	
	public List<PayVO> radioChage(PayVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		List<PayVO> list = null;	
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(ADMINLIST+getChangeRadio(vo));
			System.out.println("radioChage(PayVO vo) ="+ADMINLIST +getChangeRadio(vo));
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				while(rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if(list == null) list = new ArrayList<PayVO>();
					// rs -> vo
				     vo = new PayVO();
					vo.setPayNo(rs.getLong("payNo"));
					vo.setCancel(rs.getString("cancel"));
					vo.setName(rs.getString("name"));
					vo.setAddress(rs.getString("address"));
					vo.setPhone(rs.getString("phone"));
					vo.setStatus(rs.getString("status"));
					vo.setPayToday(rs.getString("payToday"));
					vo.setCartPrice(rs.getLong("totalPrices"));
					vo.setCardBank(rs.getString("CARDBANK"));
					vo.setCardType(rs.getString("CARDTYPE"));
					vo.setCardNum(rs.getString("CARDNUM"));
					vo.setPayTel(rs.getString("PAYTEL"));            
					vo.setPhonePay(rs.getString("PHONEPAY"));            
					vo.setBankType(rs.getString("BANKTYPE"));            
					vo.setBankNum(rs.getString("BANKNUM"));      
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
	} // end of radioChage()

	// 7-1. 상품 목록
	// PayController - (Execute) - PayAdminListService - [PayDAO.orderList()]
	public List<PayVO> orderList(String id) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		List<PayVO> list = null;	
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(ORDERLIST);
			pstmt.setString(1, id);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				while(rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if(list == null) list = new ArrayList<PayVO>();
					// rs -> vo
					//a.image,a.title,a.price, ca.cnt,ca.payNo 
				    PayVO vo = new PayVO();
					vo.setCartImage(rs.getString("image"));
					vo.setCartTitle(rs.getString("title"));
					vo.setPrice(rs.getLong("price"));
					vo.setAlbumCnt(rs.getInt("cnt"));
					vo.setPayNo(rs.getLong("payNo"));   
					vo.setToday(rs.getString("today"));
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
	} // end of orderList()	
	
	// 7-2. 주문목록 리스트 
	// PayController - (Execute) - PayListService - [PayDAO.list()]
	public List<OrderVO> orderCartlist(OrderVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		 List<OrderVO> list =null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			//a.image,a.title,a.price, ca.cnt
			pstmt = con.prepareStatement(ORDERCARTLIST);
			pstmt.setString(1, vo.getId());
			pstmt.setLong(2, vo.getNo());
			System.out.println("orderCartlist="+ORDERCARTLIST);
			// 5. 실행
			rs = pstmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if(list == null) list = new ArrayList<OrderVO>();
				 vo = new OrderVO();
				vo.setCartImage(rs.getString("image"));
				vo.setCartTitle(rs.getString("title"));
				vo.setCartPrice(rs.getLong("price"));
				vo.setAlbumCnt(rs.getInt("cnt"));
		           list.add(vo);
				}
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("예외 발생 :결제 상세 보기 DB 처리 중 오류 발생");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		// 결과 데이터를 리턴해 준다.
		return list;
	} // end of view()
	
	//앨범 정보 
	private String getPayListCart(long[] cartNos) {
	String sql ="SELECT  a.image, a.title, a.price, ca.albumCnt,ca.cartNo FROM album a, cartAlbum ca where cartNo in( ";
	for(int i=0;i<cartNos.length;i++) {
		if(i==0) {
			sql += cartNos[i];
		} else {
			sql += ","+cartNos[i];
		}
	}
	    	sql += ")and ca.albumNo = a.albumNo";
	    	return sql;	
	}
	// 카트 정보를 담는 곳 
	   private String getPayClick(String[] cartNos) {
	    	String sql = "INSERT INTO cartAlbumData(cartAlbumDataNo, goodsNo, cnt, userId,payNo) "
	        		+ "         ( select cartAlbumDataNo_seq.nextval, AlbumNo, albumcnt, memberid,payNo from( "
	        		+ "SELECT  AlbumNo, albumcnt, memberid ,? payNo FROM cartAlbum where cartNo in( " ;
	    	for(int i=0;i<cartNos.length;i++) {
	    		if(i==0) {
	    			sql += cartNos[i];
	    		} else {
	    			sql += ","+cartNos[i];
	    		}
	    	}
	    	sql += " ) ) )";
	    	return sql;
	    }	
	
	final String CARTLIST = "SELECT a.image,a.title,a.price, ca.cnt, ca.goodsNo,ca.cartAlbumDataNo,ca.payNo FROM  album a, cartAlbumData ca where ca.userId=? and (ca.goodsNo = a.albumNo)and payNo=? ";
	
	final String ORDERCARTLIST = "SELECT a.image,a.title,a.price, ca.cnt FROM  album a, cartAlbumData ca where ca.userId=? and (ca.goodsNo = a.albumNo)and payNo=? ";
	
	
	final String PAYNOALLDELETE = "DELETE FROM cartAlbumData where payNo = 0";
	
	final String WRITE = "INSERT ALL "
			+ " INTO pay (payNo,totalPrices, address, name, phone,userId)"
			+ "	VALUES (?, ?, ?, ?,?,?) "
			+ " INTO means_pay(meansPayNo, ";
	
	
	
	private String getPayWrite(PayVO vo) {
		String sql = WRITE;
		if(vo.getCardType() != null) {
			sql += " cardBank, cardType, cardNum) "
				+  " VALUES (meansPay_seq.nextval,?,?, ?) ";
		}
		if(vo.getPhonePay() !=null) {
		sql += " payTel,phonePay) VALUES(meansPay_seq.nextval, ?, ?) ";	
			
		}
		if(vo.getBankNum() != null) {
			sql +=" bankType ,bankNum) VALUES(meansPay_seq.nextval, ?, ?) ";
		}
		sql += " SELECT * FROM dual ";
		return sql;
	}
	final String PAYNOUPDATE= "update cartAlbumData "
			+ " set payNo = ?"
			+ " where cartAlbumDataNo ";
	
	private String in(Long[] longs) {
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
	
	final String LIST = "SELECT p.payNo, p.name, p.address,p.phone,p.totalprices "
            + "FROM pay p order by p.payNo desc";
	
	final String VIEW= "SELECT p.payNo,p.totalprices,p.cancel,p.name, p.address,p.phone,p.status,p.payToday,mp.CARDBANK,mp.CARDTYPE,mp.CARDNUM,mp.PAYTEL,mp.PHONEPAY,mp.BANKTYPE,mp.BANKNUM "
			+ " from pay p,means_pay mp "
			+ " where  p.userId =?and(p.payNo=mp.MEANSPAYNO)and payNo=?";

	final String ADMINLIST ="SELECT p.payNo,p.cancel,p.name, p.address,p.phone,p.status,p.payToday,p.totalPrices,mp.CARDBANK,mp.CARDTYPE,mp.CARDNUM,mp.PAYTEL,mp.PHONEPAY,mp.BANKTYPE,mp.BANKNUM"
			+ " from pay p, means_pay mp"
			+ " where(p.payNo=mp.MEANSPAYNO)";
	
	final String UPDATE= "update pay "
			+ " set address = ?, name = ? "
			+ " where payNo = ?"; 
	final String DELETE= "UPDATE pay SET cancel = ?,status = ? WHERE payNo = ?"; 

	final String NO = " select payNo_seq.nextval from dual ";
	
	final String CHANGESTATUS= "UPDATE pay SET status = ? WHERE payNo = ?"; 
	
	final String PAYSELECT = "SELECT ca.payNo FROM cartAlbumData ca WHERE ca.cartAlbumDataNo = (SELECT MAX(ca.cartAlbumDataNo) FROM cartAlbumData ca) ";
	private String getChangeRadio(PayVO vo) {
		String sql = "";
		//라디오 버튼으로 결제 상태 보여주는거 다르게 표시
		if(vo.getRadioStatus().equals("전체")){
			
		}
		if(vo.getRadioStatus().equals("준비")){
			sql += "and p.status='준비' ";
		}
		if(vo.getRadioStatus().equals("취소")){
			sql += "and p.status='취소' ";
		}
		if(vo.getRadioStatus().equals("완료")){
			sql += "and p.status='완료' ";
		}
		if(vo.getRadioStatus().equals("배송중")) {
			sql += "and p.status='배송중' ";
		}
		sql +="";
		return sql;
	}
	final String ORDERLIST = "SELECT a.image,a.title,a.price, ca.cnt,ca.payNo,to_char(ca.today, 'yyyy-mm-dd')today FROM  album a, cartAlbumData ca where ca.userId=? and (ca.goodsNo = a.albumNo) ";
	
	
	
	
	}

