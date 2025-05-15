package com.ecom.ecom_proj.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Product {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private String name;
		private String desc;
		private String brand;
		private BigDecimal price;
		private String category;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yy")
		private Date releasedate;
		private boolean available;
		private int quantity;
		
		private String imageName;
		private String imageType;
		
		@Lob
		private byte[] imageData;
		
		
		public String getImageName() {
			return imageName;
		}
		public void setImageName(String imageName) {
			this.imageName = imageName;
		}
		public String getImageType() {
			return imageType;
		}
		public void setImageType(String imageType) {
			this.imageType = imageType;
		}
		public byte[] getImageData() {
			return imageData;
		}
		public void setImageData(byte[] imageData) {
			this.imageData = imageData;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public Date getReleasedate() {
			return releasedate;
		}
		public void setReleasedate(Date releasedate) {
			this.releasedate = releasedate;
		}
		public boolean isAvailable() {
			return available;
		}
		public void setAvailable(boolean available) {
			this.available = available;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public Product() {
			
		}
		public Product(int id, String name, String desc, String brand, BigDecimal price, String category,
				Date releasedate, boolean available, int quantity, String imageName, String imageType,
				byte[] imageData) {
			super();
			this.id = id;
			this.name = name;
			this.desc = desc;
			this.brand = brand;
			this.price = price;
			this.category = category;
			this.releasedate = releasedate;
			this.available = available;
			this.quantity = quantity;
			this.imageName = imageName;
			this.imageType = imageType;
			this.imageData = imageData;
		}
		
		
		
			
}
