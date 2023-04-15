package com.cos.blog.domain.reply.dto;

import lombok.Data;

@Data
public class SavaReqDto {
	private int userId;
	private int boardId;
	private String content;
}
