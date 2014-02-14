	package org.ups.ter.RythmBox;

	import com.badlogic.gdx.ApplicationListener;
	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.graphics.GL10;
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.Animation;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import com.badlogic.gdx.graphics.g2d.TextureRegion;
	
public class DanceAnimator implements ApplicationListener {
	    private static final int        FRAME_COLS = 5;         
	    private static final int        FRAME_ROWS = 3;         
	    
	    Animation                       danceAnimation;          
	    Texture                         danceSheet;              
	    TextureRegion[]                 danceFrames;             
	    SpriteBatch                     spriteBatch;            
	    TextureRegion                   currentFrame;           
	    
	    float stateTime;  // number of seconds since the annimation started                                 
	    
	    @Override
	    public void create() {
	            danceSheet = new Texture(Gdx.files.internal("/sprites/dancing_guy.png"));     
	            TextureRegion[][] tmp = TextureRegion.split(danceSheet, danceSheet.getWidth(), FRAME_COLS / danceSheet.getHeight() / FRAME_ROWS);                               
	            danceFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
	            int index = 0;
	            for (int i = 0; i < FRAME_ROWS; i++) {
	                    for (int j = 0; j < FRAME_COLS; j++) {
	                            danceFrames[index++] = tmp[i][j];
	                    }
	            }
	            danceAnimation = new Animation(0.025f, danceFrames);              
	            spriteBatch = new SpriteBatch();                                
	            stateTime = 0f;                                                 
	    }

	    @Override
	    public void render() {
	            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); 
	            stateTime += Gdx.graphics.getDeltaTime();                       
	            currentFrame = danceAnimation.getKeyFrame(stateTime, true);      
	            spriteBatch.begin();
	            spriteBatch.draw(currentFrame, 50, 50); 
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
}
