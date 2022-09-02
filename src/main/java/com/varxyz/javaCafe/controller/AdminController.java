package com.varxyz.javaCafe.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.javaCafe.dto.Category;
import com.varxyz.javaCafe.dto.Menu;
import com.varxyz.javaCafe.service.CategoryService;
import com.varxyz.javaCafe.service.MenuService;

@Controller("controller.adminController")
public class AdminController {

	MenuService menuService = new MenuService();
	CategoryService categoryService = new CategoryService();

	@GetMapping("controller/admin")
	public String adminForm(Model model) {
		model.addAttribute("category", categoryService.findAllCateName());
		model.addAttribute("menu", menuService.getAllMenu());
		return "admin/admin";
	}

	@PostMapping("controller/deleteCategory")
	public String deleteCategory(Category category, Model model) {
		if (!category.getName().equals("unknown")) {
			categoryService.deleteCategory(category.getName());
			return "redirect:/controller/main";
		}
		model.addAttribute("msg", "카테고리를 선택해 주세요.");
		return "error/error";
	}

	@PostMapping("controller/deleteMenu")
	public String deleteCategory(Menu menu, Model model) {
		if (!menu.getName().equals("unknown")) {
			menuService.deleteMenu(menu.getName());
			return "redirect:/controller/main";
		}
		model.addAttribute("msg", "삭제할 메뉴를 선택해 주세요.");
		return "error/error";
	}

	@PostMapping("controller/readMenu")
	public String readMenu(Category category, Model model) {
		if (!category.getName().equals("unknown")) {
			List<Menu> menuList = new ArrayList<Menu>();
			menuList = menuService.getMenu(category.getName());
			model.addAttribute("menuList", menuList);
			return "admin/successReadMenu";
		}
		model.addAttribute("msg", "카테고리를 선택해 주세요.");
		return "error/error";
	}

	@GetMapping("controller/successReadMenu")
	public String successReadMenu() {
		return "admin/successReadMenu";
	}

	@PostMapping("controller/updateCategoryPic")
	public String updateCategory(Category category, @RequestParam("file") MultipartFile file, Model model) {

		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메소드
		long size = file.getSize();
		if (!category.getName().equals("unknown")) {
			if (fileRealName != "") {
				System.out.println("파일명 : " + fileRealName);
				System.out.println("용량크기(byte) : " + size);
				// 서버에 저장할 파일이름 fileextension으로 .jsp이런식의 확장자 명을 구함
				String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
				String uploadFolder = "C:\\Users\\psg\\NCS\\backend\\javaCafe\\src\\main\\webapp\\resources\\img\\category";

				/*
				 * 파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
				 * 타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시) 고유한 랜던 문자를 통해 db와 서버에 저장할
				 * 파일명을 새롭게 만들어 준다.
				 */

				UUID uuid = UUID.randomUUID();
				System.out.println(uuid.toString());
				String[] uuids = uuid.toString().split("-");

				String uniqueName = uuids[0];
				System.out.println("생성된 고유문자열 : " + uniqueName);
				System.out.println("확장자명 : " + fileExtension);

				File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); // 적용 후
				try {
					file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				categoryService.updateCategoryPic(category.getName(), uniqueName);
				return "redirect:/controller/home";

			} else {
				model.addAttribute("msg", "올바른 사진을 선택해 주세요.");
				return "error/error";
			}

		} else {
			model.addAttribute("msg", "카테고리를 선택해주세요.");
			return "error/error";
		}
	}

	@PostMapping("controller/updateMenu")
	public String updateMenu(String prevName, String afterName, long afterPrice, Model model) {
		if(!prevName.equals("unknown")) {
			if(!afterName.equals("")) {
				menuService.updateMenu(prevName, afterName, afterPrice);
				return "redirect:/controller/main";
			}else {
				model.addAttribute("msg", "변경될 메뉴를 선택해 주세요.");
				return "error/error";
			}
		}else {
			model.addAttribute("msg", "변경할 메뉴를 선택해 주세요.");
			return "error/error"; 
		}
	}
	
	@PostMapping("controller/addInventory")
	public String addInventory(String name, long quantity, Model model) {
		if(!name.equals("unknown")) {
			menuService.addInventory(name, quantity);
			return "redirect:/controller/admin";
		}
		model.addAttribute("msg", "메뉴를 선택해 주세요.");
		return "error/error";
	}
	
	@PostMapping("controller/deleteInventory")
	public String deleteInventory(String name, long quantity, Model model) {
		if(!name.equals("unknown")) {
			if(menuService.getMenuByName(name).get(0).getQuantity() - quantity >= 0 ) {
				menuService.deleteInventory(name, quantity);
				return "redirect:/controller/admin";
			}else {
				model.addAttribute("msg", "재고는 음수가 될 수 없습니다.");
				return "error/error";
			}
		}
		model.addAttribute("msg", "메뉴를 선택해 주세요.");
		return "error/error";
	}
	
	@GetMapping("controller/inventoryManagement")
	public String inventoryManagement(Model model) {
		model.addAttribute("cateList", categoryService.findAllCateName());
		model.addAttribute("menuList", menuService.getAllMenu());
		return "admin/inventoryManagement";
	}

}
