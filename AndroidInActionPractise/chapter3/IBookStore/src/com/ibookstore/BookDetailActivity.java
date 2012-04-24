package com.ibookstore;

import com.ibookstore.data.Book;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
 

public class BookDetailActivity extends Activity {

	 
	private TextView lbl_name;
	private TextView lbl_author;
	private TextView lbl_category;
	private TextView lbl_publisher;
	private TextView lbl_publishDate;
	private TextView lbl_price;
	private TextView lbl_rate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookdetail_activity);
		this.lbl_name = (TextView)findViewById(R.id.lbl_name);
		this.lbl_author = (TextView)findViewById(R.id.lbl_author);
		this.lbl_category = (TextView)findViewById(R.id.lbl_category);
		this.lbl_publisher = (TextView)findViewById(R.id.lbl_publisher);
		this.lbl_publishDate = (TextView)findViewById(R.id.lbl_publishDate);
		this.lbl_price = (TextView)findViewById(R.id.lbl_price);
		this.lbl_rate = (TextView)findViewById(R.id.lbl_rate);
		
		IBookStoreApplication app  = (IBookStoreApplication) getApplication();
		Book b = app.getCurrentBook();
		
		if(b!=null){
			this.lbl_name.setText(b.getName());
			this.lbl_author.setText(b.getAuthor());
			this.lbl_category.setText(b.getCategory());
			this.lbl_publisher.setText(b.getPublisher());
			this.lbl_publishDate.setText(b.getPublishDate());
			this.lbl_price.setText(String.valueOf(b.getPrice()));
			this.lbl_rate.setText(String.valueOf(b.getRate())); 
		}
		
	}
	
	
}