package gamelocations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;
import helpers.GameRenderer;
import transitions.OverlayAccessor;


/**
 * Created by Hareesh on 3/21/2016.
 */
public class Location {
    TiledMap tiledMap;
    String location = "test";
    LevelLoader ll;
    MapProperties prop;
    int mapWidth;
    int mapHeight;
    int tilePixelWidth;
    int tilePixelHeight;
    GameRenderer renderer;

    public Location( GameRenderer renderer){
        this.renderer = renderer;
        setLocation("test");
    }

    public void setLocation(String location) {
        this.location = location;
        if(tiledMap!=null){
            Gdx.app.log("LOC", "disposing tiledMap");
            tiledMap.dispose();
        }
        if(location == "test"){
            Gdx.app.log("LOC", "creating test");
            tiledMap = new TmxMapLoader().load("data/GoldenSun/town/test/test-tiled.tmx");
            prop = tiledMap.getProperties();
            mapWidth = prop.get("width", Integer.class)*16;
            mapHeight = prop.get("height", Integer.class)*16;
            tilePixelWidth = prop.get("tilewidth", Integer.class);
            tilePixelHeight = prop.get("tileheight", Integer.class);
            Gdx.app.log("LOC", "created test");
            renderer.getIsaac().setLocation(location);
            ll = new LevelLoader(tiledMap, renderer);
        }
        if(location == "madraExt"){
            Gdx.app.log("LOC", "creating madraExt");
            tiledMap = new TmxMapLoader().load("data/GoldenSun/town/madra/madraExtTiled.tmx");
            prop = tiledMap.getProperties();
            mapWidth = prop.get("width", Integer.class)*32;
            mapHeight = prop.get("height", Integer.class)*32;
            tilePixelWidth = prop.get("tilewidth", Integer.class);
            tilePixelHeight = prop.get("tileheight", Integer.class);
            Gdx.app.log("LOC", "created madraExt");
            renderer.getIsaac().setLocation(location);
            ll = new LevelLoader(tiledMap, renderer);
        }
        if(location == "imilExt"){
            Gdx.app.log("LOC", "creating imilExt");
            tiledMap = new TmxMapLoader().load("data/GoldenSun/town/imil/imilExtTiled.tmx");
            prop = tiledMap.getProperties();
            mapWidth = prop.get("width", Integer.class)*16;
            mapHeight = prop.get("height", Integer.class)*16;
            tilePixelWidth = prop.get("tilewidth", Integer.class);
            tilePixelHeight = prop.get("tileheight", Integer.class);
            Gdx.app.log("LOC", "created imilExt");
            renderer.getIsaac().setLocation(location);
            ll = new LevelLoader(tiledMap, renderer);
        }
    }

    public void render(float runTime){
        if(ll.getEntering()) ll.getTM().update(Gdx.graphics.getDeltaTime());
        ll.render(runTime);
        ll.getTM2().update(Gdx.graphics.getDeltaTime());
    }

    public String getLocation(){
        return location;
    }

    public Integer getMapWidth(){
        return mapWidth;
    }

    public Integer getMapHeight(){
        return mapHeight;
    }

    public Array<Body> getBodies(){ return ll.getBodies(); }
    public Array<Body> getUpperBodies(){ return ll.getUpperBodies(); }
}
