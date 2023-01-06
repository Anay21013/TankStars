package com.mygdx.game.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.TankStars;

public class Enemy extends Sprite{
    public World world;
    public Body b2body;
    private TextureRegion left;
    public Enemy(World world,PlayScreen screen){
        super(screen.getAtlas().findRegion("left"));
        this.world = world;
        defineEnemy();
        left = new TextureRegion(getTexture(),20,29,48,48);
        setBounds(0,0,48/TankStars.PPM,48/TankStars.PPM);
    }
    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y - getHeight()/2);

    }
    public void defineEnemy(){
        BodyDef bdef =new BodyDef();
        bdef.position.set(928/ TankStars.PPM,180/TankStars.PPM);
        bdef.type =BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape =new CircleShape();
        shape.setRadius(7/TankStars.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
