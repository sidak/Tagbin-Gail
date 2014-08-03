package com.sidak.tagbinGail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MaterialActivity extends Activity {
	private Button next;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_material);
		next=(Button)findViewById(R.id.next_label);
		next.setOnClickListener(new View.OnClickListener() {
		
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(MaterialActivity.this, CustomListView.class);
				startActivity(intent);
			}
		});
		
	}
	
}
