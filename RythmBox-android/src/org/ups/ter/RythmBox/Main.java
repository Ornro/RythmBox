package org.ups.ter.RythmBox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		ImageView imageViewPlay = (ImageView) findViewById(R.id.imageViewPlay);
		ImageView imageViewHighScore = (ImageView) findViewById(R.id.imageViewHighScore);
		ImageView imageViewExit = (ImageView) findViewById(R.id.imageViewExit);

		imageViewPlay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Main.this, GameActivity.class));
			}
		});

		imageViewHighScore.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						HighScoreActivity.class);
				startActivity(intent);
			}
		});

		imageViewExit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// Write your awesome code here
				finish();
				System.exit(0);
			}
		});

	}

}
