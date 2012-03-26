package net.fxing;

import java.text.NumberFormat;
import java.text.ParseException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class C2Activity extends Activity {
    
	  /** Called when the activity is first created. */
	
		private final String tag = "Tip Calculator";
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        final EditText editText_mealPrice = (EditText) findViewById(R.id.mealPrice);
	        final TextView textView_answer = (TextView) findViewById(R.id.answer);
	        final Button button_caculate = (Button)findViewById(R.id.calculateButton); 
	        
	        button_caculate.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) { 
					
					try{
						Log.i(tag, "On click invoked");
						//grab the meal price from the UI
						String mealPrice = editText_mealPrice.getText().toString();
						Log.i(tag,"meal price is [ "+mealPrice+" ]");
						String answer = "";
						// check to see if the meal price includes a $
						if(mealPrice.indexOf("$") == -1){
							mealPrice = "$"+mealPrice;
						}
						float fmp = 0.0F;
						//get currency formater
						NumberFormat nf = NumberFormat.getCurrencyInstance();
						fmp = nf.parse(mealPrice).floatValue();
						fmp *= 1.2;
						Log.i(tag,"Total meal price is [ "+fmp+" ] ");
						//format the result
						answer = "Full price , including 20% tip: "+nf.format(fmp);
						textView_answer.setText(answer);
						Log.i(tag,"onClick complete");
					}catch(ParseException e){
						Log.i(tag, "ParseException caught");
						textView_answer.setText("failed to parse amount");
					}catch(Exception ex){
						Log.e(tag,"Error to Calucate the Amount ");
					}
				}
				
			});
	    }
	    
}