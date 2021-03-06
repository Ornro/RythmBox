package org.ups.ter.RythmBox;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class GameActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;
		
		// Default level
		int choice = 0;
		
		// Get the level id sent by LevelChooser activity
		Intent intent = getIntent();
		if(null != intent) {
			choice = intent.getIntExtra("levelId", 0);
		}
		
		switch (choice) {
		
			case 0:initialize(new Game(30,"data/music/easy.mp3"), cfg);break;
			case 1:initialize(new Game(60,"data/music/medium.mp3"), cfg);break;
			case 2:initialize(new Game(120,"data/music/hard.mp3"), cfg);break;
	
			default:break;
		}
		
		
	}
}
