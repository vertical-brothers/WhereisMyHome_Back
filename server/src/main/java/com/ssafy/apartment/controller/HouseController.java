package com.ssafy.apartment.controller;

/*
 * 아파트정보 Controller
 * 
 * Restful Controller
 * HouseDto 사용
 * 
 * 22.11.17 장한결
 * */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.apartment.model.service.HouseService;
import com.ssafy.board.model.BoardDto;
import com.ssafy.apartment.model.HouseDto;
import com.ssafy.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/houseinfo")
@CrossOrigin("*")
@Api("아파트 정보 (houseinfo) 관련 API")
public class HouseController {

	private final Logger logger = LoggerFactory.getLogger(HouseController.class);
	private final HouseService houseService;
	
	@Autowired
	public HouseController(HouseService houseService) {
		this.houseService = houseService;
	}
	
	// 아파트 코드로 아파트 정보 조회
	// 22.11.17 장한결
	/*
	 * argument
	 * String : aptCode (아파트 코드, PK)
	 * 
	 * return
	 * HouseDto
	 * */
	@ApiOperation(value = "아파트 코드로 찾기", notes = "아파트 코드로 아파트 정보 호출.", response = HouseDto.class)
	@GetMapping(value="/code/{aptCode}")
	private ResponseEntity<HouseDto> searchByAptcode(@PathVariable String aptCode) throws Exception {
		logger.debug("searchByAptcode call, aptCode : {}", aptCode);
		HouseDto house = houseService.searchByAptcode(aptCode);
		logger.debug("searchByAptcode result, HouseDto : {}", house);
		return new ResponseEntity<HouseDto>(house, HttpStatus.OK);
	}
	
	// 아파트 이름으로로 아파트 정보 조회
	// 22.11.17 장한결
	/*
	 * argument
	 * String : apartmentName (아파트 이름)
	 * 
	 * return
	 * List<HouseDto>
	 * */
	@ApiOperation(value = "아파트 리스트 (아파트명)", notes = "아파트 이름으로 아파트 정보 호출.", response = List.class)
	@GetMapping(value="/name/{apartmentName}")
	private ResponseEntity<List<HouseDto>> searchByAptName(@PathVariable String apartmentName) throws Exception {
		logger.debug("searchByAptName call, aptCode : {}", apartmentName);
		List<HouseDto> list = houseService.searchByAptName(apartmentName);
		logger.debug("searchByAptName result, HouseList : {}", list);
		if(list.size() == 0) {
			return new ResponseEntity<List<HouseDto>>(list, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<HouseDto>>(list, HttpStatus.OK);
		}
	}
	
	// 아파트 동 이름으로로 아파트 정보 조회
	// 22.11.18 장한결
	/*
	 * argument
	 * String : dongName (동이름)
	 * 
	 * return
	 * List<HouseDto>
	 * 
	 * */
	@ApiOperation(value = "아파트 리스트 (동 이름)", notes = "동이름으로 아파트 정보 호출.", response = List.class)
	@GetMapping(value="/dongName/{dongName}")
	private ResponseEntity<List<HouseDto>> searchByDongName(@PathVariable String dongName) throws Exception {
		logger.debug("searchByDongName call, dongCode : {}", dongName);
		List<HouseDto> list = houseService.searchByDongName(dongName);
		logger.debug("searchByDongName result, HouseList : {}", dongName);
		if(list.size() == 0) {
			return new ResponseEntity<List<HouseDto>>(list, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<HouseDto>>(list, HttpStatus.OK);
		}
	}
	
	// 아파트 동코드로 아파트 정보 조회
	// 22.11.17 장한결
	/*
	 * argument
	 * String : dongCode (동코드)
	 * 
	 * return
	 * List<HouseDto>
	 * */
	@ApiOperation(value = "아파트 리스트 (동 코드)", notes = "동코드로 아파트 정보 모두 호출.", response = List.class)
	@GetMapping(value="/dongCode/{dongCode}")
	private ResponseEntity<List<HouseDto>> searchByDongcode(@PathVariable String dongCode) throws Exception {
		logger.debug("searchByDongcode call, dongCode : {}", dongCode);
		List<HouseDto> list = houseService.searchByDongCode(dongCode);
		logger.debug("searchByDongcode result, HouseList : {}", dongCode);
		if(list.size() == 0) {
			return new ResponseEntity<List<HouseDto>>(list, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<HouseDto>>(list, HttpStatus.OK);
		}
	}

}
