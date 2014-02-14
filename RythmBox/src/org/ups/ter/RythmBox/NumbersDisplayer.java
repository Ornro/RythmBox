package org.ups.ter.RythmBox;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class NumbersDisplayer implements ApplicationListener {
	private SpriteBatch batch;
	private Texture[] numberTextures;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		
		for (int i = 1; i <= 6; i++) {
			numberTextures[i] = new Texture(Gdx.files.internal("data/numbers/number-1.png"));
			numberTextures[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		for (int i = 1; i <= 6; i++) {
			numberTextures[i].dispose();
		}
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		showCircles();
		batch.end();
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
	

	private void showCircles() {
		int baseX = 50;
		int baseY = 50;
		
		int circlesNumber = MathUtils.random(5) + 1;
		
		for(int i = 0; i <= circlesNumber; circlesNumber++) {
			batch.draw(numberTextures[i], randomizePosition(baseX), randomizePosition(baseY));	
		}
	}
	
	private int randomizePosition(int basePosition) {
		return MathUtils.random(512);
	}
}
