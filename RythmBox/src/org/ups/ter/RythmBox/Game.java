package org.ups.ter.RythmBox;

import java.util.List;

import org.ups.ter.RythmBox.Frames.DanceAnimation;
import org.ups.ter.RythmBox.Music.MusicPlayer;
import org.ups.ter.RythmBox.circlemanager.Circle;
import org.ups.ter.RythmBox.circlemanager.CircleManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

 
public class Game implements ApplicationListener {
   
    private DanceAnimation                  normalGuy;         
    private DanceAnimation                  vador; 
    
    private BitmapFont						font;
    private SpriteBatch                     spriteBatch;
    private Texture							background;
    	
    private CircleManager 					circleManager;
	
    private String 							songPath;
    private float							frequency;
    private int								savedXTimes = 0;

	private Stage 							stage;
	private MusicPlayer 					musicPlayer;
	
	/**
	 * 
	 * @param bpm battements par minute (rythme de la chanson)
	 * @param songPath le chemin de la chanson eg: "data/music/easy.mp3"
	 */
	public Game(int bpm, String songPath){
		super();
		this.frequency = (1f/bpm)*60; //convertion bpm a hz
		this.songPath = songPath;
	}
	
    
    @Override
    public void create() {
    	// cree la scene
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        
        // cree la police
        font = new BitmapFont();
        font.setScale(3.0f);
        
        
    	spriteBatch = new SpriteBatch();
    	
    	// genere la musique, le controlleur des cercles 
        circleManager = new CircleManager();
		musicPlayer = new MusicPlayer(songPath);
		musicPlayer.playFrom(1.0f);
		
		// cree le processeur pour les actions effectues sur la scene
		// eg: events ... 
		Gdx.input.setInputProcessor(stage);

		// genere les animations, le fond et lance le système de generation des cercles
        generateBackgorund();
    	generateAnimations();

    	scheduleCirclesRefresh(); // independant des annimations
    }
    
    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  
        
        spriteBatch.begin();
        // game over quand la musique finit
        if (!musicPlayer.isPlaying()) {
        	saveHighScore();
        } else {
	        spriteBatch.draw(background,0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());    
	        drawDancingGuys();
	        font.draw(spriteBatch, "Score : " + circleManager.getScore(), 30, 50);
        }
        spriteBatch.end();
        
        if (musicPlayer.isPlaying()) stage.draw();
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

	@Override
	public void dispose() {
		stage.dispose();		
	}
	
	private void scheduleCirclesRefresh() {
		Timer.schedule(new Task(){
			@Override
		    public void run() {
				if (musicPlayer.isPlaying()) stage.addActor(circleManager.generateCircle());
		    }
		}, frequency, frequency);	
	}
	
	private void generateBackgorund(){
		background = new Texture(Gdx.files.internal("data/backgrounds/sarajevo.png"));
	}
	
	
	private void generateAnimations(){
		normalGuy = new DanceAnimation("data/sprites/dancing-guy.png",frequency);
		vador =	new DanceAnimation("data/sprites/dancing-vador.png",frequency);
	}
	
	private void drawDancingGuys(){
		TextureRegion normalGuyTexture, vadorTexture;
		int height = normalGuy.getScaledHeight();
		int width = normalGuy.getScaledWidth();
		
		int thirdOfWidth = Gdx.graphics.getWidth()*33/100;
		
		int firstPositionWidth = thirdOfWidth/2-width/2;
		int secondPositionWidth = firstPositionWidth+thirdOfWidth;
		int thirdPositionWidth = secondPositionWidth+thirdOfWidth;
		
		int firstPositionHeight=Gdx.graphics.getHeight()*5/100;
		int secondPosition=Gdx.graphics.getHeight()*1/100;
		
		normalGuyTexture = normalGuy.getCurrentFrame(true);
		vadorTexture = vador.getCurrentFrame(true);	

		spriteBatch.draw(normalGuyTexture, firstPositionWidth, firstPositionHeight, height, width);
        spriteBatch.draw(normalGuyTexture, thirdPositionWidth, firstPositionHeight, height, width);
        spriteBatch.draw(vadorTexture, secondPositionWidth, secondPosition, height, width); 
	}
	
	private void displayGameOverScreen(){
		font.setScale(10.0f);
		int width=(int)font.getBounds("GAME OVER").width;
		int height=(int)font.getBounds("GAME OVER").height;
		circleManager.getScore();
		
		font.draw(spriteBatch, "GAME OVER", Gdx.graphics.getWidth()/2-width/2, Gdx.graphics.getHeight()/2+height);
		
		font.setScale(5.0f);
		String score = "Score: "+circleManager.getScore();
		width=(int)font.getBounds(score).width;
		height=(int)font.getBounds(score).height;
		font.draw(spriteBatch, score, Gdx.graphics.getWidth()/2-width/2, Gdx.graphics.getHeight()/2-height);
	}
	
	private void saveHighScore() {
		if (savedXTimes == 0){
			FileHandle file = Gdx.files.external("RythmBox/highscore.txt");
			file.writeString(""+circleManager.getScore() + "\n", true);
			savedXTimes ++;
		} 
		
		displayGameOverScreen();
		
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	Gdx.app.exit();
		    }
		}, 3);
	}
	
}
