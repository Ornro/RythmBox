package org.ups.ter.RythmBox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer {

	
	public boolean playMusic(String location) {
		
		Music music = Gdx.audio.newMusic(Gdx.files.internal(location));		
		
		return true;
		
	}
}
