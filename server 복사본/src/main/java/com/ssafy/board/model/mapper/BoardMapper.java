package com.ssafy.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> listArticle(Map<String, Object> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void writeArticle(BoardDto board) throws Exception;
	void modifyArticle(BoardDto board) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	int totalArticleCount(Map<String, String> map) throws Exception;
	void updateHit(int articleNo) throws Exception;
}
