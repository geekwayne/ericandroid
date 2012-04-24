package com.ibookstore;

import com.ibookstore.data.Book;
import com.ibookstore.data.BookSearchCriteria;

import android.app.Application;

public class IBookStoreApplication extends Application {
	
	private BookSearchCriteria bookSearchCriteria;
	
	private Book currentBook;
	
	public BookSearchCriteria getBookSearchCriteria() {
		return bookSearchCriteria;
	}

	public void setBookSearchCriteria(BookSearchCriteria bookSearchCriteria) {
		this.bookSearchCriteria = bookSearchCriteria;
	}

	public Book getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(Book currentBook) {
		this.currentBook = currentBook;
	} 
	
	
}
