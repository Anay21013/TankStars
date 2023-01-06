package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TankStars;

public class enemysec implements Screen {
    private Sprite plsec;
    private TankStars game;
    private SpriteBatch batch;

    public enemysec(TankStars game) {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        Texture homeTexture = new Texture("enemysec.png");
        plsec = new Sprite(homeTexture);
        plsec.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        plsec.draw(batch);
        batch.end();
        if (Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen(game));
        }
    }

    @Override
    public void resize(int i, int i1) {

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
        plsec.getTexture().dispose();
    }
}
