package com.ssafy.loginLog;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apartment.controller.ApartmentReviewController;
import com.ssafy.homeLog.model.ApartLogDto;
import com.ssafy.loginLog.model.service.LoginLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/log/login")
@CrossOrigin("*")
@Api("로그인 로그 관련 API")
public class LoginLogController {
	
	@Autowired
	LoginLogService loginLogService;
	private final Logger logger = LoggerFactory.getLogger(LoginLogController.class);
	
	
	@ApiOperation(value = "로그인 로그를 수집합니다", notes = "로그인 로그 수집", response = List.class)
	@PostMapping
	public ResponseEntity<Void> addLog() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("add Loginlog call========================>");
		loginLogService.addLoginLog();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ApiOperation(value = "로그인 로그를 불러옵니다", notes = "로그인 정보 조회", response = List.class)
	@GetMapping
	public ResponseEntity<Integer> getLog() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("get Loginlog call========================>");
		int count = loginLogService.getLoginLog();
		return new ResponseEntity<Integer>(count, HttpStatus.OK); 
	}
}
