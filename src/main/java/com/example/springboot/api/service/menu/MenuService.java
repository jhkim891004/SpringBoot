package com.example.springboot.api.service.menu;

import com.example.springboot.api.mapper.menu.MenuMapper;
import com.example.springboot.common.exception.BizException;
import com.example.springboot.common.response.error.ErrorCode;
import com.example.springboot.model.dto.menu.ReqMenuModifyDTO;
import com.example.springboot.model.vo.menu.MenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {
	private final MenuMapper menuMapper;

	/**
	 * 모든 메뉴를 가져온다.
	 * @return
	 */
	public List<MenuVO> getAllMenu() {
		return menuMapper.findAll();
	}

	/**
	 * 식별키에 해당하는 메뉴를 가져온다.
	 * @param id 식별키
	 * @return
	 */
	public MenuVO getOneMenu(Long id) {
		return menuMapper.findOneById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Menu."));
	}

	@Transactional
	public MenuVO modifyMenu(ReqMenuModifyDTO dto) {
		List<MenuVO> beforeVOList = new ArrayList<>();
		List<MenuVO> afterVOList = menuMapper.findAllByParentId(dto.getAfterParentId());

		if(dto.getBeforeParentId() != dto.getAfterParentId()) {
			beforeVOList = menuMapper.findAllByParentId(dto.getBeforeParentId());

			menuMapper.removeByParentId(dto.getBeforeParentId());
			AtomicInteger seq = new AtomicInteger(1);
			beforeVOList.stream()
					.filter(entity -> !entity.isExists(dto.getId()))
					.forEach(entity -> {
						MenuVO saveVO = MenuVO.builder()
								.parentId(entity.getParentId())
								.menuName(entity.getMenuName())
								.seq(seq.get())
								.useYn(entity.getUseYn())
								.build();
						menuMapper.save(saveVO);
					});

			menuMapper.removeByParentId(dto.getAfterParentId());
			afterVOList.stream()
					.filter(entity -> entity.getSeq() >= dto.getSeq())
					.forEach(entity -> {
						entity.incrementSeq();
						menuMapper.save(entity);
					});
		}

		if(dto.getBeforeParentId() == dto.getAfterParentId()) {
			afterVOList.add(dto.toVO());
			menuMapper.removeByParentId(dto.getAfterParentId());
			afterVOList.stream()
					.filter(entity -> !entity.isExists(dto.getId()))
					.sorted(Comparator.comparing(MenuVO::getSeq))
					.forEach(entity -> {
						entity.incrementSeq();
						menuMapper.save(entity);
					});
		}

		throw new BizException("TEST", ErrorCode.INTERNAL_SERVER_ERROR);
//		return dto.toVO();
	}
}
