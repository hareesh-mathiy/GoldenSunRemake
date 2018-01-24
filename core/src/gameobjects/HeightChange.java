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
public class HeightChange extends InteractiveTileObject {
    MapObjects objects;
    MapObject object;
    String hc;

    public HeightChange(World world, TiledMap map, Rectangle bounds, LevelLoader ll, MapObject object) {
        super(world, map, bounds, ll, object);
        this.object = object;
        fixture.setUserData(this);
    }

    @Override
    public void onHit(GameRenderer renderer) {

    }

    @Override
    public void onEndHit(GameRenderer renderer) {

        objects = map.getLayers().get(11).getObjects();
        for (MapObject obj : objects) {
            hc = (String) obj.getProperties().get("hc");
            if (hc != null) {
                if((object.equals(obj)) && Integer.parseInt(hc) == 0) {
                    if (renderer.getIsaac().getHPos() == 1 || renderer.getIsaac().getHPos() == -1){
                        renderer.getIsaac().setHPos(0);
                        renderer.getIsaac().setHC(true);
                        Gdx.app.log("HC", "0");
                    }

                }
                if((object.equals(obj)) && Integer.parseInt(hc) == 1) {
                    if (renderer.getIsaac().getHPos() == 0)
                    {
                        Gdx.app.log("HC", "1");
                        renderer.getIsaac().setHPos(1);
                        renderer.getIsaac().setHC(true);
                        ll.drawUpper();
                    }
                }
                if((object.equals(obj)) && Integer.parseInt(hc) == -1) {
                    if (renderer.getIsaac().getHPos() == 0) {
                        renderer.getIsaac().setHPos(-1);
                        renderer.getIsaac().setHC(true);
                        Gdx.app.log("HC", "-1");
                    }
                }
            }
        }
    }
}
