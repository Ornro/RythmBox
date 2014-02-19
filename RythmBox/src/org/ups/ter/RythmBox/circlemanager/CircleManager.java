package org.ups.ter.RythmBox.circlemanager;

import java.util.ArrayList;
import java.util.List;

import org.ups.ter.RythmBox.circlemanager.Circle.Type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class CircleManager {
	private static final int		MAX_CIRCLES = 4;
	private int 					gridCellWidth;
	private int 					gridCellHeight;
	
	private Texture[] 				numberTexturesClick = new Texture[MAX_CIRCLES];
	private Texture[] 				numberTexturesTap = new Texture[MAX_CIRCLES];
	private Texture[] 				numberTexturesOk = new Texture[MAX_CIRCLES];
	private Texture[]				numberTexturesKo = new Texture[MAX_CIRCLES];
	private Texture					noClickTexture;
	
	private List<Circle> 			circles = new ArrayList<Circle>();
	private int 					currentScore;
	
	private int 					circleHeight;
	private int 					circleWidth;

	private int 					nextToDisplay = -1;
	private int 					nextToTouch = 0;
	private int 					numberOfRedHits = 0;
	private ArrayList<Integer> 		usedXCoordinates = new ArrayList<Integer>();
	private ArrayList<Integer> 		usedYCoordinates  = new ArrayList<Integer>();
	
	//Bricole et mascagne
	private boolean					wasLastRed = false;
	private int 					numberOfHorizontalLines;
	private int 					numberOfVerticalLines;
	
	/**
	 * Initialize manager
	 * 
	 * @param screenWidth
	 * @param screenHeight
	 */
	public CircleManager() {
		scaleNumbers(10); // scale numbers
		scaleGrid();
		
		currentScore = 0;
		
		initTextures();
	}
	
	/**
	 * scales the size of the circles 
	 * @param percentage
	 */
	private void scaleNumbers(int percentage){
		int scaledHeight = 128;
    	int scaledWidth = 128;
		int windowWidth = Gdx.graphics.getWidth()*percentage/100;
		int windowHeight = Gdx.graphics.getHeight()*percentage/100;

//		while (scaledHeight != windowHeight && scaledWidth != windowWidth){
//			if (scaledHeight > windowHeight && scaledWidth > windowWidth){
//				scaledHeight--;
//				scaledWidth--;
//			} else {
//				scaledHeight++;
//				scaledWidth++;
//			}
//		}
		
		circleHeight = windowWidth;
		circleWidth = windowWidth;
		System.out.println("Height: "+circleHeight);
		System.out.println("Width: "+circleWidth);
    }
	
	private void scaleGrid(){
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		
		numberOfHorizontalLines = screenWidth / circleWidth;
		numberOfVerticalLines = screenHeight / circleHeight;
		gridCellHeight = screenHeight / numberOfHorizontalLines;
		gridCellWidth = screenWidth / numberOfVerticalLines;
	}
	
	/**
	 * Initialize circle textures.
	 */
	private void initTextures() {
		for(int i = 0; i < MAX_CIRCLES; i++) {
			String imageFile = "data/numbers/number-" + (i+1) + ".png";
			numberTexturesClick[i] = new Texture(Gdx.files.internal(imageFile));
			numberTexturesClick[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			String imageFileYellow = "data/numbers/number-yellow-" + (i+1) + ".png";
			numberTexturesTap[i] = new Texture(Gdx.files.internal(imageFileYellow));
			numberTexturesTap[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			String imageFileGreen = "data/numbers/number-green-" + (i+1) + ".png";
			numberTexturesOk[i] = new Texture(Gdx.files.internal(imageFileGreen));
			numberTexturesOk[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			String imageFileRed = "data/numbers/number-red-" + (i+1) + ".png";
			numberTexturesKo[i] = new Texture(Gdx.files.internal(imageFileRed));
			numberTexturesKo[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}	
		
		noClickTexture = new Texture(Gdx.files.internal("data/numbers/number-red.png"));
		noClickTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	

	
	public void calculateScoreAndClean(){
		for(Circle c : circles) {
			int circleNumber = c.getNumber();
			
			if (c.isAlive()){
				if (c.isAccepted()) currentScore += (circleNumber+1) * 10;
			}else {
				currentScore -= (5-circleNumber) * 10;
			}
			c.remove();
		}
		currentScore -= numberOfRedHits * 50;
		
		circles.clear();
		usedXCoordinates.clear();
		usedYCoordinates.clear();
		
		numberOfRedHits = 0;
		nextToDisplay = -1;
		nextToTouch = 0;
		wasLastRed = false;
	}
	
	public Circle generateCircle(){
		if (nextToDisplay == MAX_CIRCLES-1){
			calculateScoreAndClean();
		}
		
		nextToDisplay++;
		if (MathUtils.random(10) <= 7+(nextToDisplay)){
			return createCircle(true);
		} else {
			return createCircle(false);
		}
	}
	
	private Circle createCircle(boolean normal){
		int cellX, cellY;
				
		do {
			cellX = MathUtils.random(1, numberOfVerticalLines-2);
			cellY = MathUtils.random(1, numberOfHorizontalLines-2);
		} while (usedXCoordinates.contains(cellX) && usedYCoordinates.contains(cellY));

		usedXCoordinates.add(cellX);
		usedYCoordinates.add(cellY);
		
		int circlePosX = gridCellWidth * cellX;
		int circlePosY = gridCellHeight * cellY;
		
		Circle tmpCircle = null;
		if (normal){
			tmpCircle = new Circle(this, nextToDisplay, numberTexturesClick[nextToDisplay], circlePosX, circlePosY, circleWidth, circleHeight, Type.CLICK_IT);
		} else {
			if (MathUtils.random(1) == 0 && !wasLastRed){ 
				nextToDisplay--;
				tmpCircle = new Circle(this, nextToDisplay, noClickTexture, circlePosX, circlePosY, circleWidth, circleHeight, Type.AVOID_IT);
				wasLastRed = true;
			} else {
				tmpCircle = new Circle(this, nextToDisplay, numberTexturesTap[nextToDisplay], circlePosX, circlePosY, circleWidth, circleHeight, Type.TAP_IT);
			}
		}
		
		tmpCircle.setTouchable(Touchable.enabled);
		tmpCircle.setZIndex(99);
		circles.add(tmpCircle);
		
		System.out.println(tmpCircle.toString());
		
		return tmpCircle;
	}
	
	public void handleTap(Circle circle, int numberOfTimes) {
		int circleNumber = circle.getNumber();
		
		if (circle.getType() == Type.AVOID_IT){
			numberOfRedHits += 1;
			Gdx.input.vibrate(600);
		} else {
			if (circleNumber != nextToTouch){
				circle.kill(numberTexturesKo[circleNumber]);
				Gdx.input.vibrate(600);
			} else {
				switch(circle.getType()){
				case CLICK_IT:
					if (numberOfTimes == 1) {
						circle.accept(numberTexturesOk[circleNumber]); //change color only
						incrementNextToTouch(circleNumber);
					} 
					break;
				case TAP_IT:
					if (numberOfTimes == circleNumber+1) {
						circle.accept(numberTexturesOk[circleNumber]); //change color only
						incrementNextToTouch(circleNumber);
					} 
					break;
				default:
					break;
				}
			}
		}
	}
	
	private void incrementNextToTouch(int start){
		int generatedCircles = circles.size();
		System.out.println("nextToTouch: "+nextToTouch);
		System.out.println("Touched "+start);
		System.out.println("size: "+generatedCircles);
		nextToTouch++;
		
		for (int i=start+1;i<generatedCircles;i++){
			System.out.println("i: "+i);
			System.out.println("isAlive: "+circles.get(i).isAlive());
			
			if (!circles.get(i).isAlive() && circles.get(i).getType() != Type.AVOID_IT) {
				nextToTouch++;
			} else break;
		}
		System.out.println("nextToTouch: "+nextToTouch);
	}
	
	public List<Circle> getCircles() {
		return circles;
	}
	
	public int getScore() {
		return currentScore;
	}
}
