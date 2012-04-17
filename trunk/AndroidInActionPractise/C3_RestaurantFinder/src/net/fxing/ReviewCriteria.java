package net.fxing;
 
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ReviewCriteria extends Activity {
	
	private static final int MENU_GET_REVIEWS = Menu.FIRST;
	
    private EditText editText_location;
    
    private Spinner spinner_cuisine;
    
    private Button button_grabViews;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_criteria);
        
        this.editText_location = (EditText)findViewById(R.id.location);
        this.spinner_cuisine = (Spinner) findViewById(R.id.cuisine);
        this.button_grabViews = (Button) findViewById(R.id.get_reviews_button);
        
        ArrayAdapter<String> cuisines = new ArrayAdapter<String>(this,R.layout.spinner_view,getResources().getStringArray(R.array.cuisines));
        cuisines.setDropDownViewResource(R.layout.spinner_view_dropdown);
        this.spinner_cuisine.setAdapter(cuisines);
        
        this.button_grabViews.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				 handleGetReviews();
			}
        	
        });
    }

	protected void handleGetReviews() {
		new AlertDialog.Builder(this).setTitle("test").setMessage("test")
		 .setPositiveButton("Continue", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				  //
			}
		}).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ReviewCriteria.MENU_GET_REVIEWS, 0,R.string.getReviews)
		.setIcon(R.drawable.ic_launcher); 
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()){
			case MENU_GET_REVIEWS:
				handleGetReviews();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
    
    
}