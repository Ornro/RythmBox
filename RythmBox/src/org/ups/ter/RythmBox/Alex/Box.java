package org.ups.ter.RythmBox.Alex;

import java.util.ArrayList;

import org.ups.ter.RythmBox.Music.MusicPlayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Box extends Game {

		
	public Texture buttonPlay, buttonQuit, buttonHighScore, backgroundImage;
	public Sprite spritePlay,spriteQuit,spriteHighScore;
	public ArrayList<Texture> buttons;
	public SpriteBatch spriteBatch, batch;
	public MusicPlayer m;
	public int w, h;

	public void create() {
		batch = new SpriteBatch();
//		// Use LibGDX's default Arial font.
//		font = new BitmapFont();
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		m = new MusicPlayer("data/music/start_menu.mp3");
		m.playFrom(1.0f);
		
		buttonPlay = new Texture(Gdx.files.internal("data/menu/play.png"));
		buttonHighScore = new Texture(Gdx.files.internal("data/menu/hiscores.png"));
		buttonQuit = new Texture(Gdx.files.internal("data/menu/quit.png"));	
		backgroundImage = new Texture(Gdx.files.internal("data/menu/menu_back.png"));
	
		buttons = new ArrayList<Texture>();
		buttons.add(backgroundImage);
		buttons.add(buttonPlay);
		buttons.add(buttonHighScore);
		buttons.add(buttonQuit);
		for (Texture t : buttons ) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		}
	
		 spriteBatch = new SpriteBatch();
		 
		
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		spriteBatch.dispose();
	}

}
