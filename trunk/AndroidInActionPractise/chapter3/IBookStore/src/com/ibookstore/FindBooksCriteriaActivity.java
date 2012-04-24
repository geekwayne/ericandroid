package com.ibookstore;

import org.apache.commons.lang.StringUtils;

import com.ibookstore.data.BookSearchCriteria;
import com.ibookstore.util.UITools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FindBooksCriteriaActivity extends Activity {

	private EditText txt_title;
	private EditText txt_author;
	private EditText txt_publisher;
	private Spinner spinner_book_category;
	private Button btn_findBooks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findbooks_criteria_activity);
		
		txt_title = (EditText)findViewById(R.id.txt_title);
		txt_author = (EditText)findViewById(R.id.txt_author);
		txt_publisher  = (EditText) findViewById(R.id.txt_publisher);
		spinner_book_category = (Spinner)findViewById(R.id.spinner_book_category);
		btn_findBooks = (Button)findViewById(R.id.btn_findBooks);
		
		ArrayAdapter<String> adapter_bookCategories = new ArrayAdapter<String>(this,R.layout.spinner_view,getResources().getStringArray(R.array.bookCategories));
		adapter_bookCategories.setDropDownViewResource(R.layout.spinner_view_dropdown);
		
		this.spinner_book_category.setAdapter(adapter_bookCategories);
		
		this.btn_findBooks.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View paramView) {
				handleFindBooks();
			}
			
		});
		
	}
	
	protected void handleFindBooks() {
		//validate the user input
		if(!validateCriteria()) return;
		
		//Get the Criteria
		BookSearchCriteria c = new BookSearchCriteria();
		c.setTitle(this.txt_title.getText().toString());
		c.setAuthor(this.txt_author.getText().toString());
		c.setCategory(this.spinner_book_category.getSelectedItem().toString());
		c.setPublisher(this.txt_publisher.getText().toString());
		
		//Put Criteria to Application 
		IBookStoreApplication app = (IBookStoreApplication) getApplication();
		app.setBookSearchCriteria(c);
		
		//call list Intent 
		Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_BOOKLIST);
		startActivity(intent);
		
	}
	 
	private boolean validateCriteria(){
		boolean result = false;
		String tips =""; 
		if(txt_title.getText() == null || StringUtils.isBlank(txt_title.getText().toString())){
			tips = "请您输入\" "+getResources().getString(R.string.book_titile)+" \"，字段不能为空";
		}else{
	    	result = true;
	    }
		if(result!=true){
			UITools.openAlertDialogWithOKButton(this,"test",tips);
		}
		return result;
	}
}
