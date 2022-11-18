package com.ssafy.member.controller;


import java.util.Map;


import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.jwt.service.JwtService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/user")
@Api("사용자 REST 컨트롤러 API v1")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private  MemberService memberService;
	@Autowired
	private  JwtService jwtService;

//	@GetMapping("/join")
//	public String join() {
//		return "user/join";
//	}

	@ApiOperation(value = "아이디 체크", notes ="회원가입시사용가능한 아이디인지 확인한다.")
	@GetMapping("/{userid}")
	@ResponseBody
	public ResponseEntity<Void> idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		if(cnt == 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
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
	@PostMapping()
	@ResponseBody
	public ResponseEntity<Void> join(@RequestBody MemberDto memberDto) throws Exception{
		logger.debug("memberDto info : {}", memberDto);
			memberService.joinMember(memberDto);
			logger.debug("회원가입정보 : {}", memberDto);
			
			String refreshToken = jwtService.createRefreshToken("userid", memberDto.getUserId());
			logger.debug("refresh token info : {}", refreshToken);
			memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);
			logger.debug("회 refreshToken 정보 : {}", refreshToken);
			ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.CREATED);
			return result;
		
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) {
		logger.debug("map : {}", map.get("userid"));
		try {
			MemberDto memberDto = memberService.loginMember(map);
			logger.debug("memberDto : {}", memberDto);
			if(memberDto != null) {
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/board");
				if("ok".equals(map.get("saveid"))) {
					cookie.setMaxAge(60*60*24*365*40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				logger.debug("userrole : {}", memberDto.getUserRole());

				return "index";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/list")
	public String list(Model model) {
		try {
			model.addAttribute("users",memberService.listMember());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/user/list";
	}
	
	@GetMapping("/info")
	public String info(Model model, HttpSession session) {		
		try {
			MemberDto memberDto = memberService.getMember(((MemberDto)(session.getAttribute("userinfo"))).getUserId());
			logger.debug("memberDto info : {}", memberDto);
			model.addAttribute("user",memberDto);
			return "/user/info";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 정보 수정 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute MemberDto member, Model model, HttpSession session) {		
		try {
			logger.debug("update : {}", member);
			memberService.updateMember(member);
			logger.debug("memberDto info : {}", member);
			return "redirect:/user/info";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 정보 수정 중 문제 발생!!!");
			return "error/error";
		}
	}

}
