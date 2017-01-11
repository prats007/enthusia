package com.almesh.enthusia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class GCMMessageReceivedActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
			
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_two)));  
        String currentpage="enthu_options";
	}
}
