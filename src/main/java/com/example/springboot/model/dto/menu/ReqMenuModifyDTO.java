package com.example.springboot.model.dto.menu;

import com.example.springboot.model.vo.menu.MenuVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ReqMenuModifyDTO {
	@NotNull
	@Min(1)
	private Long id;

	@NotNull
	@Min(1)
	private Long beforeParentId;

	@NotNull
	@Min(1)
	private Long afterParentId;

	@NotBlank
	private String menuName;

	@NotNull
	@Min(1)
	private int seq;

	@NotBlank
	private String useYn;

	public MenuVO toVO() {
		return MenuVO.builder()
				.parentId(this.afterParentId)
				.menuName(this.menuName)
				.seq(this.seq)
				.useYn(this.useYn)
				.build();
	}
}
