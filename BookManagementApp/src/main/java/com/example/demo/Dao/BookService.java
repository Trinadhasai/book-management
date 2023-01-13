package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.Book;
import com.example.demo.exceptions.BookAlreadyExistsException;

public interface BookService 
{
	public List<Book> getAllBooks();
	
	public Book addBook(Book book) throws BookAlreadyExistsException;
	
	public boolean deleteBook(int bid);
	
	public boolean updateBook(Book book);

}
