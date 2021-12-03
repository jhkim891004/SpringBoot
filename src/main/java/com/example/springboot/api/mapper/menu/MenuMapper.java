package com.example.springboot.api.mapper.menu;

import com.example.springboot.model.vo.menu.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
	List<MenuVO> findAll();
	Optional<MenuVO> findOneById(Long id);
	List<MenuVO> findAllByParentId(Long parentId);
	int removeAll();
	int removeById(Long id);
	int removeByParentId(Long parentId);
	int save(MenuVO vo);
	int saveAll(List<MenuVO> vos);
}
