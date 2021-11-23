package com.example.springboot.model.dto.menu;

import com.example.springboot.model.vo.menu.MenuVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MenuResponseDTO {
	private Long id;
	private Long parentId;
	private String menuName;
	private List<MenuResponseDTO> children;

	@Builder
	public MenuResponseDTO(MenuVO vo) {
		this.id = vo.getId();
		this.parentId = vo.getParentId();
		this.menuName = vo.getMenuName();
		this.children = vo.getChildren().stream()
				.map(MenuResponseDTO::new)
				.collect(Collectors.toList());
	}
}
