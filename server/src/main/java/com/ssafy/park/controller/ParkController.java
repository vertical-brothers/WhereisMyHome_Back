package com.ssafy.park.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.park.model.ParkDto;
import com.ssafy.park.model.service.ParkService;

@Controller
@RequestMapping("/park")
public class ParkController {
	
    @Autowired
	public ParkService parkService;

	@GetMapping("/getAllParks")
	@ResponseBody
	public ResponseEntity<?> getAllParks(@RequestParam Map<String,String> map, Model model) throws IOException {
        String lng = map.get("lng");
        String lat = map.get("lat");
		try {
			List<ParkDto> list=parkService.selectparks(lat, lng);
			if(list!=null&&!list.isEmpty()) {
				return new ResponseEntity<List<ParkDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
