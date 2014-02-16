package org.ups.ter.RythmBox.circlemanager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Circle extends Actor implements Comparable {
	
	CircleManager parent;
	
	int number;
	
	int posX;
	int posY;
	Texture texture;
	
	Circle(CircleManager parent, int number, Texture texture, int x, int y) {
		this.parent = parent;
		this.number = number;
		this.posX = x;
		this.posY = y;
		this.texture = texture;
		
		setWidth(128);
		setHeight(128);
		setBounds(x, y, 128, 128);
		
        addListener(new InputListener(){
            
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
            		int pointer, int button) {
            		handleTouch();
            	return true;
            }
        });
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
	
	public int getNumber() {
		return number;
	}
	

	@Override
    public void draw(SpriteBatch batch, float alpha){
        batch.draw(texture, this.posX, this.posY);
    }
	
	private final void handleTouch() {    	
    	// addAction(Actions.fadeOut(0.5f));
    	parent.circleTouched(number);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (number != other.number)
			return false;
		
		return true;
	}

	@Override
	public int compareTo(Object c2) {
		Circle c = (Circle)c2;
		
		if(this.number < c.getNumber()) {
			return -1;
		} else if(this.number > c.getNumber()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
}
