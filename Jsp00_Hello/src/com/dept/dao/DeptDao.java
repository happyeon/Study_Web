package com.dept.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dept.dto.DeptDto;

public class DeptDao {
	
	public List<DeptDto> selectList(){
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 2. 계정 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2. 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// 3. query 준비
		String sql = " SELECT DEPTNO, DNAME, LOC "
				   + " FROM DEPT "
				   + " ORDER BY DEPTNO ";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		List<DeptDto> list = new ArrayList<DeptDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("3. query 준비" + sql);
			
			// 4. query 실행 및 리턴
			rs = stmt.executeQuery(sql);
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				DeptDto dto = new DeptDto();
				dto.setDeptno(rs.getInt(1));
				dto.setDname(rs.getString(2));
				dto.setLoc(rs.getString(3));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. db 종료
			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("5. db 종료");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	

}
