package org.ups.ter.RythmBox;

import org.ups.ter.RythmBox.Frames.DanceAnimation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game implements ApplicationListener{
	private static final float		FR = 0.25f;
    private static final int        FRAME_COLS = 5;         
    private static final int        FRAME_ROWS = 3;         
    
    DanceAnimation                  normalGuy1;         
    DanceAnimation                  normalGuy2; 
    DanceAnimation                  vador; 
    
    SpriteBatch                     spriteBatch;
    Texture							background;
    
    float stateTime;  // number of seconds since the animation started
    
    @Override
    public void create() {   	
    	generateBackgorund();
    	generateAnimations();
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  

        spriteBatch.begin();
        spriteBatch.draw(background,0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.draw(normalGuy1.getCurrentFrame(true), 100, 30); // draws the thing
        spriteBatch.draw(vador.getCurrentFrame(true), 200, 5); // draws the thing
        spriteBatch.draw(normalGuy2.getCurrentFrame(true), 300, 25); // draws the thing
        spriteBatch.end();
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
		// TODO Auto-generated method stub
		
	}
	
	private void generateBackgorund(){
		background = new Texture(Gdx.files.internal("data/backgrounds/bucarest.png"));
	}
	
	private void generateAnimations(){
		normalGuy1 = new DanceAnimation("data/sprites/dancing-guy.png");
		normalGuy2 = new DanceAnimation("data/sprites/dancing-guy.png");
		vador =	new DanceAnimation("data/sprites/dancing-vador.png");
	}
	
}
