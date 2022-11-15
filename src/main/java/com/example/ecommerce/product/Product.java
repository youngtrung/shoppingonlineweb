package com.example.ecommerce.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String product;
	
	private Integer price;
	
	private String mainImagePath;
	
	

	public Product() {
	
	}



	public Product(String product, Integer price) {
	
		this.product = product;
		this.price = price;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getProduct() {
		return product;
	}



	public void setProduct(String product) {
		this.product = product;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public String getMainImagePath() {
		return mainImagePath;
	}



	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}
	
	
	
	
	
	
}
