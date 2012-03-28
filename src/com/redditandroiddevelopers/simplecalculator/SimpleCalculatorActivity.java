package com.redditandroiddevelopers.simplecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SimpleCalculatorActivity extends Activity implements
    	OnClickListener {

	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bPlus, bMinus, bTimes,
			bDivide, bEquals, bDel;
	EditText etResult;
	//used in equals function
	float firstArg, currentArg;

	boolean clearScreen = false; // set true when screen needs to be cleared on
									// next button press
	// 0 = noop
	// 1 = plus
	// 2 = minus
	// 3 = times
	// 4 = divide
	// 5 = integral (To be implemented :D )
	int operation;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Bind buttons from main.xml
		init();
	}

	private void init() {
		// monotonous tasks from constructor
		b1 = (Button) findViewById(R.id.btn_1);
		b2 = (Button) findViewById(R.id.btn_2);
		b3 = (Button) findViewById(R.id.btn_3);
		b4 = (Button) findViewById(R.id.btn_4);
		b5 = (Button) findViewById(R.id.btn_5);
		b6 = (Button) findViewById(R.id.btn_6);
		b7 = (Button) findViewById(R.id.btn_7);
		b8 = (Button) findViewById(R.id.btn_8);
		b9 = (Button) findViewById(R.id.btn_9);
		b0 = (Button) findViewById(R.id.btn_0);
		bPlus = (Button) findViewById(R.id.btn_add);
		bMinus = (Button) findViewById(R.id.btn_subtract);
		bTimes = (Button) findViewById(R.id.btn_multiply);
		bDivide = (Button) findViewById(R.id.btn_divide);
		bEquals = (Button) findViewById(R.id.btn_equals);
		bDel = (Button) findViewById(R.id.btn_delete);
		etResult = (EditText) findViewById(R.id.editText_results);
		etResult.setText("");
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		b0.setOnClickListener(this);
		bPlus.setOnClickListener(this);
		bMinus.setOnClickListener(this);
		bDivide.setOnClickListener(this);
		bTimes.setOnClickListener(this);
		bEquals.setOnClickListener(this);
		bDel.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// clear screen if necessary
		if (clearScreen) {
			etResult.setText("");
			clearScreen = false;
		}

		// determine which button was pressed
		switch (arg0.getId()) {
		case R.id.btn_0:
			numPressed(0);
			break;
		case R.id.btn_1:
			numPressed(1);
			break;
		case R.id.btn_2:
			numPressed(2);
			break;
		case R.id.btn_3:
			numPressed(3);
			break;
		case R.id.btn_4:
			numPressed(4);
			break;
		case R.id.btn_5:
			numPressed(5);
			break;
		case R.id.btn_6:
			numPressed(6);
			break;
		case R.id.btn_7:
			numPressed(7);
			break;
		case R.id.btn_8:
			numPressed(8);
			break;
		case R.id.btn_9:
			numPressed(9);
			break;
		case R.id.btn_add:
			bPlusPressed();
			break;
		case R.id.btn_subtract:
			bMinusPressed();
			break;
		case R.id.btn_divide:
			bDividePressed();
			break;
		case R.id.btn_multiply:
			bTimesPressed();
			break;
		case R.id.btn_equals:
			bEqualsPressed();
			break;
		case R.id.btn_delete:
			bDelPressed();
			break;
		default:
			etResult.setText("Unknown Button pressed");
			break;
		}

	}

	private void bDelPressed() {
		// Delete the last character off the result
		String temp = etResult.getText().toString();
		if (temp.length() > 0) {
			temp = temp.substring(0, temp.length() - 1);
			etResult.setText(temp);
		}

	}

	private void bEqualsPressed() {
		// remove any non numerical characters
		String temp = etResult.getText().toString();
		temp = temp.replaceAll("[^\\d]", "");
		currentArg = Integer.parseInt(temp);

		// perform operation
		float result;
		switch (operation) {
		case 1: // plus
			result = firstArg + currentArg;
			break;
		case 2: // minus
			result = firstArg - currentArg;
			break;
		case 3: // times
			result = firstArg * currentArg;
			break;
		case 4: // divide
			result = firstArg / currentArg;
			break;
		default:
			// crash app with ArithmeticException
			// TODO: implement properly
			result = 1 / 0;
		}
		etResult.setText("=" + result);
		clearScreen = true;

	}

	private void bTimesPressed() {
		operation = 3;
		firstArg = Float.parseFloat((etResult.getText().toString()));
		etResult.setText("x");
	}

	private void bDividePressed() {
		operation = 4;
		firstArg = Float.parseFloat((etResult.getText().toString()));
		etResult.setText("\u00F7");

	}

	private void bMinusPressed() {
		operation = 2;
		firstArg = Float.parseFloat((etResult.getText().toString()));
		etResult.setText("-");

	}

	private void bPlusPressed() {
		operation = 1;
		firstArg = Float.parseFloat((etResult.getText().toString()));
		etResult.setText("+");

	}

	private void numPressed(int i) {
		// add the number pressed onto what is already in the calc
		etResult.setText(etResult.getText() + String.valueOf(i));
	}
}