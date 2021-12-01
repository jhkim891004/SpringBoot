package com.example.springboot.api.controller.menu;

import com.example.springboot.api.service.menu.MenuService;
import com.example.springboot.common.response.success.SuccessResponse;
import com.example.springboot.model.dto.menu.ReqMenuModifyDTO;
import com.example.springboot.model.dto.menu.ResMenuModifyDTO;
import com.example.springboot.model.dto.menu.ResMenuSearchDTO;
import com.example.springboot.model.vo.menu.MenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MenuController {
	private final MenuService menuService;

	@GetMapping("/api/v1/menu")
	public SuccessResponse<List<ResMenuSearchDTO>> getAllMenu() {
		return new SuccessResponse<>(HttpStatus.OK, menuService.getAllMenu().stream()
				.map(ResMenuSearchDTO::new)
				.collect(Collectors.toList()));
	}

	@GetMapping("/api/v1/menu/{id}")
	public SuccessResponse<ResMenuSearchDTO> getOneMenu(@PathVariable("id") Long id) {
		MenuVO menu = menuService.getOneMenu(id);
		return new SuccessResponse<>(HttpStatus.OK, new ResMenuSearchDTO(menu));
	}

	@PutMapping("/api/v1/menu")
	public SuccessResponse<ResMenuModifyDTO> modifyMenu(@RequestBody @Valid ReqMenuModifyDTO dto) {
		MenuVO menu = menuService.modifyMenu(dto);
		return new SuccessResponse<>(HttpStatus.CREATED, new ResMenuModifyDTO(menu));
	}

}
