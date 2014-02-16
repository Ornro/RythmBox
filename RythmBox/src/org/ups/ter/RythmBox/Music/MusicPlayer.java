package org.ups.ter.RythmBox.Music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MusicPlayer {
	
	public Sound song;

	public MusicPlayer(String location) {
		song = Gdx.audio.newSound(Gdx.files.internal(location));	
	}
	
	public void playFrom(float f) {		
		song.play(f);					
	}
	
	public void playFrom() {
		song.play();
	}
	
	public void stop() {
		song.stop();
	}
	
	public void setSong(String location) {
		song = Gdx.audio.newSound(Gdx.files.internal(location));
	}
	
	
}
