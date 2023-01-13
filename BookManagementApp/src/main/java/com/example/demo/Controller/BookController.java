package com.example.demo.Controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.BookService;
import com.example.demo.Model.Book;
import com.example.demo.Response.ResponseHandler;
import com.example.demo.exceptions.BookAlreadyExistsException;

@RestController
@RequestMapping("api/v1")
public class BookController 
{
	@Autowired
	private BookService bookService;
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<?> getAllBooks()
	{
		List <Book> bookList = bookService.getAllBooks();
		if(bookList !=null)
		{
			/* This is the default way to returning response */
			//return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
			/* Custom way to returning response */
			//return ResponseHandler.generateResponse("Succesfully retrieved the data", HttpStatus.OK, bookList);
			/* Cache way of response */
			CacheControl cacheControl = CacheControl.maxAge(10, TimeUnit.MINUTES);
			return ResponseEntity.ok()
					.cacheControl(cacheControl)
					.body(ResponseHandler.generateResponse("Succesfully retrieved the data", HttpStatus.OK, bookList));
		}
		return new ResponseEntity<String>("The Book List is empty!", HttpStatus.NO_CONTENT);
	}

	@PostMapping(value="/addBook", consumes ="application/json; charset = utf-8")
	public ResponseEntity<?> addBook(@RequestBody Book book) throws BookAlreadyExistsException 
	{
		if(bookService.addBook(book)!=null)
				{
			return new ResponseEntity<Book>(book, HttpStatus.CREATED);
				}
		 //throw new BookAlreadyExistsException();
		
		return new ResponseEntity<String>("The Book could not be saved!", HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/delete/{bid}")
	public ResponseEntity<?> deleteBook(@PathVariable("bid") int bid)
	{
		if(bookService.deleteBook(bid))
		{
			return new ResponseEntity<String>("Book deleted!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Book could be deleted!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBook(@RequestBody Book book)
	{
		if(bookService.updateBook(book))
		{
			return new ResponseEntity<String>("Book Updated!", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Book could not be updated!", HttpStatus.CONFLICT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
