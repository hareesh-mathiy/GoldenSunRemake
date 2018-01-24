package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

import gamelocations.Location;
import helpers.GameRenderer;
import gamelocations.LevelLoader;

/**
 * Created by Hareesh on 3/25/2016.
 */
public class Warp extends InteractiveTileObject{

    Location location;
    MapObjects objects;
    MapObject object;
    String warp;

    public Warp(World world, TiledMap map, Rectangle bounds, LevelLoader ll, MapObject object) {
        super(world, map, bounds, ll, object);
        this.object = object;
        fixture.setUserData(this);
    }

    @Override
    public void onHit(GameRenderer renderer) {
        final GameRenderer rend = renderer;
        Gdx.app.log("WARP", "hit");
        location = renderer.getLocation();
        //-----------------TEST-------------------------------
        if(renderer.getLocation().getLocation().equals("test")){
            objects =  map.getLayers().get(9).getObjects();
            for(MapObject obj : objects){
                warp = (String) obj.getProperties().get("warp");
                if(warp != null ){
                    if((object.equals(obj)) && Integer.parseInt(warp) == 1) {
                        ll.setEntering(true);
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                rend.setLocation("madraExt", 289, 27);
                                Gdx.app.log("WARP", "warping madraExt");
                            }
                        }, 0.5f);
                    }
                    if((object.equals(obj)) && Integer.parseInt(warp) == 2) {
                        ll.setEntering(true);MapObjects objects;
                        MapObject object;
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                rend.setLocation("imilExt", 173, 21);
                                Gdx.app.log("WARP", "warping imilExt");
                            }
                        }, 0.5f);
                    }
                }
            }
        }
        //-----------------MADRA-------------------------------
        if(renderer.getLocation().getLocation().equals("madraExt")){
            objects =  map.getLayers().get(9).getObjects();
            for(MapObject obj : objects){
                warp = (String) obj.getProperties().get("warp");
                if(warp != null ){
                    if((object.equals(obj)) && Integer.parseInt(warp) == 1) {
                        ll.setEntering(true);
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                rend.setLocation("test", 24, 96);
                                Gdx.app.log("WARP", "warping test");
                            }
                        }, 0.5f);
                    }
                }
            }
        }
        //-----------------IMIL-------------------------------
        if(renderer.getLocation().getLocation().equals("imilExt")){
            objects =  map.getLayers().get(9).getObjects();
            for(MapObject obj : objects){
                warp = (String) obj.getProperties().get("warp");
                if(warp != null ){
                    if((object.equals(obj)) && Integer.parseInt(warp) == 1) {
                        if((object.equals(obj)) && Integer.parseInt(warp) == 1) {
                            ll.setEntering(true);
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    rend.setLocation("test", 52, 96);
                                    Gdx.app.log("WARP", "warping test");
                                }
                            }, 0.5f);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEndHit(GameRenderer renderer) {

    }
}
