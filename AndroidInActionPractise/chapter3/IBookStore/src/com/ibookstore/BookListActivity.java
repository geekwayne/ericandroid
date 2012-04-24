package com.ibookstore;

import java.util.List;

import com.ibookstore.adapter.BookAdapter;
import com.ibookstore.data.Book;
import com.ibookstore.data.BookSearchCriteria;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

public class BookListActivity extends ListActivity {
	
	private TextView label_empty;
	private ProgressDialog progressDialog;
	private List<Book> books;
	
	private final Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			BookAdapter adapter = new BookAdapter(BookListActivity.this,books);
			setListAdapter(adapter);
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.booklist_activity);
		label_empty =  (TextView)findViewById(R.id.label_empty);
		
		 // set list properties
        final ListView listView = getListView();
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setEmptyView(this.label_empty);
        
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		//Get the SearchCriteria
		IBookStoreApplication app = (IBookStoreApplication) getApplication();
		BookSearchCriteria c = app.getBookSearchCriteria();
		
		loadBooks();
	}
	
	private void loadBooks() {

        this.progressDialog = ProgressDialog.show(this, " ËÑË÷ÖÐ...", " ÕýÔÚËÑË÷..ÇëÉÔºò..", true, false);

			new Thread(){
				@Override
				public void run(){
					books = Book.getTestData();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally{
						handler.sendEmptyMessage(0);
					}
					
				}
			}.start();
	}
	
}
