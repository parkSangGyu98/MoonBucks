package com.varxyz.javaCafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
	private String name;
	private long price;
	private long quantity;
	private String categoryName;
	private long count;
	private String size;
	private String take;
	private String picture;
	private long sizePrice;
}
