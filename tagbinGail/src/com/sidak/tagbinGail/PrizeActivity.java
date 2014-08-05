package com.sidak.tagbinGail;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class PrizeActivity extends Activity {
	private Button prize;
	private Button next_button;
	private VideoView vidView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prize);
		prize=(Button)findViewById(R.id.prize_text);
		next_button=(Button)findViewById(R.id.next_button);
		vidView=(VideoView)findViewById(R.id.prize_video);
		Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vid);

		vidView.setVideoURI(uri);
		prize.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vidView.start();
			}
		});
		
		next_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PrizeActivity.this,
						IndustryActivity.class);
				startActivity(intent);
			}
		});
		
		vidView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				Random rand = new Random();
				int  n = rand.nextInt(2) ;
				if(n==1){
					prize.setText(R.string.prize_won);
					
				}
				else prize.setText(R.string.prize_lost);
			}
		});
	}
}
