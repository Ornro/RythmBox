package org.ups.ter.RythmBox.circlemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Circle extends Actor implements Comparable {
	private Texture 				texture;
	private CircleManager			manager;
	private final int 				number;
	private int 					posX;
	private int 					posY;
	private int						numberOfTimesTapped = 0;
	private boolean 				isAlive = true;
	private Type 					type;
	private boolean 				isAccepted = false;
	
	public enum Type {
	    CLICK_IT,
	    AVOID_IT,
	    TAP_IT
	}
	
	Circle(CircleManager parent, int number, Texture texture, int x, int y, int scaledWidth, int scaledHeight, Type type) {
		this.manager = parent;
		this.number = number;
		this.posX = x;
		this.posY = y;
		this.texture = texture;
		this.type = type;
		
		// scales the size of the circles at 10%
		// of the screen size and set bounds
		setWidth(scaledWidth);
		setHeight(scaledHeight);
		setBounds(this.posX, this.posY, scaledWidth, scaledHeight);
		
        addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
            	handleTap(count);
            }
        });
	}
	
	private final void handleTap(int count) {    
		this.numberOfTimesTapped += 1;
		manager.handleTap(this,numberOfTimesTapped);
	}
	
	@Override
    public void draw(SpriteBatch batch, float alpha){
		System.out.println("Drawing :"+this.getHeight());
        batch.draw(texture, this.posX, this.posY,this.getWidth(),this.getHeight());
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
		if (posX != other.posX || posY != other.posY)
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
	
	public void kill(Texture newTexture){
		this.texture = newTexture;
		this.isAlive = false;
		this.isAccepted = false;
	}
	
	public void accept(Texture newTexture){
		this.texture = newTexture;
		this.isAccepted = true;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public boolean isAccepted(){
		return isAccepted;
	}
	
	public Type getType(){
		return this.type;
	}
	
}
