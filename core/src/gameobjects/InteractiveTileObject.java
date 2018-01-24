package gameobjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameRenderer;
import gamelocations.LevelLoader;

/**
 * Created by Hareesh on 3/25/2016.
 */
public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    protected MapProperties props;
    protected LevelLoader ll;
    protected MapObject object;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds, LevelLoader ll, MapObject object){
        this.world = world;
        this.map = map;
        this.bounds = bounds;
        this.ll = ll;
        this.object = object;
        props = map.getProperties();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(bounds.getX() * 0.30f + bounds.getWidth()/2 * 0.30f, bounds.getY() * 0.30f + bounds.getHeight()/2 * 0.30f);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth()/2 * 0.30f, bounds.getHeight()/2 * 0.30f);
        fdef.shape = shape;
        fdef.isSensor = true;
        fixture = body.createFixture(fdef);
        ll.getBodies().add(body);

        shape.dispose();
    }

    public InteractiveTileObject(World world, TiledMap map, Shape shape, LevelLoader ll, MapObject object){
        this.world = world;
        this.map = map;
        this.ll = ll;
        this.object = object;

        FixtureDef fdef = new FixtureDef();
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bd);
        body.createFixture(shape, 1);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
        if(ll.getHeightChange()==1){
            ll.getUpperBodies().add(body);
        }
        ll.getBodies().add(body);
        shape.dispose();
    }

    public abstract void onHit(GameRenderer renderer);
    public abstract void onEndHit(GameRenderer renderer);
}
