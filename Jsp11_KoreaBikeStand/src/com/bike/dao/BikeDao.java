package com.bike.dao;

import static com.bike.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bike.dto.BikeDto;

public class BikeDao {
	
	public boolean insert(List<BikeDto> list) {
		// 힌트! 한 번에 여러개
		Connection con = getConnection();
		
		String sql = " INSERT INTO KOREABIKE "
				   + " VALUES (?, ?, ?, ?, ?) ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				BikeDto dto = list.get(i);
				pstm.setString(1, dto.getName());
				pstm.setString(2, dto.getAddr());
				pstm.setDouble(3, dto.getLatitude());
				pstm.setDouble(4, dto.getLongitude());
				pstm.setInt(5, dto.getBike_count());
				
				pstm.addBatch();
			}
			System.out.println("3. query 준비 : " + sql);
			
			int[] result = pstm.executeBatch();
			System.out.println("4. query 실행 및 리턴");
			for (int i = 0; i < result.length; i++) {
				if (result[i] == -2) {
					res++;
				}
			}
			
			if (res == list.size()) {
				con.commit();
			} else {
				con.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		return (res == list.size())? true : false;
	}
	
	public boolean delete() {
		// table에 전체삭제
		Connection con = getConnection();
		
		String sql = " DELETE FROM KOREABIKE ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query 준비");
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴 : " + sql);
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
		
		return (res > 0)? true : false;
	}

}
