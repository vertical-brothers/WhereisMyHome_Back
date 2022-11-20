package com.ssafy.apartment.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.apartment.model.HouseDealDto;
import com.ssafy.apartment.model.service.HousedealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/housedeal")
@CrossOrigin("*")
@Api("아파트 거래정보 관련 API")
public class HousedealController {

	private HousedealService housedealService; // 관심지역

	@Autowired
	public HousedealController(HousedealService housedealService) {
		this.housedealService = housedealService;
	}

	private static final Logger logger = LoggerFactory.getLogger(HousedealController.class);

//	@GetMapping(value = "/mvaptapi")
//	public String mvaptapi(Model model) throws Exception {
//		logger.debug("ApartmentController ! mvaptapi  ");
//		return "/apartment/aptapi";
//	}

//	@GetMapping(value = "/search")
//	@ResponseBody
//	public ResponseEntity<?> search(@RequestParam Map<String, String> map, Model model) throws Exception {
//		logger.debug("ApartmentController ! search : {}", map);
//		try {
//			List<HouseDealDto> list = housedealService.listAll(map);
//			logger.debug("ApartmentController ! search : {}",list.size());
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
//	@GetMapping(value = "/search")
//	@ResponseBody
//	public ResponseEntity<?> search(@RequestParam Map<String, String> map) throws Exception {
//		logger.debug("ApartmentController ! search : {}", map);
//		try {
//			List<HouseDealDto> list = housedealService.listAll(map);
//			logger.debug("ApartmentController ! search : {}",list.size());
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
	// 아파트 코드로 아파트 거래 정보 조회
	// 22.11.18 장한결
	/*
	 * argument
	 * String : aptCode (아파트 코드)
	 * 
	 * return
	 * List<HouseDealDto>
	 * */
	@ApiOperation(value = "아파트 거래정보 검색 (아파트 코드)", notes = "아파트 코드로 아파트 거래 정보 호출.", response = List.class)
	@GetMapping(value="/name/{aptCode}")
	private ResponseEntity<List<HouseDealDto>> searchByAptCode(@PathVariable String aptCode) throws Exception {
		logger.debug("searchByAptCode(HouseDeal) call, aptCode : {}", aptCode);
		List<HouseDealDto> list = housedealService.searchByAptcode(aptCode);
		logger.debug("searchByAptCode(HouseDeal) result, HouseDealList : {}", list);
		if(list.size() == 0) {
			return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
