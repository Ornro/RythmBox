package org.ups.ter.RythmBox.circlemanager;

import com.badlogic.gdx.graphics.Texture;

public class Circle {
	
	int posX;
	int posY;
	Texture texture;
	
	Circle(Texture texture, int x, int y) {
		this.posX = x;
		this.posY = y;
		this.texture = texture;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Texture getTexture() {
		return texture;
	}
	
}
