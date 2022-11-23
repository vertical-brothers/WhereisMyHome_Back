package com.ssafy.homeLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homeLog.model.ApartLogDto;
import com.ssafy.homeLog.model.service.ApartLogService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/log")
@Api("로그 (star_log) 관련 API")
public class LogController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	

	
	
	@Autowired
	private ApartLogService apartLogService;
	
	private static final Logger logger = LoggerFactory.getLogger(LogController.class);
	
	/*
	 2022-11-23 이인재
	 aptCode를 pathvariable로 받아 저장하는 api
	 */

	@PostMapping(value = "/apartlog/{aptCode}")
	@ApiOperation(value = "아파트 기록 저장", notes = "아파트 기록 수집합니다.", response = Void.class)
	private ResponseEntity<Void> addLog(@PathVariable String aptCode) throws Exception{
		logger.debug("start to add apartLog ===>");
		apartLogService.addLog(aptCode);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/apartlog")
	@ApiOperation(value = "아파트 기록 가져오기", notes = "아파트 기록을 반환합니다.", response = List.class)
	private ResponseEntity<List<ApartLogDto>> getLog() throws Exception{
		logger.debug("start to get apartLog ===>");
		List<ApartLogDto> list = apartLogService.getLog();
		logger.debug("top 3 is {}", list);
		return new ResponseEntity<List<ApartLogDto>>(list, HttpStatus.OK);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
