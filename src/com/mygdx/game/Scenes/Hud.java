package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;

import java.awt.*;

public class Hud implements Disposable {
    public Stage stage;
    Label levelLabel;
    Label life1;
    Label life2;
    private Viewport viewport;
    public Hud(SpriteBatch sb){
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        viewport = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);
        levelLabel = new Label("1 vs 1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        life1 = new Label("Player1",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        life2 = new Label("Player2",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        table.add(life1).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);
        table.add(life2).expandX().padTop(10);
        stage.addActor(table);
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
