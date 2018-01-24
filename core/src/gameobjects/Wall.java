package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import gamelocations.LevelLoader;
import helpers.GameRenderer;

/**
 * Created by Hareesh on 3/28/2016.
 */
public class Wall extends InteractiveTileObject{

    public Wall(World world, TiledMap map, Shape shape, LevelLoader ll, MapObject object) {
        super(world, map, shape, ll, object);
        fixture.setUserData(this);
    }

    @Override
    public void onHit(GameRenderer renderer) {
        renderer.getIsaac().colliding = true;
    }

    @Override
    public void onEndHit(GameRenderer renderer) {
        renderer.getIsaac().colliding = false;
    }
}
