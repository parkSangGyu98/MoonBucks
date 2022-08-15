package com.varxyz.javaCafe.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.javaCafe.dto.Category;


public class CategoryDao {
private JdbcTemplate jdbcTemplate;
	
	public CategoryDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 카테고리 추가
	public void addCategory(String cateName, String picture) {
		String sql = "INSERT INTO Category (name, picture) VALUES (?, ?)";
		jdbcTemplate.update(sql, cateName, picture);
	}
	
	// 카테고리 삭제
	public void deleteCategory(String cateName) {
		String sql = "DELETE FROM Category WHERE name = ?";
		jdbcTemplate.update(sql, cateName);
	}
	
	// 카테고리 이름 변경을 위한 메뉴테이블에 있는 카테고리이름 변경해주기 //기본키라 변경이 불가 -> 방법 찾아야함
	public void updateCategoryInMenu(String prevName, String afterName) {
		String sql = "UPDATE Menu SET categoryName = ? WHERE categoryName = ?";
		jdbcTemplate.update(sql, afterName, prevName);
	}
	
	
	// 카테고리 이름 변경 //기본키라 변경이 불가 -> 방법 찾아야함
	public void updateCategory(String prevName, String afterName) {
		String sql = "UPDATE Category SET name = ? WHERE name = ?";
		updateCategoryInMenu(prevName, afterName);
		jdbcTemplate.update(sql, afterName, prevName);
	}
	
	public void updateCategoryPic(String name, String picture) {
		String sql = "UPDATE Category SET picture = ? WHERE name = ?";
		jdbcTemplate.update(sql, picture, name);
	}
	
	// 모든 카테고리명 조회 
	public List<Category> findAllCateName(){
		String sql = "SELECT * FROM Category";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
	}
	
}
