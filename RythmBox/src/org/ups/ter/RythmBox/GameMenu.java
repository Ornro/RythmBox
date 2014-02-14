package org.ups.ter.RythmBox;

import java.util.ArrayList;

import org.ups.ter.RythmBox.Music.MusicPlayer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture buttonPlay, buttonQuit, buttonHighScore, backgroundImage;
	private Sprite spritePlay,spriteQuit,spriteHighScore;
	private ArrayList<Texture> buttons;
	private SpriteBatch spriteBatch;
	private MusicPlayer m;
	private int w, h;
	
	@Override
	public void create() {		

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
		/*
		 spritePlay = new Sprite(buttonPlay);
		 spritePlay.setSize(256, 256);
		 //sprite.setPosition(w/4, 200);
		 
		 spriteQuit = new Sprite(buttonQuit);
		 spriteQuit.setSize(256, 256);
		 //sprite.setPosition(w/6, 400);
		 
		 spriteHighScore = new Sprite(buttonHighScore);
		 spriteHighScore.setSize(256, 256);
		 //sprite.setPosition(w/8, 600);
		*/
		 spriteBatch = new SpriteBatch();
		
		
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		for(Texture t : buttons ) {
			t.dispose();
		}
	
	}

	@Override
	public void render() {		
	
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         spriteBatch.begin();
         spriteBatch.draw(backgroundImage, 0, 0);
         spriteBatch.draw(buttonPlay, w/2, h/2);
         spriteBatch.draw(buttonHighScore, w/2, h/3);
         spriteBatch.draw(buttonQuit, w/2, h/6);
         spriteBatch.end();
         
         if(Gdx.input.justTouched()) {
        	 System.out.println("Touched that stuff");
         }
                 

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
