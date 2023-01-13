package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Book
{
	@Id
	private int bid;
	private String bookName;
	private double bookPrice;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
