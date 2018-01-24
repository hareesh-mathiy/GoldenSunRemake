package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.TestGame;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import gameobjects.Isaac;
import helpers.GameRenderer;
import helpers.InputHandler;
import helpers.AssetLoader;
import helpers.ServerConfig;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Hareesh on 2016-03-20.
 */
public class GameScreen implements Screen {
    private InputHandler inputHandler;
    private GameRenderer renderer;
    private float runTime;
    private String device;
    long startTime = 0;
    public static float gameWidth, gameHeight;
    ServerConfig sc;

    public GameScreen(final TestGame game) {
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();

        switch (Gdx.app.getType()) {
            case Android:
                renderer = new GameRenderer("android");
                device = "android";
                break;
            case Desktop:
                renderer = new GameRenderer("desktop");
                device = "desktop";
                break;
            case WebGL:
        }
        sc = new ServerConfig(renderer);
        startTime = TimeUtils.nanoTime();
        inputHandler = new InputHandler(renderer);
        AssetLoader.load();
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        //Gdx.app.log("GS", Float.toString(1/delta));
        if(sc.getConnectedToServer()){
            renderer.render(runTime, sc);
            if (device == "android") {
                inputHandler.updateAndroid(delta);
            }
            if (device == "desktop") {
                inputHandler.updateDesktop(delta);
            }
            if(ServerConfig.otherPlayers != null){
                for(HashMap.Entry<String, Isaac> entry : ServerConfig.otherPlayers.entrySet()) {
                    inputHandler.updateOtherPlayers(delta, entry.getValue());
                }
            }
        } else{
            Gdx.gl.glClearColor( 0.33f, 0.22f, 0.33f, 1 );
            Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        }
    }


    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        renderer.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
        renderer.getBatch().dispose();
    }

    public float getRuntime() {
        return runTime;
    }


}
