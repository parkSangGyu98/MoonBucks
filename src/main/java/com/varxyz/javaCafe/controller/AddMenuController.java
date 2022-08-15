package com.varxyz.javaCafe.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.javaCafe.dto.Menu;
import com.varxyz.javaCafe.service.CategoryService;
import com.varxyz.javaCafe.service.MenuService;

@Controller("controller.addMenuController")
public class AddMenuController {

	MenuService menuService = new MenuService();
	CategoryService categoryService = new CategoryService();

	@GetMapping("controller/add_menu")
	public String addMenuForm(Model model) {
		model.addAttribute("category", categoryService.findAllCateName());
		return "menu/add_menu";
	}

	@PostMapping("controller/add_menu")
	public String addMenu(@RequestParam("file") MultipartFile file, Menu menu, Model model) {

		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메소드
		long size = file.getSize();

		if (!menu.getName().equals("")) {
			if (fileRealName != "") {
				if (!menu.getCategoryName().equals("unknown")) {
					System.out.println("파일명 : " + fileRealName);
					System.out.println("용량크기(byte) : " + size);
					// 서버에 저장할 파일이름 fileextension으로 .jsp이런식의 확장자 명을 구함
					String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
					String uploadFolder = "C:\\Users\\psg\\NCS\\backend\\javaCafe\\src\\main\\webapp\\resources\\img\\menu";

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

					menu.setPicture(uniqueName);
					menuService.addMenu(menu, uniqueName);
					model.addAttribute("menu", menu);

					return "redirect:/controller/main";
				} else {
					model.addAttribute("msg", "추가할 메뉴의 카테고리를 선택해주세요.");
					return "error/error";
				}
			} else {
				model.addAttribute("msg", "올바른 사진을 선택해 주세요.");
				return "error/error";
			}
		} else {

			model.addAttribute("msg", "메뉴명을 입력해 주세요.");
			return "error/error";
		}
	}

}
