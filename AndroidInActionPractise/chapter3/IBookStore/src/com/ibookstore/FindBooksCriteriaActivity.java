package com.ibookstore;

import org.apache.commons.lang.StringUtils;

import com.ibookstore.util.UITools;

import android.app.Activity;
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
		validateCriteria();
	}
	
	//Validate from Criteria Fields
	private boolean validateCriteria(){
		boolean result = false;
		String tips =""; 
		if(txt_title.getText() == null || StringUtils.isBlank(txt_title.getText().toString())){
			tips = "不能为空";
		}else if (txt_author.getText() == null || StringUtils.isBlank(txt_author.getText().toString()) ){
			tips = "不能为空";
		}else if (txt_publisher.getText() == null || StringUtils.isBlank(txt_author.getText().toString()) ){
			tips = "不能为空";
	    }else{
	    	result = true;
	    }
		if(result!=true){
			UITools.openAlertDialogWithOKButton(this,"test",tips);
		}
		return result;
	}
}
