package com.cos.blog.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.blog.config.DB;
import com.cos.blog.domain.board.dto.SaveReqDto;

public class BoardDao {

	public int save(SaveReqDto dto) {
		String sql = "INSERT INTO BOARD(USERID, TITLE, CONTENT, CREATEDATE)"
							  + "VALUES(?,?,?,NOW())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		return result;
	}
	
}
