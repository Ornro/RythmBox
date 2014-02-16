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

public class HighscoreScreen implements Screen {
  final Box game;   
  SpriteBatch                     spriteBatch;
  Texture							background, buttonHighScore;
  float stateTime;  // number of seconds since the animation started
 

	public HighscoreScreen(final Box gam) {
		this.game = gam;
		
		background = new Texture(Gdx.files.internal("data/menu/menu_back.png"));
		buttonHighScore = new Texture(Gdx.files.internal("data/menu/hiscores.png"));
        game.m.stop();
        spriteBatch = new SpriteBatch();
	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  
        
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(buttonHighScore, game.w/2, 100);		
		//game.spriteBatch.end();
		spriteBatch.end();
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
        
		
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
