package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.EMissile;
import com.mygdx.game.Missile;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Enemy;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.TankStars;
import com.mygdx.game.Tools.B2WorldCreator;

import java.awt.*;
import java.util.ArrayList;


public class PlayScreen implements Screen {
    private TankStars game;
    private Player player;
    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;
    private Enemy enemy;
    private World world;
    private Texture txt;
    private Box2DDebugRenderer b2dr;
    private TextureAtlas atlas;
    ArrayList<Missile> missileList = new ArrayList<Missile>();
    ArrayList<EMissile> emissileList = new ArrayList<EMissile>();
    private Missile m;
    private EMissile e;
    private Music music;
    public PlayScreen(TankStars game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH/TankStars.PPM,TankStars.V_HEIGHT/TankStars.PPM,gamecam);
        hud = new Hud(game.batch);
        maploader = new TmxMapLoader();
        map = maploader.load("main-map.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map,1/TankStars.PPM);
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        player = new Player(world,this);
        enemy = new Enemy(world,this);
        new B2WorldCreator(world,map);
        txt = new Texture("bullet.png");
        atlas = new TextureAtlas("tanks.pack");
        m = new Missile(world,player);
        e = new EMissile(world,this);
        music = Gdx.audio.newMusic(Gdx.files.internal("explosion-5981.mp3"));
    }
    @Override
    public void show() {

    }

    public TextureAtlas getAtlas() {
        return new TextureAtlas("tanks.pack");
    }

    public void handleInput(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x<=2){
            player.b2body.applyLinearImpulse(new Vector2(0.05f,0.12f),player.b2body.getLocalCenter(),true);
            m.b2body.applyLinearImpulse(new Vector2(0.05f,0.12f),player.b2body.getLocalCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x<=2){
            player.b2body.applyLinearImpulse(new Vector2(-0.05f,0.12f),player.b2body.getLocalCenter(),true);
            m.b2body.applyLinearImpulse(new Vector2(-0.05f,0.12f),player.b2body.getLocalCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x<=2){
            enemy.b2body.applyLinearImpulse(new Vector2(0.05f,0.12f),player.b2body.getLocalCenter(),true);
            e.b2body.applyLinearImpulse(new Vector2(0.05f,0.12f),player.b2body.getLocalCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x<=2){
            enemy.b2body.applyLinearImpulse(new Vector2(-0.05f,0.12f),player.b2body.getLocalCenter(),true);
            e.b2body.applyLinearImpulse(new Vector2(-0.05f,0.12f),player.b2body.getLocalCenter(),true);
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && Gdx.input.getX() > 650){
            music.play();

            m.b2body.applyLinearImpulse(new Vector2(6f,6f),player.b2body.getLocalCenter(),true);
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && Gdx.input.getX() < 650){
            music.play();

            e.b2body.applyLinearImpulse(new Vector2(-6f,6f),player.b2body.getLocalCenter(),true);
        }
    }



    public void update(float dt){
        handleInput(dt);
        world.step(1 / 60f,6,2);
        player.update(dt);
        enemy.update(dt);
        m.update(dt);
        e.update(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.justTouched() && Gdx.input.getX() >=633 && Gdx.input.getX() <= 669 && Gdx.input.getY() >= 111 && Gdx.input.getY() <= 145){
            game.setScreen(new Pause(game));
        }
        renderer.render();
        b2dr.render(world,gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        game.batch.draw(txt,m.b2body.getPosition().x,m.b2body.getPosition().y,5/TankStars.PPM,5/TankStars.PPM);
        game.batch.draw(txt,e.b2body.getPosition().x,e.b2body.getPosition().y,5/TankStars.PPM,5/TankStars.PPM);
        player.draw(game.batch);
        enemy.draw(game.batch);
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
            missileList.add(new Missile(world,player));
            m = missileList.get(missileList.size()-1);
            missileList.clear();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)){
            emissileList.add(new EMissile(world,this));
            e = emissileList.get(emissileList.size()-1);
        }
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public World getWorld() {
        return world;
    }
}
