package org.ups.ter.RythmBox;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "RythmBox";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 768;
		
		new LwjglApplication(new Game(20,"data/music/easy.mp3"), cfg);
	}
}
