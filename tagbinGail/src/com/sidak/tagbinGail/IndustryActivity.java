package com.sidak.tagbinGail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IndustryActivity extends Activity {
	private Button next;
//	private LinearLayout lLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_industry);
		next=(Button)findViewById(R.id.next_label);
		next.setOnClickListener(new View.OnClickListener() {
		
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(IndustryActivity.this, MaterialActivity.class);
				startActivity(intent);
			}
		});
	}
		/*
		lLayout=(LinearLayout)findViewById(R.id.industry_layout);
		int sdk = android.os.Build.VERSION.SDK_INT;
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		    lLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.pages1));
		} else {
			lLayout.setBackground(getResources().getDrawable(R.drawable.pages1));
		}/*
	}
	/*
	private   void  unbindDrawables( View  view) {
	     if  (view.getBackground() !=  null ) {
	        view.getBackground().setCallback( null );
	    }
	     if  (view  instanceof   ViewGroup ) {
	         for  ( int  i = 0; i < (( ViewGroup ) view).getChildCount(); i++) {
	            unbindDrawables((( ViewGroup ) view).getChildAt(i));
	        }
	        (( ViewGroup ) view).removeAllViews();
	    }
	}
	@Override
	protected   void  onPause() {
	     super .onPause();
	    unbindDrawables(findViewById(R.id.industry_layout));
	     System .gc();
	}*/
}
