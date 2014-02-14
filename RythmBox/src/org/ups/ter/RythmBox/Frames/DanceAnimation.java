package org.ups.ter.RythmBox.Frames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DanceAnimation {
	private static final float		FRAME_RATE = 0.25f;
    private static final int        FRAME_COLS = 5;         
    private static final int        FRAME_ROWS = 3;  
    
    private String					spritePath;
    private Animation               danceAnimation;          
    private Texture                 danceSheet;              
    private TextureRegion[]         danceFrames;             
    TextureRegion                   currentFrame;  
    
    float stateTime;  // number of seconds since the animation started
    
    /**
     * animates a sprit 
     * @param path the internal path of the sprite
     */
    public DanceAnimation(String internalPath){
    	spritePath = internalPath;
    	initialize();
    }
    
    private void initialize(){
		 danceSheet = new Texture(Gdx.files.internal(spritePath));     
           TextureRegion[][] tmp = TextureRegion.split(danceSheet, 85, 120 );                               
           danceFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
           int index = 0;
           for (int i = 0; i < FRAME_ROWS; i++) {
                   for (int j = 0; j < FRAME_COLS; j++) {
                           	danceFrames[index++] = tmp[i][j];
                   }
           }
           danceAnimation = new Animation(FRAME_RATE, danceFrames);              
           stateTime = 0f;          
	}
    
   public TextureRegion getCurrentFrame(boolean looping){
	   stateTime += Gdx.graphics.getDeltaTime();    
	   return danceAnimation.getKeyFrame(stateTime, looping);
   }
}
