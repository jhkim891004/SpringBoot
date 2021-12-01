package com.example.springboot.model.dto.menu;

import com.example.springboot.model.vo.menu.MenuVO;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ResMenuModifyDTO {
	private Long id;
	private Long parentId;
	private String menuName;
	private int seq;
	private String useYn;
	private String modDate;

	public ResMenuModifyDTO(MenuVO vo) {
		this.id = vo.getId();
		this.parentId = vo.getParentId();
		this.menuName = vo.getMenuName();
		this.seq = vo.getSeq();
		this.useYn = vo.getUseYn();
		this.modDate = vo.getModDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
