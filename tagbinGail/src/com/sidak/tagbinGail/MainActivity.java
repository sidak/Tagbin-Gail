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
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button next;
	private Button capture_pic;
	String mCurrentPhotoPath;
	static final int REQUEST_TAKE_PHOTO = 1;
	private Uri fileUri; // file url to store image/video
	public static final int MEDIA_TYPE_IMAGE = 1;

	// directory name to store captured images and videos
	private static final String IMAGE_DIRECTORY_NAME = "GAIL India Ltd.";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		next = (Button) findViewById(R.id.next_label);
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						IndustryActivity.class);
				startActivity(intent);
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
			Toast.makeText(
					MainActivity.this,
					getResources().getString(R.string.photo_clicked), Toast.LENGTH_SHORT).show();
		}
	}

}
