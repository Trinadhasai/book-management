package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;
import com.example.demo.exceptions.BookAlreadyExistsException;

@Service
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepo;
	
	@Override
	public List<Book> getAllBooks() {
		List<Book> bookList = bookRepo.findAll();
		if(bookList != null && bookList.size() >0)
		{
			return bookList;
		}
		else
			return null;
	}

	/*
	 * @Override public Book addBook(Book book) { if(book !=null) { //return
	 * bookRepo.saveAndFlush(book); return bookRepo.save(book);
	 * 
	 * } else return null;
	 * 
	 * 
	 * }
	 * 
	 */
	
	@Override
	public boolean deleteBook(int bid) {
		
		bookRepo.deleteById(bid);
		return true;
	}

	@Override
	public boolean updateBook(Book book) 
	{
		Book book1 = bookRepo.getById(book.getBid());
		
		if(book1!=null)
		{
			book1.setBookPrice(book.getBookPrice());
			bookRepo.saveAndFlush(book1);
			
		}
		return true;
	}

	@Override
	public Book addBook(Book book) throws BookAlreadyExistsException {

       Optional <Book> optional = bookRepo.findById(book.getBid());
       
       if(optional.isPresent())
       {
    	   throw new BookAlreadyExistsException();
    	   
       }
       else
    	   return bookRepo.saveAndFlush(book);
		
	}
	
	

}
