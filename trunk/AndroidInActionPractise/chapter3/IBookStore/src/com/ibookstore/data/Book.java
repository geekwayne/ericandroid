package com.ibookstore.data;

import java.util.ArrayList;
import java.util.List;

public class Book {
	
	private String name;
	private String author;
	private String publisher;
	private String category;
	private String bookVersion;
	private String publishDate;
	private int rate;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBookVersion() {
		return bookVersion;
	}
	public void setBookVersion(String bookVersion) {
		this.bookVersion = bookVersion;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public static List<Book> getTestData(){
		List<Book> list = new ArrayList<Book>();
		
		for (int i = 1; i <= 50; i++) {
			Book book = new Book();
			book.setName("BookName"+i);
			book.setAuthor("BookAuthor"+i);
			book.setPublisher("Publisher"+i);
			book.setCategory("Category"+i);
			book.setBookVersion("bookVersion"+i);
			book.setPublishDate("1990-12-09");
			book.setRate(5);
			book.setPrice(25.5);
			list.add(book);
		}
		
		return list;
	}
}
