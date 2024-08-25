package com.musaic.member.db;

import lombok.Data;

@Data
public class LoginVO {
	private String id;
	private String pw;
	private String name;
	private String photo;
	private Long gradeNo;
	private String gradeName;
	private Long newMsgCnt;
}