package org.ups.ter.RythmBox.circlemanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class CircleManager {

	private int gridCellWidth;
	private int gridCellHeight;
	
	private Texture[] numberTextures = new Texture[6];

	private List<Circle> circles = new ArrayList<Circle>();
	
	private int nextCircle;
	
	private int currentScore;


	public CircleManager(int screenWidth, int screenHeight) {
		gridCellWidth = screenWidth / 8;
		gridCellHeight = screenWidth / 4;
		currentScore = 0;
		
		initTextures();
	}
	
	public List<Circle> getCircles() {
		return circles;
	}
	
	public int getScore() {
		return currentScore;
	}
	
	private void initTextures() {
		for(int i = 0; i < 6; i++) {
			String imageFile = "data/numbers/number-" + (i+1) + ".png";
			numberTextures[i] = new Texture(Gdx.files.internal(imageFile));
			numberTextures[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}		
	}
	

	public List<Circle> generateNewCircles() {
		int circlesNumber = MathUtils.random(2, 4);
		
		for(Circle c : circles) {
			c.remove();
		}
		
		circles.clear();
		
		ArrayList<Integer> usedX = new ArrayList<Integer>();
		ArrayList<Integer> usedY = new ArrayList<Integer>();
		
		for(int i = 0; i < circlesNumber; i++) {
			int cellX, cellY;
			do {
				cellX = MathUtils.random(1, 6);
				cellY = MathUtils.random(0, 2);
			} while (usedX.contains(cellX) && usedY.contains(cellY));

			usedX.add(cellX); 		
			usedY.add(cellY);
			
			int circlePosX = gridCellWidth * cellX;
			int circlePosY = gridCellHeight * cellY;
			
			Circle tmpCircle = new Circle(this, i, numberTextures[i], circlePosX, circlePosY);
			tmpCircle.setTouchable(Touchable.enabled);
			tmpCircle.setZIndex(99);

			circles.add(tmpCircle);
		}
		
		nextCircle = 0;
		
		return circles;
	}

	public void circleTouched(int number) {

		if(number == nextCircle) {
			// Bon cercle touch�
			currentScore += (nextCircle+1) * 10;
			circles.get(nextCircle).remove();
			nextCircle++;
		} else {
			// Mauvais cercle touch�
			// On el�ve tous les cercles
			for(Circle c : circles) {
				c.remove();
			}
			Gdx.input.vibrate(600);
		}
		
	}

}
