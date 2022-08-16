# MoonBucks
 + 온라인 카페 키오스크형식의 개인프로젝트입니다.
 + 2022.8.1 ~ 2022.8.6
## 사용한 기술
+ Java
+ Spring flatform
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

  ## 구현 기능에 관한 일부 코드
  ### 관리자
  + 카테고리 추가, 삭제
 
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
  
  

  
