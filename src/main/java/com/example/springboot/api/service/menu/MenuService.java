package com.example.springboot.api.service.menu;

import com.example.springboot.api.mapper.menu.MenuMapper;
import com.example.springboot.model.dto.menu.ReqMenuModifyDTO;
import com.example.springboot.model.dto.menu.ResMenuModifyListDTO;
import com.example.springboot.model.dto.menu.ResMenuSearchDTO;
import com.example.springboot.model.dto.menu.ResMenuSearchListDTO;
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
	public List<ResMenuSearchListDTO> getAllMenu() {
		/**
		 * for in 문을 이용한 response 에 사용되는 DTO mapping 작업

		 List<MenuVO> menuList = menuMapper.findAll();
		 List<ResMenuSearchListDTO> responseList = new ArrayList<>();
		 for(MenuVO vo : menuList) {
		 	responseList.add(new ResMenuSearchListDTO(vo));
		 }
		 return responseList;

		 */

		/**
		 * for 문을 이용한 response 에 사용되는 DTO mapping 작업

		 List<MenuVO> menuList = menuMapper.findAll();
		 List<ResMenuSearchListDTO> responseList = new ArrayList<>();
		 for(int i=0; i<menuList.size(); i++) {
		 	responseList.add(new ResMenuSearchListDTO(menuList.get(i)));
		 }
		 return responseList;

		 */

		/* stream 을 이용한 response 에 사용되는 DTO 로 mapping 작업 */
		return menuMapper.findAll().stream()
				.map(ResMenuSearchListDTO::new)
				.collect(Collectors.toList());
	}

	/**
	 * 식별키에 해당하는 메뉴를 가져온다.
	 * @param id 식별키
	 * @return
	 */
	public ResMenuSearchDTO getOneMenu(Long id) {
		/**
		 * Optional 객체를 이용한 null 체크
		 * return 받는 객체를 Optional 객체로 parsing하게 되면 기본적인 null 체크 및 empty 객체 생성 가능
		 * .orElseThrow: 조회해 온 객체가 null 일 경우 exception을 던진다.
		 */
		MenuVO vo = menuMapper.findOneById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Menu."));

		return new ResMenuSearchDTO(vo);
	}

	/**
	 * 메뉴를 수정한다.
	 * @param dto 수정할 메뉴 정보
	 */
	@Transactional
	public List<ResMenuModifyListDTO> modifyMenu(ReqMenuModifyDTO.ModifyList dto) {
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

		return menuMapper.findAll().stream()
				.map(ResMenuModifyListDTO::new)
				.collect(Collectors.toList());
	}

	/**
	 * 메뉴를 삭제한다.
	 * @param id 식별키
	 */
	@Transactional
	public void removeMenu(Long id) {
		/**
		 * 삭제할 데이터를 조회 후 조회한 데이터가 유효한 경우 삭제 처리한다.
		 */
		MenuVO vo = menuMapper.findOneById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Menu."));
		menuMapper.removeById(vo.getId());
	}
}
