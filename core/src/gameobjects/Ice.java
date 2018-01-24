package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameRenderer;
import gamelocations.LevelLoader;

/**
 * Created by Hareesh on 3/25/2016.
 */
public class Ice extends InteractiveTileObject {
    MapObjects objects;
    MapObject object;
    String ice;

    public Ice(World world, TiledMap map, Rectangle bounds, LevelLoader ll, MapObject object) {
        super(world, map, bounds, ll, object);
        this.object = object;
        fixture.setUserData(this);
    }

    @Override
    public void onHit(GameRenderer renderer) {
        objects = map.getLayers().get(10).getObjects();
        for (MapObject obj : objects) {
            ice = (String) obj.getProperties().get("ice");
            if((object.equals(obj)) && Integer.parseInt(ice) == 1) {
                if(renderer.getIsaac().onIce != true) {
                    if(renderer.getIsaac().getFacing().equals("upLeft")) renderer.getIsaac().setFacing("left");
                    if(renderer.getIsaac().getFacing().equals("downLeft")) renderer.getIsaac().setFacing("left");
                    if(renderer.getIsaac().getFacing().equals("upRight")) renderer.getIsaac().setFacing("right");
                    if(renderer.getIsaac().getFacing().equals("downRight")) renderer.getIsaac().setFacing("right");
                    renderer.getIsaac().onIce = true;
                }
                if(renderer.getIsaac().isSliding != true){
                    renderer.getIsaac().isSliding = true;
                }
            }
            if((object.equals(obj)) && Integer.parseInt(ice) == 0) {
                renderer.getIsaac().onIce = false;
                renderer.getIsaac().isSliding = false;
            }
        }
    }

    @Override
    public void onEndHit(GameRenderer renderer) {

    }
}
