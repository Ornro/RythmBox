package org.ups.ter.RythmBox;

import java.util.HashSet;
import java.util.Set;

import org.ups.ter.RythmBox.circlemanager.Circle;
import org.ups.ter.RythmBox.circlemanager.CircleManager;

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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class NumbersDisplayer implements ApplicationListener {
	private SpriteBatch batch;
	
    private Set<Circle> circles;
	private CircleManager circleManager;
	
	@Override
	public void create() {
		
		circleManager = new CircleManager(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		
		batch = new SpriteBatch();
		
    	circles = circleManager.generateNewCircles();
		
		scheduleCirclesRefresh();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		drawCircles();
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
	
	private void scheduleCirclesRefresh() {
		float delay = 3;

		Timer.schedule(new Task(){
			@Override
		    public void run() {
		    	circles = circleManager.generateNewCircles();
		    }
		}, delay, delay);	
	}
	
	private void drawCircles() {
		for(Circle c : circles) {
			batch.draw(c.getTexture(), c.getPosX(), c.getPosY());	
		}
	}
	
}
