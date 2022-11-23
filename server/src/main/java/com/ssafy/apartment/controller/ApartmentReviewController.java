package com.ssafy.apartment.controller;

/*
 * 아파트정보 Controller
 * 
 * Restful Controller
 * HouseDto 사용
 * 
 * 22.11.17 장한결
 * */

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apartment.model.service.ApartmentReviewService;
import com.ssafy.apartment.model.service.HouseService;
import com.ssafy.apartment.model.ApartmentReviewDto;
import com.ssafy.apartment.model.HouseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/aptreview")
@CrossOrigin("*")
@Api("아파트 리뷰 (apartmentReview) 관련 API")
public class ApartmentReviewController {

	private final Logger logger = LoggerFactory.getLogger(ApartmentReviewController.class);
	private final ApartmentReviewService apartmentReviewService;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	public ApartmentReviewController(ApartmentReviewService apartmentReviewService) {
		this.apartmentReviewService = apartmentReviewService;
	}
	
	// 아파트 코드로 아파트 리뷰 정보 조회
	// 22.11.20 장한결
	/*
	 * argument
	 * String : aptCode (아파트 코드, PK)
	 * 
	 * return
	 * HouseDto
	 * */
	@ApiOperation(value = "아파트 코드로 찾기", notes = "아파트 코드로 아파트 리뷰 정보 호출", response = List.class)
	@GetMapping(value="/aptcode/{aptCode}")
	private ResponseEntity<List<ApartmentReviewDto>> searchByAptcode(@PathVariable String aptCode) throws Exception {
		logger.debug("searchByAptcode(Review) call, aptCode : {}", aptCode);
		List<ApartmentReviewDto> list = apartmentReviewService.showReviewByAptcode(aptCode);
		logger.debug("searchByAptcode(Review) result, review list : {}", list);
		return new ResponseEntity<List<ApartmentReviewDto>>(list, HttpStatus.OK);
	}
	// 아파트 리뷰 정보 삭제
	// 22.11.20 장한결
	/*
	 * argument
	 * String : aptCode (아파트 코드, PK)
	 * 
	 * */
	@ApiOperation(value = "아파트 리뷰 삭제", notes = "아파트 리뷰정보 삭제 API", response = List.class)
	@DeleteMapping(value="/{id}")
	private ResponseEntity<String> deleteReview(@PathVariable String id) throws Exception {
		logger.debug("deleteApartmentReview call, id : {}", id);
		try {
			apartmentReviewService.deleteReview(id);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);	
		} catch(Exception e) {
			logger.debug("deleteApartmentReview error : {}", e);
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	// 아파트 리뷰 입력
	// 22.11.20 장한결
	/*
	 * argument
	 * ApartmentReviewDto
	 * 
	 * */
	@ApiOperation(value = "아파트 리뷰 작성", notes = "아파트 리뷰정보 작성 API", response = List.class)
	@PostMapping
	private ResponseEntity<String> writeReview(@RequestBody ApartmentReviewDto dto) throws Exception {
		logger.debug("write Review call, dto : {}", dto);
		try {
			apartmentReviewService.writeReview(dto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);	
		} catch(Exception e) {
			logger.debug("writeApartmentReview error : {}", e);
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// 아파트 아파트 리뷰 정보 작성
	// 22.11.20 장한결
	/*
	 * argument
	 * ApartmentReviewDto
	 * 
	 * */
	@ApiOperation(value = "아파트 리뷰 수정", notes = "아파트 리뷰정보 수정 API", response = List.class)
	@PutMapping
	private ResponseEntity<String> updateReview(@RequestBody ApartmentReviewDto dto) throws Exception {
		logger.debug("update Review call, dto : {}", dto);
		try {
			apartmentReviewService.updateReview(dto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);	
		} catch(Exception e) {
			logger.debug("updateApartmentReview error : {}", e);
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// 최신 아파트 리뷰 정보 조회
	// 22.11.20 장한결
	/*
	 * argument
	 * String : int (원하는 글 갯수)
	 * 
	 * return
	 * HouseDto
	 * */
	@ApiOperation(value = "최신 아파트 리뷰 정보 조회", notes = "최신 아파트 리뷰 정보 조회, limit 개의 아파트 리뷰 정보만 나옴.", response = List.class)
	@GetMapping(value="/recent/{limit}")
	private ResponseEntity<List<ApartmentReviewDto>> recentReviewList(@PathVariable int limit) throws Exception {
		logger.debug("recentReviewList(Review) call, limit : {}", limit);
		List<ApartmentReviewDto> list = null;
		try {
			list = apartmentReviewService.showRecentReview(limit);
			logger.debug("recentReviewList(Review) result, review list : {}", list);
			return new ResponseEntity<List<ApartmentReviewDto>>(list, HttpStatus.OK);
		} catch(Exception e) {
			logger.debug("recentReviewList(Review) error, {}",e);
			return new ResponseEntity<List<ApartmentReviewDto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
