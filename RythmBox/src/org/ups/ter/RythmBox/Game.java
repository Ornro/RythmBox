package org.ups.ter.RythmBox;

import java.util.List;

import org.ups.ter.RythmBox.Frames.DanceAnimation;
import org.ups.ter.RythmBox.Music.MusicPlayer;
import org.ups.ter.RythmBox.circlemanager.Circle;
import org.ups.ter.RythmBox.circlemanager.CircleManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

 
public class Game implements ApplicationListener {
    
    DanceAnimation                  normalGuy;         
    DanceAnimation                  vador; 
    
    BitmapFont						font;
    SpriteBatch                     spriteBatch;
    Texture							background;
    	
    List<Circle> 					circles;
	CircleManager 					circleManager;
    
    float stateTime;  // number of seconds since the animation started
    
	private Stage stage;
	private MusicPlayer musicPlayer;
    
    @Override
    public void create() {
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        
        font = new BitmapFont();
        font.setScale(3.0f);
    	spriteBatch = new SpriteBatch();
        circleManager = new CircleManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
		musicPlayer = new MusicPlayer("data/music/easy.mp3");
		musicPlayer.playFrom(1.0f);
		
        Gdx.input.setInputProcessor(stage);

        generateBackgorund();
    	generateAnimations();
        scheduleCirclesRefresh();
    }
    
    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  

        spriteBatch.begin();
        spriteBatch.draw(background,0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());    
        drawDancingGuys();
        font.draw(spriteBatch, "Score : " + circleManager.getScore(), 30, 50);
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
		float delay = 1.9333333f;
		
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
	
	private void generateBackgorund(){
		background = new Texture(Gdx.files.internal("data/backgrounds/bucarest.png"));
	}
	
	private void generateAnimations(){
		normalGuy = new DanceAnimation("data/sprites/dancing-guy.png");
		vador =	new DanceAnimation("data/sprites/dancing-vador.png");
	}
	
	private void drawDancingGuys(){
		TextureRegion normalGuyTexture, vadorTexture;
		int height = normalGuy.getScaledHeight();
		int width = normalGuy.getScaledWidth();
		
		int thirdOfWidth = Gdx.graphics.getWidth()*33/100;
		
		int firstPositionWidth = thirdOfWidth/2-width/2;
		int secondPositionWidth = firstPositionWidth+thirdOfWidth;
		int thirdPositionWidth = secondPositionWidth+thirdOfWidth;
		
		int firstPositionHeight=Gdx.graphics.getHeight()*15/100;
		int secondPosition=Gdx.graphics.getHeight()*5/100;
		
		normalGuyTexture = normalGuy.getCurrentFrame(true);
		vadorTexture = vador.getCurrentFrame(true);	

		spriteBatch.draw(normalGuyTexture, firstPositionWidth, firstPositionHeight, height, width);
        spriteBatch.draw(normalGuyTexture, thirdPositionWidth, firstPositionHeight, height, width);
        spriteBatch.draw(vadorTexture, secondPositionWidth, secondPosition, height, width); 
	}
	
}
