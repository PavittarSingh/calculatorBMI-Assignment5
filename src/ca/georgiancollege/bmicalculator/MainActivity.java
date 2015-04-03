/**
 * @Name : Pavitar Singh
 * @Version: March 03, 2015
 * @Description: THis App calculates User Bmi it can be in Imerial and MEtric Units and it also allows to reset the fields
 */
package ca.georgiancollege.bmicalculator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

//Initialise the Variables
	private EditText _weightText;
	private EditText _heightText;
	private TextView _resultText;
	private Button _calculateButton;
	private float _userHeight;
	private float _userWeight;
	private RadioGroup _radioGroup;
	private RadioButton _button;
	private String _check;
	private float _bmi;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this._weightText = (EditText) findViewById(R.id.weightText);
        this._heightText = (EditText) findViewById(R.id.heightText);
        this._resultText = (TextView) findViewById(R.id.resultView);
        this._calculateButton = (Button) findViewById(R.id.calculateButton);
        this._radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
	}

	//Click event on the Calculate Button
	
	public void CalculateOnClick(View view) {
	     
		//events on the Radio button and Button
		int s = this._radioGroup.getCheckedRadioButtonId();
	    this._button = (RadioButton) findViewById(s);
	    this._check = (String) this._button.getText();
	    
	     if (view.getId() == R.id.calculateButton) {

	      // get the users values 

	      this._userWeight = Float.parseFloat(this._weightText.getText().toString());
	      this._userHeight = Float.parseFloat(this._heightText.getText().toString());
	 
	      // calling Calculate Bmi function 
	      float bmiValue = calculateBMI(this._userWeight, this._userHeight);
	 
	      // formatting the bmi result upto 2 decimal place
	      String bmiCheck = String.format("%.02f", bmiValue);
	    
	      // showing the meaning of the bmi value
	      String bodyType = bodyBMI(bmiValue);
	 
	      // set the color of the text
	      this._resultText.setTextColor(Color.GREEN);
     
	   //  set the value in the result text
	      this._resultText.setText(bmiValue + "-" + bodyType);
	    
	     }
	   }
	
	//Reset Button is Clicked
	public void resetOnClick(View view) {
		
		//Blank all the Fields
		this._radioGroup.clearCheck();
		this._resultText.setText("");
		this._weightText.setText("");
		this._heightText.setText("");
	}
	
	//Funtion to Calculate Bmi Of the User
	private float calculateBMI (float weight, float height) {

    	//If imperial is Selected 
    	if(this._check.equals("Imperial")){
    		this._bmi = ((this._userWeight * 703) / (this._userHeight * this._userHeight));
    	}
    	//if MEtric units are Selected
    	else if (this._check.equals("Metric")){
    		
    		this._bmi = (this._userWeight / (this._userHeight * this._userHeight));
    	}
    	
    	//Return the USer Bmi Calculated
    	return this._bmi;
    
	    }

	 
	    // To check the Body Type of the USer
	    private String bodyBMI(float bmiValue) {

	     if (bmiValue < 16) {
	      return "Severely underweight";
	     } else if (bmiValue < 18.5) {

	      return "Underweight";
	     } else if (bmiValue < 25) {

	      return "Normal";
	     } else if (bmiValue < 30) {

	      return "Overweight";
	     } else {
	      return "Obese";
	     }

	    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
