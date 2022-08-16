# MoonBucks
+ 온라인 카페 키오스크형식의 개인프로젝트입니다.
 + 2022.8.1 ~ 2022.8.6 (6일)
## 사용한 기술 및 환경
+ Window
+ eclipse
+ Java
+ Spring
+ Jsp
+ MySQL
+ HTML/CSS3
+ Javascript
+ bootstrap

## 구현 기능
+ 관리자
  + 카테고리 추가, 삭제
  + 메뉴 CRUD
  + 카테고리, 메뉴 사진 등록 및 수정
  + 재고 업데이트
  + 카테고리별 메뉴 조회
+ 사용자
  + 카테고리, 메뉴, 사이즈, 수량, Take out 여부 순으로 주문 선택
  + 개별 주문, 추가 주문, 주문 초기화
  + 최종 주문내역 조회
+ 장바구니
  + 실시간 주문 내역 수집
  + 추가주문 시 기존 주문내역 포함한 내역 수집
  
 ## 주요 코드
  ### 관리자
   + 카테고리 추가
  	 1. 가지고 온 파일의 이름을 고유번호 받아서 DB에 저장하고 지정한 경로에 파일을 복사가 됩니다.
  	 2. 저장되있는 고유번호를 가지고와 이미지를 띄워줍니다.
	 
	 		AddCategoryController 일부
			
			// 카테고리 추가 시 이미지 업로드
			@PostMapping("/controller/add_category")
			public String addCategory(@RequestParam("file") MultipartFile file,
									Category category, Model model) {
			String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메소드
			long size = file.getSize();
			if(!category.getName().equals("")) {
				if(fileRealName != "") {
			System.out.println("파일명 : " + fileRealName);
			System.out.println("용량크기(byte) : " + size);
			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
			String uploadFolder = "C:\\Users\\psg\\NCS\\backend\\javaCafe\\src\\main\\webapp\\resources\\img\\category";

			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");

			String uniqueName = uuids[0];
			System.out.println("생성된 고유문자열 : " + uniqueName);
			System.out.println("확장자명 : " + fileExtension);

			File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
			try {
				file.transferTo(saveFile); // 실제 파일 저장메서드
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
				categoryService.addCategory(category.getName(), uniqueName);
				model.addAttribute("category", category);
					
				return "redirect:/controller/admin";
			}else {
				model.addAttribute("msg", "올바른 사진을 선택해 주세요.");
				return "error/error";
			}
			}else {
				model.addAttribute("msg","카테고리명을 입력해 주세요.");
				return "error/error";
			}

+ 메뉴 수정
   1. 수정 전 메뉴명, 수정 후 메뉴명, 수정 할 가격을 받아와 유효성 검사
   2. 수정 전 메뉴명이 "unknown"이 아니면서 수정 후의 메뉴명이 공백이 아닐 시 DB정보 수정
   3. 가격부분의 유효성검사는 jsp에 input박스 옵션을 이용하였습니다.
   
  			AdminController 일부
			
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
			
### 사용자		
+  최종 주문내역 조회
   1. session을 이용해 장바구니 내역을 리스트에 축적시키고 model로 jsp에 넘겨줍니다.
   2. 넘겨받은 리스트를 forEach문을 이용해 사진 및 모든 주문 내역을 가져옵니다.
   			
			   payment.jsp 일부
				
			   <c:forEach var="item" items="${order}" varStatus="status">
				<c:if test="${order != null}">
					<h3 style="margin-top: 80px;">${status.index+1}</h3>
						<img style="height: 130px;"
							src="../resources/img/menu/${item.picture}.jpg">
							<li style="height: 50px; line-height: 100px;">카테고리명 :
								${item.categoryName}</li>
							<li style="height: 50px; line-height: 100px;">제품명 :
								${item.name}</li>
							<li style="height: 50px; line-height: 100px;">사이즈 :
								${item.size}</li>
							<li style="height: 50px; line-height: 100px;">수량 :
								${item.count}</li>
							<li style="height: 50px; line-height: 100px;">금액 :
								${(item.price + item.sizePrice)* item.count}</li>
							<li style="height: 50px; line-height: 100px;">${item.take}</li>
					</c:if>
				</c:forEach>

	3. 성공화면
	
	![image](https://user-images.githubusercontent.com/103983349/184813020-b9c36740-1a56-4e25-b639-ee8a4cf4eae5.png)
	![image](https://user-images.githubusercontent.com/103983349/184813051-9179d7f3-aafa-4d05-9927-431d7cece98b.png)


### 장바구니
+  추가주문 시 기존 주문내역 포함한 내역 수집
   1. 현재 카테고리를 선택하는 화면이 첫 주문인지 추가 주문인지를 확인하기 위한 전역변수 num 을 선언합니다.
   2. 첫 주문이라면, 주문내역을 담을 "order"라는 세션을 생성해줍니다.
   3. 추가 주문이라면, "order" 세션을 불러와 리스트를 선언해준 뒤 그 리스트에 추가 주문 내역을 넣어줍니다.
   					
				   MainController 일부
				   
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

	4. 추가 주문 클릭시 num의 카운트가 올라가고 몇번째 주문인지 확인할 수 있습니다.
	
					MainController 일부
					
					@PostMapping("/controller/main6")
					public String buy6(Menu menu, Model model, HttpSession session) {
						session.setAttribute("menuTake", menu.getTake());
						num++;
						return "redirect:/controller/main";
					}
	
	6. 주문선택이 끝나면 최종 주문 내역인 "order"세션을 리스트형태로 jsp에 넘겨주고 최종 주문내역을 보여줍니다.
	7. 동시에, for문을 이용해 주문 내역의 수량을 가져와 재고에서 차감시킵니다.

					MainController 일부
					
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

	

## 구현 화면
  ### 클릭 시 주문 시작
  
  ![image](https://user-images.githubusercontent.com/103983349/184801519-c66bbb20-d026-4e54-a69a-274c91e31645.png)
  
  ### 카테고리 선택
  
  ![image](https://user-images.githubusercontent.com/103983349/184802269-08125871-bc71-412c-94e8-2874085e800f.png)

  ### 메뉴 선택
  
  ![image](https://user-images.githubusercontent.com/103983349/184802364-727b5bf9-9c0e-4295-8e4f-d201a47de58a.png)
  
  ### 사이즈 선택
  
  ![image](https://user-images.githubusercontent.com/103983349/184802401-2c237d6f-3daa-4780-a6ec-14b3a8b913da.png)

  ### 수량 선택
  
  ![image](https://user-images.githubusercontent.com/103983349/184803353-0e4c5515-9870-403b-a274-ebe926792ee4.png)
  
  ### Take out 여부
  
  ![image](https://user-images.githubusercontent.com/103983349/184803384-3b76f514-88db-4700-b78a-c7abb4bb5c3c.png)

  ### 주문 선택 끝난 뒤
  
  ![image](https://user-images.githubusercontent.com/103983349/184803434-655a8d56-f1aa-4989-acd6-b04c3617de22.png)
  
  ### 더 고르기
  + 기존에 주문했던 내역을 유지한 채 카테고리 선택화면으로 갑니다.
  
  ![image](https://user-images.githubusercontent.com/103983349/184803585-4dd265c5-269a-42ae-97cc-348ccf9a7e3c.png)

  ### 주문하기
  
  ![image](https://user-images.githubusercontent.com/103983349/184803644-052a3834-d3ae-4b29-86c5-2df2cc1de3da.png)

  ### 관리자 페이지 - 카테고리 (추가, 사진 수정, 삭제)
  
  ![image](https://user-images.githubusercontent.com/103983349/184803892-c8d2007f-6bad-4c01-a602-c33a986833d6.png)

  ### 관리자 페이지 - 메뉴 (카테고리별 메뉴 조회, 추가, 수정, 삭제)
  
  ![image](https://user-images.githubusercontent.com/103983349/184804045-919f45ef-6be2-478d-9e28-0264cc554a37.png)
  ![image](https://user-images.githubusercontent.com/103983349/184804058-a0a0d7f9-f7a4-4bab-b4bc-42405e1dcc96.png)
  
  ### 관리자 페이지 - 재고 (추가, 차감)
  
  ![image](https://user-images.githubusercontent.com/103983349/184804140-4fa39ba3-4f99-4635-989a-aaff3761b253.png)
  ![image](https://user-images.githubusercontent.com/103983349/184804932-69d4d4b7-be1c-46c6-baa8-7d41e9e43e2b.png)

 
  #### 참고 문헌
  + 프로젝트에 사용된 카테고리 및 메뉴 등 이미지 -> https://www.starbucks.co.kr/index.do
  + 이미지업로드 오픈소스 참고 블로그 -> https://devofroad.tistory.com/m/45
  
