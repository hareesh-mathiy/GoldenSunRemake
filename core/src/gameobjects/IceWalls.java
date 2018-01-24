package gameobjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import gamelocations.LevelLoader;
import helpers.GameRenderer;

/**
 * Created by Hareesh on 3/28/2016.
 */
public class IceWalls extends InteractiveTileObject{
    public IceWalls(World world, TiledMap map, Rectangle rect, LevelLoader ll, MapObject object) {
        super(world, map, rect, ll, object);
        fixture.setUserData(this);
    }

    @Override
    public void onHit(GameRenderer renderer) {

    }

    @Override
    public void onEndHit(GameRenderer renderer) {

    }
}
