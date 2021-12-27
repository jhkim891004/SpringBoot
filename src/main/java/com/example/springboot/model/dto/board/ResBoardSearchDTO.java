package com.example.springboot.model.dto.board;

import com.example.springboot.model.vo.board.BoardVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResBoardSearchDTO {
	private Long id;
	private String title;
	private String content;

	public ResBoardSearchDTO(BoardVO vo) {
		this.id = vo.getId();
		this.title = vo.getTitle();
		this.content = vo.getContent();
	}
}
