package com.varxyz.javaCafe.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.javaCafe.dao.CategoryDao;
import com.varxyz.javaCafe.data.DataSourceConfig;
import com.varxyz.javaCafe.dto.Category;


public class CategoryService {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	CategoryDao dao = context.getBean("categoryDao", CategoryDao.class);
	
	// 카테고리 추가
	public void addCategory(String cateName, String picture) {
		dao.addCategory(cateName, picture);
	}
	
	// 카테고리 삭제
	public void deleteCategory(String cateName) {
		dao.deleteCategory(cateName);
	}
	
	// 모든 카테고리명 조회
	public List<Category> findAllCateName(){
		return dao.findAllCateName();
	}
	
	public void updateCategoryInMenu(String prevName, String afterName) {
		dao.updateCategoryInMenu(prevName, afterName);
	}
	
	// 카테고리 이름 변경
	public void updateCategory(String prevName, String afterName) {
		dao.updateCategory(prevName, afterName);
	}
	
	// 카테고리 사진 변경
	public void updateCategoryPic(String name, String picture) {
		dao.updateCategoryPic(name, picture);
	}
	
}
