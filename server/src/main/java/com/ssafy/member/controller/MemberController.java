package com.ssafy.member.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.jwt.service.JwtService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@Api("사용자 REST 컨트롤러 API v1")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private  MemberService memberService;
	@Autowired
	private  JwtService jwtService;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "아이디 체크", notes ="회원가입시사용가능한 아이디인지 확인한다.")
	@GetMapping("/idcheck/{userid}")
	public ResponseEntity<String> idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		if(cnt == 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.CONFLICT);
	}

	/*
	입력으로 들어오는 body의 parameter는 다음과 같다.
	userid : 사용자 id
	username : 사용자 이름
	userpwd : 비밀번호
	useremail : 사용자 email
	userphone : 사용자 전화번호
	userrole : 역할
	*/
	@ApiOperation(value = "아이디 회원가입", notes ="사용자 회원가입 API")
	@PostMapping
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) throws Exception{
		logger.debug("memberDto info : {}", memberDto);
			memberService.joinMember(memberDto);
			logger.debug("회원가입정보 : {}", memberDto);
			
			String refreshToken = jwtService.createRefreshToken("userid", memberDto.getUserId());
			logger.debug("refresh token info : {}", refreshToken);
			memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);
			logger.debug("회 refreshToken 정보 : {}", refreshToken);
			ResponseEntity<String> result = new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
			return result;
		
	}
	
	/*
	 * 회원정보 수정 API
	 * input : MemberDto
	 * output : None
	 * 
	 * 22.11.20 장한결
	 * 
	 * */
	@ApiOperation(value="회원정보 수정", notes="사용자 정보 수정 API")
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody MemberDto memberDto) throws Exception{
		logger.debug("memberDto info : {}", memberDto);
			memberService.updateMember(memberDto);
			logger.debug("회원정보 수정 API Call : {}", memberDto);
			ResponseEntity<String> result = new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			return result;
	}
	
	/*
	 * 회원정보 삭제 API
	 * input : userid
	 * output : None
	 * 
	 * 실제로 회원정보 삭제하지 않고, userdel만 1로 변경
	 * 
	 * 22.11.20 장한결
	 * 
	 * */
	@ApiOperation(value="회원정보 삭제", notes="사용자 정보 수정 API")
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId) throws Exception{
		logger.debug("회원정보 삭제 API Call : {}", userId);
		memberService.deleteMember(userId);
		ResponseEntity<String> result = new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return result;
	}

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).") MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			logger.debug("input  info is {}", memberDto);
			MemberDto loginUser = memberService.login(memberDto);
			logger.debug("login user info is {}", loginUser);
			if (loginUser != null) {
				
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());// key, data
				memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			@RequestHeader("Authorization") final String header) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(header)) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
//				MemberDto memberDto = memberService.user(userid);
//				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "회원 리스트 반환", notes = "회원 리스트를 반환한다.")
	@GetMapping
	public ResponseEntity<List<MemberDto>> getlist() throws Exception {
		logger.debug("list 반환 메서드 호출 ===================>");
		List<MemberDto> list = memberService.listMember();
		return new ResponseEntity<List<MemberDto>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.getMember(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/token/{userid}")
	public ResponseEntity<Map<String, Object>> checkToken(@RequestHeader("Authorization") final String header, 
			@PathVariable("userid") String userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		boolean flag = jwtService.checkToken(header);		
		logger.debug("token value is  {}",header);		
		logger.debug("result is {}", flag);
		if(flag) {			
//			resultMap = jwtService.get(header);
			MemberDto memberDto = memberService.getMember(userId);
			resultMap.put("userInfo", memberDto);
			resultMap.put("message", SUCCESS);
			
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		else {
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value = "유저 수 조회", notes ="사용자 count API")
	@GetMapping("/count")
	public ResponseEntity<Integer> countUser() throws Exception {		
		logger.debug("count User 진입 ========>");
		Map<String, Object> resultMap = new HashMap<>();
//		boolean flag = jwtService.checkToken(header);		
		
		int count = memberService.listMember().size();
		
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}
}
