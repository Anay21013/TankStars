package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TankStars;

public class Home implements Screen {
    private Sprite home;
    private TankStars game;
    private SpriteBatch batch;

    public Home(TankStars game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        Texture homeTexture = new Texture("tank-stars.jpg");
        home = new Sprite(homeTexture);
        home.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        home.draw(batch);
        batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new Menu(game));
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
        home.getTexture().dispose();
    }
}
