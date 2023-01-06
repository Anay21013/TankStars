package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Player;

import java.awt.*;

public class Missile extends Sprite{
    public World world;
    public Body b2body;
    private Player player;
    public Missile(World world, Player player){
        this.world = world;
        defineMissile();
        this.player = player;

    }
    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y - getHeight()/2);

    }
    public void defineMissile(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(72/ TankStars.PPM,115/TankStars.PPM);
        bdef.type =BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape =new CircleShape();
        shape.setRadius(2/TankStars.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
