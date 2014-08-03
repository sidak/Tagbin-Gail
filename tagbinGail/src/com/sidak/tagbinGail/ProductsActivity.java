package com.sidak.tagbinGail;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class ProductsActivity extends ListActivity {
	ArrayAdapter<ListModel> adapter;
	List<ListModel> list;
	int LIST_ITEMS;
	private Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		adapter = new CustomListAdapter(this, getModel());
		setListAdapter(adapter);
		LIST_ITEMS = 8;
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
