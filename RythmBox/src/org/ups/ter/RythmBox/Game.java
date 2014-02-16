package org.ups.ter.RythmBox;

import java.util.List;
import java.util.Set;

import org.ups.ter.RythmBox.Frames.DanceAnimation;
import org.ups.ter.RythmBox.circlemanager.Circle;
import org.ups.ter.RythmBox.circlemanager.CircleManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Game implements ApplicationListener{
	private static final float		FR = 0.25f;
    private static final int        FRAME_COLS = 5;         
    private static final int        FRAME_ROWS = 3;         
    
    DanceAnimation                  normalGuy1;         
    DanceAnimation                  normalGuy2; 
    DanceAnimation                  vador; 
    
    SpriteBatch                     spriteBatch;
    Texture							background;
    	
    List<Circle> 					circles;
	CircleManager 					circleManager;
    
    float stateTime;  // number of seconds since the animation started
    
	private Stage stage;
    
    @Override
    public void create() {
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

    	background = new Texture(Gdx.files.internal("data/backgrounds/bucarest.png"));
        
    	normalGuy1 = new DanceAnimation("data/sprites/dancing-guy.png");
		normalGuy2 = new DanceAnimation("data/sprites/dancing-guy.png");
		vador =	new DanceAnimation("data/sprites/dancing-vador.png");
        spriteBatch = new SpriteBatch();
        circleManager = new CircleManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        Gdx.input.setInputProcessor(stage);

        scheduleCirclesRefresh();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  
        
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(normalGuy1.getCurrentFrame(true), 100, 30); // draws the thing
        spriteBatch.draw(vador.getCurrentFrame(true), 200, 5); // draws the thing
        spriteBatch.draw(normalGuy2.getCurrentFrame(true), 300, 25); // draws the thing
        spriteBatch.end();
        
		stage.draw();
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void dispose() {
		stage.dispose();		
	}
	
	private void scheduleCirclesRefresh() {
		float delay = 2.5f;
		
    	circles = circleManager.generateNewCircles();

		Timer.schedule(new Task(){
			@Override
		    public void run() {
				
		    	circles = circleManager.generateNewCircles();
		    	
				for(Circle c : circles) {
					stage.addActor(c);
				}
		    }
		}, delay, delay);	
	}
	
}
