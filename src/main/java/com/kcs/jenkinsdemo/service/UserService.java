package com.kcs.jenkinsdemo.service;

import java.util.List;

import com.kcs.jenkinsdemo.dto.ResponseVO;
import com.kcs.jenkinsdemo.dto.UserVO;

public interface UserService {

	ResponseVO<List<UserVO>> findAll();

	ResponseVO<UserVO> findById(Long id);

	ResponseVO<Long> save(UserVO vo);

	ResponseVO<Long> update(UserVO vo);

	ResponseVO<Void> delete(Long id);

}
