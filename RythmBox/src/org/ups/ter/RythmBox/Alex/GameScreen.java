package org.ups.ter.RythmBox.Alex;

import java.util.Iterator;

import org.ups.ter.RythmBox.Frames.DanceAnimation;
import org.ups.ter.RythmBox.Music.MusicPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
  final Box game;

  private static final float		FR = 0.25f;
  private static final int        FRAME_COLS = 5;         
  private static final int        FRAME_ROWS = 3;         
  
  DanceAnimation                  normalGuy1;         
  DanceAnimation                  normalGuy2; 
  DanceAnimation                  vador; 
  
  SpriteBatch                     spriteBatch;
  Texture							background;
  float stateTime;  // number of seconds since the animation started


	public GameScreen(final Box gam) {
		this.game = gam;
		
		background = new Texture(Gdx.files.internal("data/backgrounds/bucarest.png"));
        
    	normalGuy1 = new DanceAnimation("data/sprites/dancing-guy.png");
		normalGuy2 = new DanceAnimation("data/sprites/dancing-guy.png");
		vador =	new DanceAnimation("data/sprites/dancing-vador.png");
        spriteBatch = new SpriteBatch();
        game.m.stop();
        game.m.setSong("data/music/easy.mp3");
        game.m.playFrom();
	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  
        
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(normalGuy1.getCurrentFrame(true), 100, 30); // draws the thing
        spriteBatch.draw(vador.getCurrentFrame(true), 200, 5); // draws the thing
        spriteBatch.draw(normalGuy2.getCurrentFrame(true), 300, 25); // draws the thing
        spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		
	}

}
