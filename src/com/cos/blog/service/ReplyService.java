package com.cos.blog.service;

import com.cos.blog.domain.reply.ReplyDao;
import com.cos.blog.domain.reply.dto.SavaReqDto;

public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}
	
	
	public int saveReply(SavaReqDto dto) {
		return replyDao.saveReply(dto);	
	}
	
}
