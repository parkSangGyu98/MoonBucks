package com.varxyz.javaCafe.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.javaCafe.dao.MenuDao;
import com.varxyz.javaCafe.data.DataSourceConfig;
import com.varxyz.javaCafe.dto.Menu;

public class MenuService {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	MenuDao dao = context.getBean("menuDao", MenuDao.class);
	
	// 메뉴 추가
	public void addMenu(Menu menu, String pictureName) {
		dao.addMenu(menu, pictureName);
	}
	
	// 메뉴 삭제
	public void deleteMenu(String menuName) {
		dao.deleteMenu(menuName);
	}
	
	// 메뉴 이름,가격 수정
	public void updateMenu(String prevName, String afterName, long price) {
		dao.updateMenu(prevName, afterName, price);
	}
	
	// 카테고리명으로 메뉴정보 가져오기
	public List<Menu> getMenu(String categoryName){
		return dao.getMenu(categoryName);
	}
	
	// 이름으로 메뉴정보 가져오기
	public List<Menu> getMenuByName(String menuName){
		return dao.getMenuByName(menuName);
	}
	
	// 모든메뉴정보 가져오기
	public List<Menu> getAllMenu(){
		return dao.getAllMenu();
	}
	
	public void addInventory(String name, long quantity) {
		dao.addInventory(name, quantity);
	}
	
	public void deleteInventory(String name, long quantity) {
		dao.deleteInventory(name, quantity);
	}
	
}
