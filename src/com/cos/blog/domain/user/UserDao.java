package com.cos.blog.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.blog.config.DB;
import com.cos.blog.domain.user.dto.JoinReqDto;

public class UserDao {

	public int save(JoinReqDto dto) { //회원가입
		String sql = "INSERT INTO user(username, password, email, address, userRole, createDate) VALUES(?,?,?,?, 'USER', now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			int result = pstmt.executeUpdate();
			return result;
		}catch(Exception e) {
			e.printStackTrace();		
		}finally {//무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public void update() { //회원수정
		
		
	}
	
	public void usernameCheck() {//아이디 중복 체크
		
		
	}
	
	public int findByUsername(String username) { // 회원 정보
		String sql = "SELECT * FROM USER WHERE USERNAME=?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				return 1; //있다.
			}
			
			return -1;
		}catch(Exception e) {
			e.printStackTrace();		
		}finally {//무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return -1;
		
	}
}
