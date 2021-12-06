package com.example.springboot.api.controller.menu;

import com.example.springboot.api.service.menu.MenuService;
import com.example.springboot.common.response.success.SuccessResponse;
import com.example.springboot.model.dto.menu.ReqMenuModifyDTO;
import com.example.springboot.model.dto.menu.ResMenuSearchDTO;
import com.example.springboot.model.vo.menu.MenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuController {
	private final MenuService menuService;

	@GetMapping("/api/v1/menu")
	public SuccessResponse<List<ResMenuSearchDTO>> getAllMenu() {
		return new SuccessResponse<>(HttpStatus.OK, menuService.getAllMenu());
	}

	@GetMapping("/api/v1/menu/{id}")
	public SuccessResponse<ResMenuSearchDTO> getOneMenu(@PathVariable("id") Long id) {
		return new SuccessResponse<>(HttpStatus.OK, menuService.getOneMenu(id));
	}

	@PutMapping("/api/v1/menu")
	public SuccessResponse modifyMenu(@RequestBody @Valid ReqMenuModifyDTO.ModifyList dto) {
		menuService.modifyMenu(dto);
		return new SuccessResponse<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/api/v1/menu/{id}")
	public SuccessResponse removeMenu(@PathVariable("id") Long id) {
		menuService.removeMenu(id);
		return new SuccessResponse<>(HttpStatus.NO_CONTENT);
	}
}
