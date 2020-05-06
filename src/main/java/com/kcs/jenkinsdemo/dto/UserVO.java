package com.kcs.jenkinsdemo.dto;

import com.kcs.jenkinsdemo.core.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {

	private Long id;
	
	private String name;
	
	private String userRole;
	
	private String status;
	
	public static UserVO convert(User user) {
		UserVO vo = new UserVO();
		vo.setId(user.getId());
		vo.setName(user.getName());
		vo.setUserRole(user.getUserRole().name());
		vo.setStatus(user.getStatus() ? "Active" : "Inactive");
		return vo;
	}
}
