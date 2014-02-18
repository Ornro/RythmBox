package org.ups.ter.RythmBox.Frames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DanceAnimation {
	
    private static final int        FRAME_COLS = 5;         
    private static final int        FRAME_ROWS = 3;  
    public static final int			SPRITE_SPLITTING_HEIGHT = 120;
    public static final int			SPRITE_SPLITTING_WIDTH = 85;
    private float					FRAME_RATE = 0.25f;
    
    private int						scaledHeight;
    private int						scaledWidth;
    private boolean					isScaled = false;
    private String					spritePath;
    private Animation               danceAnimation;          
    private Texture                 danceSheet;              
    private TextureRegion[]         danceFrames;             
    TextureRegion                   currentFrame;  
    
    float stateTime;  // number of seconds since the animation started
    
    /**
     * animates a sprit 
     * @param path the internal path of the sprite
     * @param frameRate the frame rate
     */
    public DanceAnimation(String internalPath, float frameRate){
    	spritePath = internalPath;
    	initialize();
    	this.FRAME_RATE = frameRate;
    }
    
    private void initialize(){
    	danceSheet = new Texture(Gdx.files.internal(spritePath));    
		TextureRegion[][] tmp = TextureRegion.split(danceSheet, SPRITE_SPLITTING_WIDTH, SPRITE_SPLITTING_HEIGHT );                               
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

	public int getScaledHeight(){
		if (!isScaled) scaleSprites();
		return scaledHeight;
	}

	public int getScaledWidth(){
		if (!isScaled) scaleSprites();
		return scaledWidth;
	}

	private void scaleSprites(){
    	scaledHeight = DanceAnimation.SPRITE_SPLITTING_HEIGHT;
    	scaledWidth = DanceAnimation.SPRITE_SPLITTING_WIDTH;
		int windowWidth = Gdx.graphics.getWidth()*50/100;
		int windowHeight = Gdx.graphics.getHeight()*50/100;

		while (scaledHeight != windowHeight && scaledWidth != windowWidth){
			if (scaledHeight > windowHeight && scaledWidth > windowWidth){
				scaledHeight--;
				scaledWidth--;
			} else {
				scaledHeight++;
				scaledWidth++;
			}
		}
		isScaled = true;
    }
}