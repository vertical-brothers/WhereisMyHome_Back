package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.apartment.model.StarDto;

public interface StarService {

	void addStar(StarDto starDto) throws Exception;
	List<StarDto> listStar(String userId) throws Exception;
	void deleteStar(int starNo) throws Exception;
	StarDto getStar(StarDto starDto) throws Exception;
	
	/* 로그 수집 관련 service */
	void addLog(String aptCode) throws Exception;
}
