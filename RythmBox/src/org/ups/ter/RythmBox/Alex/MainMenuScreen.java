package org.ups.ter.RythmBox.Alex;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {

  final Box game;

	OrthographicCamera camera;

	public MainMenuScreen(final Box gam) {
		game = gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.w, game.h);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.spriteBatch.setProjectionMatrix(camera.combined);

		game.spriteBatch.begin();

		game.spriteBatch.draw(game.backgroundImage, 0, 0);
		game.spriteBatch.draw(game.buttonPlay, game.w/2, game.h/2);
		game.spriteBatch.draw(game.buttonHighScore, game.w/2, game.h/3);
		game.spriteBatch.draw(game.buttonQuit, game.w/2, game.h/6);
		
		game.spriteBatch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
