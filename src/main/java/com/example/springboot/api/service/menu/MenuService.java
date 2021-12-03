package com.example.springboot.api.service.menu;

import com.example.springboot.api.mapper.menu.MenuMapper;
import com.example.springboot.common.exception.BizException;
import com.example.springboot.common.response.error.ErrorCode;
import com.example.springboot.model.dto.menu.ReqMenuModifyDTO;
import com.example.springboot.model.dto.menu.ResMenuSearchDTO;
import com.example.springboot.model.vo.menu.MenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {
	private final MenuMapper menuMapper;

	/**
	 * 모든 메뉴를 가져온다.
	 * @return
	 */
	public List<ResMenuSearchDTO> getAllMenu() {
		/* stream 을 사용하여 response 에 사용되는 DTO 로 mapping 작업 */
		return menuMapper.findAll().stream()
				.map(ResMenuSearchDTO::new)
				.collect(Collectors.toList());
	}

	/**
	 * 식별키에 해당하는 메뉴를 가져온다.
	 * @param id 식별키
	 * @return
	 */
	public ResMenuSearchDTO getOneMenu(Long id) {
		MenuVO resultVO = menuMapper.findOneById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Menu."));
		return new ResMenuSearchDTO(resultVO);
	}

	/**
	 * 메뉴를 수정한다.
	 * @param dto 수정할 메뉴 정보
	 */
	@Transactional
	public void modifyMenu(ReqMenuModifyDTO.ModifyList dto) {
		menuMapper.removeAll();

		dto.getList().stream()
				.map(ReqMenuModifyDTO::toVO)
				.forEach(depth1 -> {
					menuMapper.save(depth1);
					MenuVO parentVo = menuMapper.findOneById(depth1.getId())
							.orElseThrow(() -> new NoSuchElementException("Not Found Menu."));

					depth1.getChildren(parentVo.getId()).stream()
							.forEach(depth2 -> {
								menuMapper.save(depth2);

								depth2.getChildren(depth2.getId()).stream()
										.forEach(depth3 -> {
											menuMapper.save(depth3);
										});
							});
				});

		throw new BizException("Success.. Execute Rollback..", ErrorCode.BAD_REQUEST);
	}

	/**
	 * 메뉴를 삭제한다.
	 * @param id 식별키
	 */
	@Transactional
	public void removeMenu(Long id) {
		menuMapper.removeById(id);
	}
}
