package com.login.dao;

import static com.login.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.LoginDto;

public class LoginDao {
	
	/*
	 * 관리자 기능
	 * 1. 회원 전체 정보 (모든 회원)
	 * 2. 가입된 회원의 전체 정보 (MYENABLED = 'Y' 인 회원)
	 * 3. 회원 등급 조정
	 */
	
	// 1. 
	public List<LoginDto> selectAllList() {
		Connection con = getConnection();
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				   + " FROM MYMEMBER "
				   + " ORDER BY MYNO ";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<LoginDto> list = new ArrayList<LoginDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				LoginDto dto = new LoginDto();
				dto.setMyno(rs.getInt("MYNO"));
				dto.setMyid(rs.getString("MYID"));
				dto.setMypw(rs.getString("MYPW"));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMyaddr(rs.getString("MYADDR"));
				dto.setMyphone(rs.getString("MYPHONE"));
				dto.setMyemail(rs.getString("MYEMAIL"));
				dto.setMyenabled(rs.getString("MYENABLED"));
				dto.setMyrole(rs.getString("MYROLE"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
				
		return list;
	}
	
	// 2.
	public List<LoginDto> selectEnabledList() {
		Connection con = getConnection();
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				   + " FROM MYMEMBER "
				   + " WHERE MYENABLED = 'Y' "
				   + " ORDER BY MYNO ";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<LoginDto> list = new ArrayList<LoginDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while (rs.next()) {
				LoginDto dto = new LoginDto(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getString(7),
											rs.getString(8),
											rs.getString(9));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		
		return list;
	}
	
	// 3.
	public int updateRole(int myno, String role) {
		Connection con = getConnection();
		String sql = " UPDATE MYMEMBER "
				   + " SET MYROLE = ? "
				   + " WHERE MYNO = ? ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, role);
			pstm.setInt(2, myno);
			System.out.println("3. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}
	
	/*
	 * 사용자 기능
	 * 1. 로그인
	 * 2. 회원가입
	 * 3. 아이디 중복 체크
	 * 4. 내 정보 조회 
	 * 5. 내 정보 수정
	 * 6. 탈퇴
	 */
	
	// 1.
	public LoginDto login(String myid, String mypw) {
		
		Connection con = getConnection();
		
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				   + " FROM MYMEMBER "
				   + " WHERE MYID = ? "
				   + " AND MYPW = ? "
				   + " AND MYENABLED = 'Y' ";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		LoginDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			System.out.println("3. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto = new LoginDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("4. db 종료");
		}
		
		return dto;
	}
	
	// 2.
	public int insert(LoginDto dto) {
		Connection con = getConnection();
		
		String sql = " INSERT INTO MYMEMBER "
				   + " VALUES(MYNOSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'Y', 'USER') ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			System.out.println("3. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		
		return res;
	} 
	
	// 3.
	public LoginDto idCheck(String myid) {
		Connection con = getConnection();
		
		String sql = " SELECT MYID FROM MYMEMBER "
				   + " WHERE MYID = ? ";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 여기서는 null로 써야 한다. new LoginDto()로 쓰면 controller에서 원하는대로 적용이 안된다. 
		// 만약에 new LoginDto()를 사용하려면 controller에서 dto 대신dto.getMyid()를 사용해야 정상적으로 적용이 된다.
		LoginDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			System.out.println("3. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while (rs.next()) {
				dto = new LoginDto();
				dto.setMyid(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
				
		return dto;
	}
	
	// 4.
	public LoginDto selectOne(int myno) {
		Connection con = getConnection();
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				   + " FROM MYMEMBER "
				   + " WHERE MYNO = ? ";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		LoginDto dto = new LoginDto();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("3. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5 db 종료");
		}
		
		return dto;
	}
	
	// 5.
	public int update(LoginDto dto) {
		Connection con = getConnection();
		
		String sql = " UPDATE MYMEMBER "
				   + " SET MYPW = ?, MYADDR = ?, MYPHONE = ?, MYEMAIL = ? "
				   + " WHERE MYNO = ? ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMypw());
			pstm.setString(2, dto.getMyaddr());
			pstm.setString(3, dto.getMyphone());
			pstm.setString(4, dto.getMyemail());
			pstm.setInt(5, dto.getMyno());
			System.out.println("3. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		
		return res;
	}
	
	// 6.
	public int delete(int myno) {
		Connection con = getConnection();
		
		String sql = " UPDATE MYMEMBER "
				   + " SET MYENABLED = 'N' "
				   + " WHERE MYNO = ? ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("3. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		
		return res;
	}

}
