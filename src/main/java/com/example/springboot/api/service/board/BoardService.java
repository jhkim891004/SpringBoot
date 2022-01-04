package com.example.springboot.api.service.board;

import com.example.springboot.api.mapper.board.BoardMapper;
import com.example.springboot.model.dto.board.ResBoardSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper boardMapper;

	/**
	 * 게시물 목록을 조회한다.
	 * @param menuId
	 * @return
	 */
	public List<ResBoardSearchDTO> getBoardList(Long menuId) {
		/**
		 * 데이터를 조회한 후 stream을 이용하여 ResBoardSearchDTO에 매핑 작업
		 * ResBoardSearchDTO::new => 메소드참조 찾아보기
		 */
		return boardMapper.findAllByMenuId(menuId).stream()
				.map(ResBoardSearchDTO::new)
				.collect(Collectors.toList());
	}
	

	/**
	 * 게시물 목록을 조회한다.
	 * @param menuId
	 * @return
	 */
	public void removeBoard(Long menuId) {
		
	}


}
