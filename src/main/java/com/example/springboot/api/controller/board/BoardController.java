package com.example.springboot.api.controller.board;

import com.example.springboot.api.service.board.BoardService;
import com.example.springboot.common.response.success.SuccessCode;
import com.example.springboot.common.response.success.SuccessResponse;
import com.example.springboot.model.dto.board.ResBoardSearchDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Board")
@RestController
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@Operation(summary = "게시물 목록 조회", description = "게시물 목록을 조회한다.")
	@GetMapping("/api/v1/{menuId}/board")
	public SuccessResponse<List<ResBoardSearchDTO>> getBoardList(@PathVariable Long menuId) {
		return new SuccessResponse<>(SuccessCode.OK, boardService.getBoardList(menuId));
	}
	
	// TODO. 게시물 단건 조회
	
	// TODO. 게시물 추가
	
	// TODO. 게시물 수정
	
	// TODO. 게시물 삭제
	
}
