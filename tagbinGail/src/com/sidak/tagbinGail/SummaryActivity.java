package com.sidak.tagbinGail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	private MyApplication myApp;
	private static final String TAG = SummaryActivity.class.getSimpleName();

	private String name;

	private String companyName;
	private String state;
	private String email;
	private String phoneNo;
	private String industry;
	private ArrayList<String> materials;
	private ArrayList<String> products;
	private String consumption;

	private String material_str;
	private String products_str;

	private String[] val;
	// do materials and products separately
	private String[] headers;

	private TextView nameLabel;
	private TextView companyNameLabel;
	private TextView stateLabel;
	private TextView emailLabel;
	private TextView phoneNoLabel;
	private TextView industryLabel;
	private TextView materialsLabel;
	private TextView productsLabel;
	private TextView consumptionLabel;

	private static final int NUM_TV = 10;
	private String imageUrl;
	private ImageView imView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApp = MyApplication.getInstance();
		if (myApp.getImageUrl() != null) {
			setContentView(R.layout.activity_summary_image);
		} else {
			setContentView(R.layout.activity_summary);
		}

		String[] headers_copy = { getString(R.string.sum_name),
				getString(R.string.sum_mail), getString(R.string.sum_phone),
				getString(R.string.sum_state),
				getString(R.string.sum_company_name),
				getString(R.string.sum_relation),
				getString(R.string.sum_materials),
				getString(R.string.sum_products),
				getString(R.string.sum_consumption),
				getString(R.string.sum_imageUrl) };

		headers = headers_copy;

		nameLabel = (TextView) findViewById(R.id.name_label);
		companyNameLabel = (TextView) findViewById(R.id.company_label);
		stateLabel = (TextView) findViewById(R.id.state_label);
		emailLabel = (TextView) findViewById(R.id.email_label);
		phoneNoLabel = (TextView) findViewById(R.id.phone_label);
		industryLabel = (TextView) findViewById(R.id.relation_label);
		materialsLabel = (TextView) findViewById(R.id.materials_label);
		productsLabel = (TextView) findViewById(R.id.products_label);
		consumptionLabel = (TextView) findViewById(R.id.consumption_label);
		imView = (ImageView) findViewById(R.id.summary_imageview);

		products_str = "";
		material_str = "";

		File root = Environment.getExternalStorageDirectory();
		File xlFile = new File(root, "gail.xls");
		fetchDataFromApp();
		String[] val_copy = { name, email, phoneNo, state, companyName,
				industry, consumption };
		val = val_copy;

		new saveAndLoadData().execute(xlFile);

		nameLabel.setText(headers[0] + " " + name);
		companyNameLabel.setText(headers[4] + " " + companyName);
		stateLabel.setText(headers[3] + " " + state);
		emailLabel.setText(headers[1] + " " + email);
		phoneNoLabel.setText(headers[2] + " " + phoneNo);
		industryLabel.setText(headers[5] + " " + industry);
		consumptionLabel.setText(headers[8] + " " + consumption);
		//materialsLabel.setText(headers[6] + " " + material_str);
		//productsLabel.setText(headers[7] + " " + products_str);

	}

	private class saveAndLoadData extends AsyncTask<File, Void, Void> {
		private WritableCellFormat timesBoldUnderline;
		private WritableCellFormat times;
		private ProgressDialog dialog = new ProgressDialog(SummaryActivity.this);

		Bitmap mBitmap;

		protected void onPreExecute() {
			/****** NOTE: You can call UI Element here. *****/

			// Progress dialog
			dialog.setMessage(" Saving data in sd card ... ");
			dialog.show();
		}

		@Override
		protected Void doInBackground(File... params) {
			// TODO Auto-generated method stub

			boolean flag = false;
			Bitmap bitmap = null;
			Bitmap newBitmap = null;
			Uri uri = null;

			if (imageUrl != null) {
				uri = Uri.parse(imageUrl);
				flag = true;
			}
			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("en", "EN"));

			WritableWorkbook workbook = null;

			try {
				workbook = Workbook.createWorkbook(params[0], wbSettings);
				workbook.createSheet("Report", 0);
				WritableSheet excelSheet = workbook.getSheet(0);
				createLabel(excelSheet);
				workbook.write();
				workbook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (flag) {
				try {

					/************** Decode an input stream into a bitmap. *********/
					bitmap = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(uri));

					if (bitmap != null) {

						/*********
						 * Creates a new bitmap, scaled from an existing bitmap.
						 ***********/

						newBitmap = Bitmap.createScaledBitmap(bitmap, 250, 250,
								true);

						bitmap.recycle();

						if (newBitmap != null) {

							mBitmap = newBitmap;
						}
					}
				} catch (IOException e) {
					// Error fetching image, try to recover

					/********* Cancel execution of this task. **********/
					cancel(true);
				}
			}

			return null;

		}

		protected void onPostExecute(Void unused) {

			// NOTE: You can call UI Element here.

			// Close progress dialog
			dialog.dismiss();

			if (mBitmap != null) {
				// Set Image to ImageView
				
				imView.setImageBitmap(mBitmap);
			}
			materialsLabel.setText(headers[6] + " " + material_str);
			productsLabel.setText(headers[7] + " " + products_str);

		}

		private void createLabel(WritableSheet sheet) throws WriteException {
			// Lets create a times font
			WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
			// Define the cell format
			times = new WritableCellFormat(times10pt);
			// Lets automatically wrap the cells
			times.setWrap(true);

			// create create a bold font with underlines
			WritableFont times10ptBoldUnderline = new WritableFont(
					WritableFont.TIMES, 10, WritableFont.BOLD, false,
					UnderlineStyle.SINGLE);
			timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
			// Lets automatically wrap the cells
			timesBoldUnderline.setWrap(true);

			CellView cv = new CellView();
			cv.setFormat(times);
			cv.setFormat(timesBoldUnderline);
			cv.setAutosize(true);

			for (int i = 0; i < NUM_TV; i++) {
				addCaption(sheet, i, 0, headers[i]);
			}
			for (int i = 0; i < 6; i++) {
				addLabel(sheet, i, 1, val[i]);
			}

			for (int i = 0; i < materials.size() - 1; i++) {
				material_str += materials.get(i) + " , ";
			}
			if (materials.size() - 1 >= 0) {
				material_str += materials.get(materials.size() - 1);
			}
			addLabel(sheet, 6, 1, material_str);
			Log.d(TAG, "material " + material_str);
			for (int i = 0; i < products.size() - 1; i++) {
				products_str += products.get(i) + " , ";
			}
			if (products.size() - 1 >= 0) {
				products_str += products.get(products.size() - 1);
			}
			addLabel(sheet, 7, 1, products_str);
			addLabel(sheet, 8, 1, val[6]);
			addLabel(sheet, 9, 1, imageUrl);

		}

		private void addCaption(WritableSheet sheet, int column, int row,
				String s) throws RowsExceededException, WriteException {
			Label label;
			label = new Label(column, row, s, timesBoldUnderline);
			sheet.addCell(label);
		}

		private void addLabel(WritableSheet sheet, int column, int row, String s)
				throws WriteException, RowsExceededException {
			Label label;
			label = new Label(column, row, s, times);
			sheet.addCell(label);
		}

	}

	public void fetchDataFromApp() {
		name = myApp.getName();
		companyName = myApp.getCompanyName();
		email = myApp.getEmail();
		state = myApp.getState();
		phoneNo = myApp.getPhoneNo();
		industry = myApp.getIndustry();
		consumption = myApp.getConsumption();
		imageUrl = myApp.getImageUrl();
		materials = myApp.getMaterials();
		products = myApp.getProducts();
	}

	@SuppressLint("InlinedApi")
	public void onBackPressed() {
		Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
		// intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
