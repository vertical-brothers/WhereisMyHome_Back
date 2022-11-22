package com.ssafy.board.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardParameterDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
@Api("게시판 컨트롤러")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

//	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
//	@GetMapping(value="/")
//	private ResponseEntity<List<BoardDto>> list(@RequestParam Map<String, String> map) throws Exception {
//		logger.debug("debug msg board getmapping {}", map);
//		
//		if(map.get("key") == null) map.put("key", "");
//		if(map.get("word") == null) map.put("word", "");
//		List<BoardDto> list = boardService.listArticle(map);
//		logger.debug(list.toString());
//		logger.debug(Arrays.toString(list.toArray()));
//		int articlecnt = boardService.totalArticleCount(map);
//
//		int spl = SizeConstant.SIZE_PER_LIST;
//		int pl = SizeConstant.LIST_SIZE;
//
//		int pageno = Integer.parseInt(map.get("pgno"));
//
//		int startpage = (pageno - 1) / 10 * pl + 1;
//
//		int endpage = startpage + 9;
//		int lastpage = articlecnt / spl + 1;
//
//		map.put("start", pageno+"");
//		map.put("end", endpage+"");
//	// 33-20 < 10
//	if (articlecnt - spl * startpage < spl * pl) { // 전체 개수-현재페이지까지 개수가 지정된 페이지 수 보다 작으면
//			endpage = articlecnt / spl + 1;
//	}
//
//	ResponseEntity<List<BoardDto>> result = ResponseEntity.ok().body(list);
//		return result;
//
//	}
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<BoardDto>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.listArticle(boardParameterDto), HttpStatus.OK);
	}
	
	@GetMapping("/total")
	public ResponseEntity<Integer> totalArticleCount(@ApiParam(value = "게시글 개수를 가져오는 api", required = true) @RequestParam Map<String, String> map ) throws Exception {
		logger.info("totalArticleCount - 호출");
		return new ResponseEntity<Integer>(boardService.totalArticleCount(map), HttpStatus.OK);
	}
//	@PostMapping("/write")
//	private String write(BoardDto boardDto, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
////		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
////		if(memberDto != null) {
////			boardDto.setUserId(memberDto.getUserId());
//		try {
//			boardDto.setUserId("ssafy");
//			boardService.writeArticle(boardDto);
//			redirectAttributes.addAttribute("pgno", 1);
//			redirectAttributes.addAttribute("key", "");
//			redirectAttributes.addAttribute("word", "");
//			return "redirect:/board/list";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글작성 중 에러발생!!!");
//			return "/error/error";
//		}
////		} else {
////			return "/user/login";
////		}
//	}
	@ResponseBody
	@PostMapping("/")
	public ResponseEntity<?> write(@RequestBody BoardDto boardDto) throws Exception {
		logger.debug("이인재 BoardController board/update {}", boardDto);
		try {			
			boardService.writeArticle(boardDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@ResponseBody
	@GetMapping("/{articleNo}")	
	public ResponseEntity<?> view(@PathVariable int articleNo) throws Exception {
		logger.debug("input <========== {}", articleNo);
		boardService.updateHit(articleNo);
		BoardDto boardDto = boardService.getArticle(articleNo);

		ResponseEntity<BoardDto> result = ResponseEntity.ok().body(boardDto);

		return result;
	}

//	@GetMapping("/mvmodify")
//	private String mvModify(@RequestParam Map<String, String> map, HttpSession session, Model model) {
//		try {
////			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
////			if(memberDto != null) {
//			BoardDto boardDto = boardService.getArticle(Integer.parseInt(map.get("articleno")));
//			model.addAttribute("article", boardDto);
//			System.out.println(map);
//			return "/board/modify";
////			} else
////				return "/user/login";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글내용 얻는 중 문제 발생!!!");
//			return "/error/error";
//		}
//	}

	@ResponseBody
	@PutMapping("/")
	private ResponseEntity<?> modify(@RequestBody BoardDto boardDto, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) {
		logger.debug("BoardController board/update {}", boardDto);
		try {
			System.out.println("boardDto content: " + boardDto.getContent());
			boardService.modifyArticle(boardDto);
			BoardDto board = boardService.getArticle(boardDto.getArticleNo());
			if (board != null) {
				return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@DeleteMapping(value = "/{articleNo}")
	private ResponseEntity<String> delete(@PathVariable int articleNo, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) {
		logger.debug("BoardController delete {}.",articleNo);
		try {
			boardService.deleteArticle(articleNo);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
