package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TankStars;

public class Pause implements Screen {
    private Sprite menu;
    private TankStars game;
    private SpriteBatch batch;
    public Pause(TankStars game){
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        Texture menuTexture = new Texture("pause.png");
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

        if (Gdx.input.justTouched() && Gdx.input.getY()<=237 && Gdx.input.getY()>=151 && Gdx.input.getX()>=506 && Gdx.input.getX()<=813) {
            game.setScreen(new help(game));
        }
        if (Gdx.input.justTouched() && Gdx.input.getX()>=508 && Gdx.input.getX()<=814 && Gdx.input.getY()>=273 && Gdx.input.getY()<=361){
            game.setScreen(new PlayScreen(game));

        }
        if (Gdx.input.justTouched() && Gdx.input.getX()>=508 && Gdx.input.getX()<=814 && Gdx.input.getY()>=397 && Gdx.input.getY()<=483){
            System.exit(0);
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
