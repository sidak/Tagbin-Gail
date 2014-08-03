package com.sidak.tagbinGail;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

public class ProductsActivity extends ListActivity {
	ArrayAdapter<ListModel> adapter;
	List<ListModel> list;
	int LIST_ITEMS;
	private Button submit;
	private CheckBox pro1;
	private CheckBox pro2;
	private CheckBox pro3;
	private CheckBox pro4;
	private CheckBox pro5;
	private CheckBox pro6;
	private CheckBox pro7;
	private CheckBox pro8;

	private MyApplication myApp;
	private CheckBox[] cBoxes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		adapter = new CustomListAdapter(this, getModel());
		setListAdapter(adapter);
		LIST_ITEMS = 8;
		myApp = MyApplication.getInstance();
		//pro1 = (CheckBox) findViewById(R.id.);
		pro2 = (CheckBox) findViewById(R.id.mat2);
		pro3 = (CheckBox) findViewById(R.id.mat3);
		pro4 = (CheckBox) findViewById(R.id.mat4);
		pro5 = (CheckBox) findViewById(R.id.mat5);
		pro6 = (CheckBox) findViewById(R.id.mat_others);
		cBoxes = new CheckBox[LIST_ITEMS];
		cBoxes[0] = pro1;
		cBoxes[1] = pro2;
		cBoxes[2] = pro3;
		cBoxes[3] = pro4;
		cBoxes[4] = pro5;
		cBoxes[5] = pro6;
		cBoxes[6] = pro7;
		cBoxes[7] = pro8;
		submit=(Button)findViewById(R.id.submit_list);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(ProductsActivity.this, ConsumptionActivity.class);
				startActivity(intent);
			}
		});
	}

	private List<ListModel> getModel() {
		list = new ArrayList<ListModel>();
		for (int i = 1; i <= LIST_ITEMS; i++) {
			String name = "sector" + i;
			String image = "p" + i;
			list.add(new ListModel(name, image));
		}

		return list;
	}
}
