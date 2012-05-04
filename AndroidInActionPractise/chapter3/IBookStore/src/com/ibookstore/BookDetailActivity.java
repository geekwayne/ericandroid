package com.ibookstore;

import com.ibookstore.data.Book;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
 

public class BookDetailActivity extends Activity {

	 
	private TextView lbl_name;
	private TextView lbl_author;
	private TextView lbl_category;
	private TextView lbl_publisher;
	private TextView lbl_publishDate;
	private TextView lbl_price;
	private TextView lbl_rate;
	private Book book; 

	private static final int  MENU_WEB_REVIEW = Menu.FIRST;
	private static final int  MENU_MAP_REVIEW = Menu.FIRST+1;
	private static final int  MENU_CALL_REVIEW = Menu.FIRST+2;
	
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
		book = app.getCurrentBook();
		
		if(book!=null){
			this.lbl_name.setText(book.getName());
			this.lbl_author.setText(book.getAuthor());
			this.lbl_category.setText(book.getCategory());
			this.lbl_publisher.setText(book.getPublisher());
			this.lbl_publishDate.setText(book.getPublishDate());
			this.lbl_price.setText(String.valueOf(book.getPrice()));
			this.lbl_rate.setText(String.valueOf(book.getRate())); 
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0,BookDetailActivity.MENU_WEB_REVIEW, 0,"完整信息").setIcon(android.R.drawable.ic_menu_info_details); 
		menu.add(1,BookDetailActivity.MENU_MAP_REVIEW, 1,"地理位置").setIcon(android.R.drawable.ic_menu_mapmode);
		menu.add(2,BookDetailActivity.MENU_CALL_REVIEW, 2,"拨打电话").setIcon(android.R.drawable.ic_menu_call);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
		case MENU_WEB_REVIEW:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getPublisher()));
			startActivity(intent);
			return true;
		case MENU_MAP_REVIEW:
			intent = new Intent(intent.ACTION_VIEW,Uri.parse("geo:0,0?q=北京市"));
			startActivity(intent);
			return true;
		case MENU_CALL_REVIEW:
			intent = new Intent(intent.ACTION_CALL,Uri.parse("tel:18600891730"));
			startActivity(intent);
			return true; 
		}
		
		return super.onMenuItemSelected(featureId, item);
	}
	
	
	
}