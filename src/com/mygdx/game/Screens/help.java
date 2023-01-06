package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TankStars;

public class help implements Screen {
    private Sprite menu;
    private TankStars game;
    private SpriteBatch batch;
    public help(TankStars game){
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        Texture menuTexture = new Texture("help.png");
        menu = new Sprite(menuTexture);
        menu.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        menu.draw(batch);
        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new Pause(game));
        }
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
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        menu.getTexture().dispose();
    }
}