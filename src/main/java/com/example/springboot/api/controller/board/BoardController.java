package com.example.springboot.api.controller.board;

import java.util.List;

import com.example.springboot.api.service.board.BoardService;
import com.example.springboot.common.response.success.SuccessCode;
import com.example.springboot.common.response.success.SuccessResponse;
import com.example.springboot.model.dto.board.ResBoardSearchDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

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
	@Operation(summary = "단일 게시물 조회", description = "단일 게시물을 조회한다.")
	@GetMapping("/api/v1/menu/board/{id}")
	public SuccessResponse<ResBoardSearchDTO> getOneBoard(
		@Parameter(description = "식별키", required = true, example = "1, 2, 3, ...")
		@PathVariable("id") Long id) {
			return new SuccessResponse<>(SuccessCode.OK, boardService.getOneBoard(id));
	}
	
	// TODO. 게시물 추가
	@Operation(summary = "게시물 추가", description = "게시물을 추가한다.")
	// @PostMapping("/api/v1/{menuId}/board")
	
	// TODO. 게시물 수정
	@Operation(summary = "게시물 수정", description = "게시물을 수정한다.")
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/api/v1/board")
	public SuccessResponse modifyBoard(@RequestBody @Valid ReqMenuModifyDTO.ModifyList dto) {
		return new SuccessResponse<>(SuccessCode.MODIFIED, boardService.modifyBoard(dto));
	}
	
	// TODO. 게시물 삭제
	
}
