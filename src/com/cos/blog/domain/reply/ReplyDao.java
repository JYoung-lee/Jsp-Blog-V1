package com.cos.blog.domain.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.blog.config.DB;
import com.cos.blog.domain.reply.dto.SavaReqDto;

public class ReplyDao {

	
	public int saveReply(SavaReqDto dto) {
		String sql = "INSERT INTO REPLY(USERID, BOARDID, CONTENT, CREATEDATE)"
				   + "VALUES(?,?,?,NOW())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();			
			return result;
		}catch(Exception e) {
			
		}finally {
			DB.close(conn, pstmt);
		}
		
		return -1;
		
	}
	
}
