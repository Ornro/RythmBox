package org.ups.ter.RythmBox;

import android.os.Bundle;
import android.provider.MediaStore.Audio;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new NumbersDisplayer(), cfg);
    }
} 
