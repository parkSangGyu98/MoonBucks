package com.varxyz.javaCafe.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.javaCafe.dto.Menu;

public class MenuDao {
	private JdbcTemplate jdbcTemplate;

	public MenuDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 메뉴 추가
	public void addMenu(Menu menu, String pictureName) {
		String sql = "INSERT INTO Menu (name, price, quantity, categoryName, picture)" + " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, menu.getName(), menu.getPrice(), menu.getQuantity(), menu.getCategoryName(), pictureName);
	}

	// 메뉴 삭제
	public void deleteMenu(String menuName) {
		String sql = "DELETE FROM Menu WHERE name = ?";
		jdbcTemplate.update(sql, menuName);
	}
	
	// 메뉴명,가격 수정
	public void updateMenu(String prevName, String afterName, long price) {
		String sql = "UPDATE Menu SET name = ?, price = ? WHERE name = ?";
		jdbcTemplate.update(sql, afterName, price, prevName);
	}

	// 카테고리명으로 메뉴정보 가져오기
	public List<Menu> getMenu(String categoryName) {
		String sql = "SELECT * FROM Menu WHERE categoryName = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class), categoryName);
	}

	// 이름으로 메뉴정보 가져오기
	public List<Menu> getMenuByName(String menuName) {
		String sql = "SELECT * FROM Menu WHERE name = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class), menuName);
	}
	
	// 모든메뉴정보 가져오기
	public List<Menu> getAllMenu(){
		String sql = "SELECT * FROM Menu";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class));
	}
}
