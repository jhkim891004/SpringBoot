package com.example.springboot.model.vo.menu;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MenuVO {
	private Long id;
	private Long parentId;
	private String menuName;
	private int seq;
	private String useYn;
	private LocalDateTime regDate;
	private String regId;
	private LocalDateTime modDate;
	private String modId;

	private List<MenuVO> children = new ArrayList<>();

	/**
	 * 메뉴 정보를 수정한다.
	 * @param parentId
	 * @param menuName
	 * @param seq
	 * @param useYn
	 */
	public void update(Long parentId, String menuName, int seq, String useYn) {
		this.parentId = parentId;
		this.menuName = menuName;
		this.seq = seq;
		this.useYn = useYn;
	}

	/**
	 * 메뉴 존재여부 확인
	 * @param id 식별키
	 * @return
	 */
	public boolean isExists(Long id) {
		return this.id == id;
	}

	/**
	 * 순서 증가
	 */
	public void incrementSeq() {
		this.seq = seq == 0 ? 1 : ++seq;
	}

	@Builder
	public MenuVO(Long id, Long parentId, String menuName, int seq, String useYn, LocalDateTime regDate, String regId, LocalDateTime modDate, String modId) {
		this.id = id;
		this.parentId = parentId;
		this.menuName = menuName;
		this.seq = seq;
		this.useYn = useYn;
		this.regDate = regDate;
		this.regId = regId;
		this.modDate = modDate;
		this.modId = modId;
	}
}
