package org.ups.ter.RythmBox.Music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicPlayer {
	
	public Music song;

	public MusicPlayer(String location) {
		song = Gdx.audio.newMusic(Gdx.files.internal(location));
	}
	
	public void playFrom(float f) {		
		song.play();					
	}
	
	public void playFrom() {
		song.play();
	}
	
	public void stop() {
		song.stop();
	}
	
	public void setSong(String location) {
		song = Gdx.audio.newMusic(Gdx.files.internal(location));
	}
	
	public boolean isPlaying(){
		return song.isPlaying();
	}
	
}
