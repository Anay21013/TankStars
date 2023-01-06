package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.collision._btMprSimplex_t;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Screens.Home;
import com.mygdx.game.Screens.Menu;
import com.mygdx.game.Screens.PlayScreen;

public class TankStars extends Game {
	public SpriteBatch batch;
	private Music theme;
	public static final int V_WIDTH = 1024;
	public static final int V_HEIGHT = 500;
	public static final float PPM = 100;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Home(this));
		theme = Gdx.audio.newMusic(Gdx.files.internal("maintheme.mp3"));

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
	}

	@Override
	public void render () {
		theme.setLooping(true);
		theme.play();
		super.render();


		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			System.exit(0);
		}

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
