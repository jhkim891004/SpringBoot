package com.example.springboot.model.dto.menu;

import com.example.springboot.model.vo.menu.MenuVO;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Getter
public class ResMenuModifyDTO {
	private final SimpleDateFormat simpleFormat =
			new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

	private Long id;
	private Long parentId;
	private String menuName;
	private int seq;
	private String useYn;
	private String regDate;
	private String regId;
	private String modDate;
	private String modId;

	public ResMenuModifyDTO(MenuVO vo) {
		this.id = vo.getId();
		this.parentId = vo.getParentId();
		this.menuName = vo.getMenuName();
		this.seq = vo.getSeq();
		this.useYn = vo.getUseYn();
		this.regDate = simpleFormat.format(vo.getRegDate());
		this.regId = vo.getRegId();
		this.modDate = simpleFormat.format(vo.getModDate());
		this.modId = vo.getModId();
	}
}
