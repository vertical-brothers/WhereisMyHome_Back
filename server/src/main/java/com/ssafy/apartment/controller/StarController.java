package com.ssafy.apartment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apartment.model.StarDto;
import com.ssafy.apartment.model.service.StarService;
import com.ssafy.jwt.service.JwtService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/star")
@Api("즐겨찾기/로그 (star/star_log) 관련 API")
public class StarController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private StarService starService;	//관심지역 service
	@Autowired
	private JwtService jwtService;
	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(StarController.class);
	
	/*
	 * 2022-11-20 이인재
	 * 관심지역 추가 Api
	 * header로 access-token을 주면 됩니다.
	 * pathvariable타입의 변수를 주기 때문에 url에 dongcode를 넘겨주어야 합니다
	 * 예시)
	 * http://localhost:8080/whereismyhome/star/1111010100
	 * */
	@PostMapping()
	@ApiOperation(value = "관심 지역추가", notes = "유저에게 해당 관심지역을 추가합니다.", response = Map.class)
	private ResponseEntity<Map<String, Object>> addStar(@RequestBody StarDto starDto, @RequestHeader("access-token") final String header) {
		// 토큰값 가져오기
		Map<String, Object> tokenValue = jwtService.get(header);
		Map<String, String> map = new HashMap<String, String>();
	
		// 토큰 값에서 userid만 가져오기
		String userId = tokenValue.get("userid").toString();
		starDto.setUserId(userId);
		logger.debug("ApartmentController ! addstar {} ", map);
		
		Map<String, Object> result = new HashMap<>();
				
		try {
			starService.addStar(starDto);
			result.put("message", SUCCESS);
			result.put("starNo", starDto.getStarNo());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", FAIL);
			result.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping
	@ApiOperation(value = "관심지역 조회", notes = "사용자가 추가한 관심지역을 조회합니다.", response = Map.class)
	private ResponseEntity<?> getStar(@RequestHeader("access-token") final String header) throws Exception{
		logger.debug("관심지역 조회 시작 단 {}", header);
		Map<String, Object> tokenValue = jwtService.get(header);
		String userId = tokenValue.get("userid").toString();
		
		if(userId != null) {
			List<StarDto> list = starService.listStar(userId);
			logger.debug("getStar(관심지역 조회) call : {}", list);
			return new ResponseEntity<List<StarDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<StarDto>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/*
	 * 2022-11-20 이인재
	 * 관심지역 삭 Api
	 * header로 access-token을 주면 됩니다.
	 * RequestBody타입의 변수를 주기 때문에 json타입의 body를 넘겨주어야 합니다.
	 * 예시)
	 * http://localhost:8080/whereismyhome/star
	 * */
	@DeleteMapping("/{starno}")
	@ApiOperation(value = "관심지역 삭제", notes = "해당 관심지을 삭제합니다.", response = Map.class)
	private ResponseEntity<Map<String, Object>> deleteStar(@PathVariable String starno, @RequestHeader("access-token") final String header) throws Exception{
		
		// 관심지역삭제 시작
		logger.debug("start delete star {} ",starno);
		// 토큰값 가져오기
		Map<String, Object> tokenValue = jwtService.get(header);
		// body에서 관심지역번호 가져오기
		int starNo = Integer.parseInt(starno);
		MemberDto memberDto = memberService.getMember(tokenValue.get("userid").toString());
		
		logger.debug("ApartmentController  ! deletestar {}", starNo);
		
		Map<String, Object> result = new HashMap<>();
		
		if(memberDto != null) {
			try {			
				starService.deleteStar(starNo);
				List<StarDto> list = starService.listStar(tokenValue.get("userid").toString());
				result.put("message", SUCCESS);
				result.put("stars", list);
				return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
			} catch (Exception e) {
				result.put("message", FAIL);
				result.put("error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			result.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping(value="/check/{aptCode}")
	@ApiOperation(value = "관심지역 기 추가여부 확인", notes = "관심지역이 이미 등록되었는지 확인하고, star객체를 반환합니다..", response = StarDto.class)
	private ResponseEntity<Map<String, Object>> checkstar(@PathVariable String aptCode, @RequestHeader("access-token") final String header) throws Exception{
		
		Map<String, Object> tokenValue = jwtService.get(header);
		String userId = tokenValue.get("userid").toString();
		logger.debug("ApartmentController  ! checkstar {} {}",aptCode, userId);
		StarDto starDto = new StarDto();
		starDto.setAptCode(aptCode);
		starDto.setUserId(userId);
		Map<String, Object> result = new HashMap<>();
		try {
			StarDto star = starService.getStar(starDto);
			result.put("message", SUCCESS);
			result.put("star", star);
			logger.debug("ApartmentController  ! checkstar {}",star);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("message", FAIL);
			result.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/log/{aptCode}")
	@ApiOperation(value = "로그 저장", notes = "로그를 수집합니다.", response = Void.class)
	private ResponseEntity<Void> addLog(@PathVariable String aptCode) throws Exception{
		starService.addLog(aptCode);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
