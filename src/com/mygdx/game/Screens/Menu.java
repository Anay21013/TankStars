package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TankStars;

public class Menu implements Screen {
    private Sprite menu;
    private TankStars game;
    private SpriteBatch batch;
    public Menu(TankStars game){
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        Texture menuTexture = new Texture("menu1.png");
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

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            game.setScreen(new playerSec(game));
        }
        if (Gdx.input.justTouched() && Gdx.input.getX()>=880 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=75 && Gdx.input.getY()<=172){
            game.setScreen(new playerSec(game));
        }
        if (Gdx.input.justTouched() && Gdx.input.getX()>=880 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=379 && Gdx.input.getY()<=471){
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
