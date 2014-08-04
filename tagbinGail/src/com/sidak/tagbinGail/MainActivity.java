package com.sidak.tagbinGail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button next;
	private Button capture_pic;
	private EditText name;
	private EditText cName;
	private EditText email;
	private EditText phone;
	private EditText state;

	private String nameStr;
	private String cNameStr;
	private String emailStr;
	private String phoneStr;
	private String stateStr;
	private static final String TAG=MainActivity.class.getSimpleName();
	private MyApplication myApp;
	String mCurrentPhotoPath;
	static final int REQUEST_TAKE_PHOTO = 1;
	private Uri fileUri=null; // file url to store image/video
	public static final int MEDIA_TYPE_IMAGE = 1;

	// directory name to store captured images and videos
	private static final String IMAGE_DIRECTORY_NAME = "GAIL India Ltd.";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApp = MyApplication.getInstance();
		setContentView(R.layout.activity_main);
		next = (Button) findViewById(R.id.next_label);
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validateInfo()){
					saveInfo();
					Intent intent = new Intent(MainActivity.this,
							IndustryActivity.class);
					startActivity(intent);
				}
			}
		});
		capture_pic = (Button) findViewById(R.id.click_pic_label);
		capture_pic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				captureImage();
			}
		});
		state = (EditText) findViewById(R.id.state_value);
		name = (EditText) findViewById(R.id.name_val);
		email = (EditText) findViewById(R.id.email_value);
		phone = (EditText) findViewById(R.id.phone_value);
		cName = (EditText) findViewById(R.id.company_value);

	}

	protected void getValueFromEdittext() {
		nameStr = name.getText().toString();
		emailStr = email.getText().toString();
		phoneStr = phone.getText().toString();
		cNameStr = cName.getText().toString();
		stateStr = state.getText().toString();
	}

	protected boolean validateInfo() {
		getValueFromEdittext();
		if(nameStr.equals("")||emailStr.equals("")||phoneStr.equals("")
				||cNameStr.equals("")||stateStr.equals("")){
			Toast.makeText(this, R.string.empty_message, Toast.LENGTH_LONG).show();
			return false;
		}
		else return true;
	}

	protected void saveInfo() {
		myApp.setName(nameStr);
		myApp.setEmail(emailStr);
		myApp.setPhoneNo(phoneStr);
		myApp.setCompanyName(cNameStr);
		myApp.setState(stateStr);
		if(fileUri!=null){
			myApp.setImageUrl(fileUri.toString());
			Log.d(TAG, "name "+nameStr + "email "+emailStr+ "phone "+phoneStr+"company "+cNameStr
					+"state "+stateStr+"image uri "+fileUri.toString());
		}
		Log.d(TAG, "in other log"+"name "+nameStr + "email "+emailStr+ "phone "+phoneStr+"company "+cNameStr
				+"state "+stateStr);
	}

	/**
	 * Checking device has camera hardware or not
	 * */
	private boolean isDeviceSupportCamera() {
		if (getApplicationContext().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	/**
	 * Capturing Camera Image will lauch camera app requrest image capture
	 */
	private void captureImage() {
		if (!isDeviceSupportCamera()) {
			Toast.makeText(MainActivity.this,
					getResources().getString(R.string.no_camera),
					Toast.LENGTH_LONG).show();

		} else {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

			// start the image capture Intent
			startActivityForResult(intent, REQUEST_TAKE_PHOTO);
		}
	}

	/**
	 * Creating file uri to store image/video
	 */
	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/**
	 * returning image / video
	 */
	private static File getOutputMediaFile(int type) {

		// External sdcard location
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				IMAGE_DIRECTORY_NAME);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
						+ IMAGE_DIRECTORY_NAME + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else {
			return null;
		}

		return mediaFile;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
			Toast.makeText(MainActivity.this,
					getResources().getString(R.string.photo_clicked),
					Toast.LENGTH_SHORT).show();
		}
	}

}
