package com.ibookstore;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class BookListActivity extends ListActivity {
	
	private TextView label_empty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.booklist_activity);
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.setContentView(R.layout.booklist_activity);
		label_empty =  (TextView)findViewById(R.id.label_empty);
		
		 // set list properties
        final ListView listView = getListView();
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setEmptyView(this.label_empty);
        
	}
	
	
}
