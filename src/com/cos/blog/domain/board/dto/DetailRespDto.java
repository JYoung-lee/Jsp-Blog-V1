package com.cos.blog.domain.board.dto;

import java.sql.Timestamp;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.Board.BoardBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailRespDto {
	private int id;
	private String title;
	private String content;
	private int readcount;
	private String username;
	private int userid;
	
	public String getTitle() {
		return title.replaceAll("<","&lt;").replaceAll(">", "&gt;");
	}
}
