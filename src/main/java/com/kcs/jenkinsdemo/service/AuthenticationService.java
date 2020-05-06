package com.kcs.jenkinsdemo.service;

import com.kcs.jenkinsdemo.dto.ResponseVO;

public interface AuthenticationService {

	ResponseVO<String> authenticateAndGenerateToken(String username, String password);

}
