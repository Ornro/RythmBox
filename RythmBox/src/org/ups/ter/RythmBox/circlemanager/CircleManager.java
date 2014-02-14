package org.ups.ter.RythmBox.circlemanager;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.MathUtils;

public class CircleManager {

	private int gridCellWidth;
	private int gridCellHeight;
	
	private Texture[] numberTextures = new Texture[6];

	private Set<Circle> circles = new HashSet<Circle>();

	
	public CircleManager(int screenWidth, int screenHeight) {
		gridCellWidth = screenWidth / 8;
		gridCellHeight = screenWidth / 4;
		
		initTextures();
	}
	
	public Set<Circle> getCircles() {
		return circles;
	}
	
	private void initTextures() {
		for(int i = 0; i < 6; i++) {
			String imageFile = "data/numbers/number-" + (i+1) + ".png";
			numberTextures[i] = new Texture(Gdx.files.internal(imageFile));
			numberTextures[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}		
	}
	

	public Set<Circle> generateNewCircles() {
		int circlesNumber = MathUtils.random(1, 5);
		
		circles.clear();
		
		for(int i = 0; i <= circlesNumber; i++) {
			int cellX = MathUtils.random(1, 6);
			int cellY = MathUtils.random(1, 3);

			int circlePosX = gridCellWidth * cellX;
			int circlePosY = gridCellHeight * cellY;
			
			circles.add(new Circle(numberTextures[i], circlePosX, circlePosY));
		}
		
		return circles;
	}
}
