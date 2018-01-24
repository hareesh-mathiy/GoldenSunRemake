package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameRenderer;
import gamelocations.LevelLoader;

/**
 * Created by Hareesh on 3/25/2016.
 */
public class Ladder extends InteractiveTileObject {

    public Ladder(World world, TiledMap map, Rectangle bounds, LevelLoader ll, MapObject object) {
        super(world, map, bounds, ll, object);
        fixture.setUserData(this);
    }

    @Override
    public void onHit(GameRenderer renderer) {
        Gdx.app.log("Ladder", "collision");
    }

    @Override
    public void onEndHit(GameRenderer renderer) {

    }
}
