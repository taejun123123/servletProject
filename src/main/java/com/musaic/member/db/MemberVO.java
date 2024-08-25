package com.musaic.member.db;

import lombok.Data;

@Data
public class MemberVO {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String birth;
	private String conDate;
	private String regDate;
	private String email;
	private String tel;
	private String address;
	private String status;
	private String photo;
	private String gradeName;
	private Long no;
	private Long gradeNo;
}