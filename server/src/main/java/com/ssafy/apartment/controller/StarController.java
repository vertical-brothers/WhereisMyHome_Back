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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.apartment.model.StarDto;
import com.ssafy.apartment.model.service.StarService;
import com.ssafy.jwt.service.JwtService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
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
	
	@PostMapping(value = "deletestar")
	private String deletestar(@RequestParam("starno") String starno, HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		logger.debug("ApartmentController  ! deletestar {}", starno);
		if(memberDto != null) {
			try {
				int starNo = Integer.parseInt(starno);
				starService.deleteStar(starNo);
				return "redirect:/star/liststar";
			} catch (Exception e) {
				e.printStackTrace();
				return "/error/error.jsp";
			}
		} else {
			return "/user/login";
		}
	}

	@GetMapping(value="/liststar")
	private String liststar(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		System.out.println(memberDto.getUserId());
			try {
				List<StarDto> list = starService.listStar(memberDto.getUserId());
				logger.debug("{}", list.size());
				model.addAttribute("stars", list);
				return "/apartment/liststar";
			} catch (Exception e) {
				e.printStackTrace();
				return "/user/login";
			}
	}

//	@GetMapping(value = "/mvaptapi")
//	public String mvaptapi(Model model) throws Exception {
//		logger.debug("ApartmentController  ! mvaptapi  ");
//		return "/apartment/aptapi";
//	}
	
//	@PostMapping(value="/addstar")
//	private String addstar(@RequestParam("dong") String dong) {
//		Map<String, String> map = new HashMap<String, String>();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		map.put("userId", memberDto.getUserId());
//		map.put("dongCode", dong);
//		map.put("dealYM", "");
//		logger.debug("ApartmentController ! addstar {} ", map);
//		try {
//			starService.addStar(map);
//			return "redirect:/star/liststar";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "/error/error";
//		}
//	}
	
	/*
	 * 2022-11-2 이인재
	 * 관심지역 추가 Api
	 * header로 access-token을 주면 됩니다.
	 * pathvariable타입의 변수를 주기 때문에 url에 dongcode를 넘겨주어야 합니다
	 * 예시)
	 * http://localhost:8080/whereismyhome/star/1111010100
	 * */
	@PostMapping("/{dong}")
	@ResponseBody
	@ApiOperation(value = "관심 지역추가", notes = "유저에게 해당 관심지역을 추가합니다.", response = Map.class)
	private ResponseEntity<Map<String, Object>> addstar(@PathVariable String dong, @RequestHeader("access-token") final String header) {
		// 토큰값 가져오기
		Map<String, Object> tokenValue = jwtService.get(header);
		Map<String, String> map = new HashMap<String, String>();
	
		// 토큰 값에서 userid만 가져오기
		String userId = tokenValue.get("userid").toString();
		map.put("userId", userId);
		map.put("dongCode", dong);
		map.put("dealYM", "");
		logger.debug("ApartmentController ! addstar {} ", map);
		
		Map<String, Object> result = new HashMap<>();
				
		try {
			starService.addStar(map);
			result.put("message", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@GetMapping
	private ResponseEntity<?> test(@RequestHeader("access-token") final String header){
		logger.debug(header);
		Map<String, Object> tmp = jwtService.get(header);
		logger.debug("test value is {}", tmp);
		logger.debug(tmp.get("userid").toString());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
