package com.ibookstore;

import com.ibookstore.util.UITools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IBookStoreActivity extends Activity {

	private Button btn_go_findBooksCriteria;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ibookstore_activity);
		btn_go_findBooksCriteria = (Button) findViewById(R.id.btn_go_findBooksCriteria);
		btn_go_findBooksCriteria.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_DETAIL);
				startActivity(intent);
			}
			
		});
	}
	
	
}