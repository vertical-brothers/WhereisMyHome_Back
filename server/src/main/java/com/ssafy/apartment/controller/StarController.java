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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.apartment.model.StarDto;
import com.ssafy.apartment.model.service.StarService;
import com.ssafy.member.model.MemberDto;

@Controller
@RequestMapping(value="/star")
public class StarController {
	
	private StarService starService;	//관심지역
	
	@Autowired
	public StarController(StarService starService) {
		this.starService = starService;
	}

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

	@GetMapping(value = "/mvaptapi")
	public String mvaptapi(Model model) throws Exception {
		logger.debug("ApartmentController  ! mvaptapi  ");
		return "/apartment/aptapi";
	}
	
	@PostMapping(value="/addstar")
	private String addstar(@RequestParam("dong") String dong, HttpSession session, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		map.put("userId", memberDto.getUserId());
		map.put("dongCode", dong);
		map.put("dealYM", "");
		logger.debug("ApartmentController ! addstar {} ", map);
		try {
			starService.addStar(map);
			return "redirect:/star/liststar";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error";
		}
	}
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
