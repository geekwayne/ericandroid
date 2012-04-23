package com.ibookstore;

import com.ibookstore.data.BookSearchCriteria;

import android.app.Application;

public class IBookStoreApplication extends Application {
	
	private BookSearchCriteria bookSearchCriteria;

	public BookSearchCriteria getBookSearchCriteria() {
		return bookSearchCriteria;
	}

	public void setBookSearchCriteria(BookSearchCriteria bookSearchCriteria) {
		this.bookSearchCriteria = bookSearchCriteria;
	} 
	
	
}
