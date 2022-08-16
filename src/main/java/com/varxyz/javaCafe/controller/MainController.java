package com.varxyz.javaCafe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.javaCafe.dto.Category;
import com.varxyz.javaCafe.dto.Menu;
import com.varxyz.javaCafe.service.CategoryService;
import com.varxyz.javaCafe.service.MenuService;


@Controller("controller.mainController")
public class MainController {
	
	static int num=0;
	
	CategoryService categoryService = new CategoryService();
	MenuService menuService = new MenuService();
	
	@GetMapping("/controller/home")
	public String homeForm(HttpSession session) {
		session.invalidate(); //기존 세션값 모두 삭제
		num = 0;
		return "main/home";
	}
	
	@PostMapping("/controller/home")
	public String shopping() {
		return "redirect:/controller/main";
	}
	
	@GetMapping("/controller/main")
	public String buyForm(Model model, HttpSession session) {
		model.addAttribute("category", categoryService.findAllCateName());
		model.addAttribute("order",session.getAttribute("order"));
		return "main/main";
	}

	@PostMapping("/controller/main")
	public String buy(Category category, Model model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		model.addAttribute("menuList", menuService.getMenu(category.getName()));
		session.setAttribute("categoryName", category.getName());
		
		if(num != 0) {
			ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
			Menu menu = new Menu();
			menu.setCategoryName(category.getName());
			list.add(num, menu);
			session.setAttribute("order", list);
		}else {
			List<Menu> list = new ArrayList<Menu>();
			Menu menu = new Menu();
			menu.setCategoryName(category.getName());
			list.add(num, menu);
			session.setAttribute("order", list);
		}
		return "redirect:/controller/main2";
	}
	

	
	@GetMapping("/controller/main2")
	public String buy2Form(Model model, HttpSession session) {
		model.addAttribute("menuList", menuService.getMenu((String)session.getAttribute("categoryName")));
		ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
		model.addAttribute("order", list);
		return "main/main2";
	}
	
	@PostMapping("/controller/main2")
	public String buy2(Menu menu, Model model, HttpSession session) {
		ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
		list.get(num).setName(menu.getName());
		list.get(num).setPrice(menuService.getMenuByName(menu.getName()).get(0).getPrice());
		list.get(num).setPicture(menuService.getMenuByName(menu.getName()).get(0).getPicture());
		session.setAttribute("order", list);
		return "redirect:/controller/main3";
	}
	
	
	@GetMapping("/controller/main3")
	public String buy3Form(Model model, HttpSession session) {
		List<String> sizeList = new ArrayList<String>();
		String[] pictureList = {"tall","grande","venti"};
		long[] sizePrice = {500,1000,1500};
		sizeList.add("Tall");
		sizeList.add("Grande");
		sizeList.add("Venti");
		model.addAttribute("sizePrice", sizePrice);
		model.addAttribute("sizeList", sizeList);
		model.addAttribute("pictureList", pictureList);
		return "main/main3";
	}
	
	@PostMapping("/controller/main3")
	public String buy3(Menu menu, Model model, HttpSession session) {
		session.setAttribute("menuSize", menu.getSize());
		ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
		if(menu.getSize().equals("Tall")) {
			list.get(num).setSize(menu.getSize());
			list.get(num).setSizePrice(500);
		}else if(menu.getSize().equals("Grande")) {
			list.get(num).setSize(menu.getSize());
			list.get(num).setSizePrice(1000);
		}else {
			list.get(num).setSize(menu.getSize());
			list.get(num).setSizePrice(1500);
		}
		
		session.setAttribute("order", list);
		return "redirect:/controller/main4";
	}
	
	
	
	
	@GetMapping("/controller/main4")
	public String buy4Form(Model model, HttpSession session) {
		return "main/main4";
	}
	
	@PostMapping("/controller/main4")
	public String buy4(Menu menu, Model model, HttpSession session) {
		session.setAttribute("menuCount", menu.getCount());
		ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
		list.get(num).setCount(menu.getCount());
		session.setAttribute("order", list);
		return "redirect:/controller/main5";
	}
	
	
	
	
	
	@GetMapping("/controller/main5")
	public String buy5Form(Model model, HttpSession session) {
		List<String> takeList = new ArrayList<String>();
		String[] takePictureList = {"forHere","takeOut"};
		takeList.add("For Here");
		takeList.add("Take Out");
		model.addAttribute("takePictureList", takePictureList);
		model.addAttribute("takeList", takeList);
		return "main/main5";
	}
	
	@PostMapping("/controller/main5")
	public String buy5(Menu menu, Model model, HttpSession session) {
		session.setAttribute("menuTake", menu.getTake());
		ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
		list.get(num).setTake(menu.getTake());
		session.setAttribute("order", list);
		return "redirect:/controller/main6";
	}

	
	@GetMapping("/controller/main6")
	public String buy6Form(Model model, HttpSession session) {
		model.addAttribute("menuTake", session.getAttribute("menuTake"));
		return "main/main6";
	}
	
	@PostMapping("/controller/main6")
	public String buy6(Menu menu, Model model, HttpSession session) {
		session.setAttribute("menuTake", menu.getTake());
		num++;
		return "redirect:/controller/main";
	}
	
	@PostMapping("/controller/goPayment")
	public String goPayment(HttpSession session) {
		ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
		for (int i = 0; i < list.size(); i++) {
			menuService.deleteInventory(list.get(i).getName(), list.get(i).getCount());
		}
		return "redirect:/controller/payment";
	}
	
	
	@GetMapping("/controller/payment")
	public String paymentForm( Model model, HttpSession session) {
		ArrayList<Menu> list = (ArrayList)session.getAttribute("order");
		
		model.addAttribute("order", list);
		return "main/payment";
	}
	
	
}









