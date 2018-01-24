package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import gameobjects.Isaac;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Hareesh on 11/27/2016.
 */

public class ServerConfig {
    private Socket socket;
    private boolean connectedToServer = false;
    public static HashMap<String, Isaac> otherPlayers;
    private GameRenderer renderer;
    private final float UPDATE_TIME = 1 / 60f;
    float timer;
    String id;
    boolean playersInLevel = false;

    public ServerConfig(GameRenderer renderer) {
        this.renderer = renderer;
        otherPlayers = new HashMap<String, Isaac>();
        connectSocket();
        configSocketEvents();
    }

    public void connectSocket() {
        try {
            socket = IO.socket("http://192.168.1.139:3001");
            socket.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
                connectedToServer = true;
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Disconnected");
                connectedToServer = false;
                socket = null;
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerID = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connected, ID: " + playerID);
                    Isaac newPlayer = new Isaac(renderer.getWorld(), 100 - 16, 30 - 16, 18, 20);
                    otherPlayers.put(playerID, newPlayer);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting new player ID");
                }
            }
        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    id = data.getString("id");
                    if(!renderer.getWorld().isLocked()) {
                        renderer.getWorld().destroyBody(otherPlayers.get(id).b2body);
                    }
                    otherPlayers.remove(id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting new player ID");
                }
            }
        }).on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray objects = (JSONArray) args[0];
                try {
                    for (int i = 0; i < objects.length(); i++) {
                        Isaac isaac = new Isaac(renderer.getWorld(), 100 - 16, 30 - 16, 18, 20);
                        Vector2 position = new Vector2();
                        Vector2 velocity = new Vector2();
                        position.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
                        position.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
                        velocity.x = ((Double) objects.getJSONObject(i).getDouble("velx")).floatValue();
                        velocity.y = ((Double) objects.getJSONObject(i).getDouble("vely")).floatValue();
                        if(!renderer.getWorld().isLocked()) {
                            isaac.b2body.setTransform(position.x, position.y, isaac.b2body.getAngle());
                            isaac.b2body.setLinearVelocity(velocity.x, velocity.y);
                        }
                        otherPlayers.put(objects.getJSONObject(i).getString("id"), isaac);
                    }
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting players." + e);
                }
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerID = data.getString("id");
                    Double x = data.getDouble("x");
                    Double y = data.getDouble("y");
                    Double velx = data.getDouble("velx");
                    Double vely = data.getDouble("vely");
                    String location = data.getString("location");
                    if (otherPlayers.get(playerID) != null) {
                        if(!renderer.getIsaac().getLocation().equals(location)){
                            otherPlayers.get(playerID).setHidden(true);
                            if(!renderer.getWorld().isLocked()) {
                                otherPlayers.get(playerID).b2body.setActive(false);
                            }
                        } else{
                            otherPlayers.get(playerID).setHidden(false);
                            if(!renderer.getWorld().isLocked()) {
                                otherPlayers.get(playerID).b2body.setActive(true);
                                otherPlayers.get(playerID).b2body.setTransform(x.floatValue(), y.floatValue(), otherPlayers.get(playerID).b2body.getAngle());
                                otherPlayers.get(playerID).b2body.setLinearVelocity(velx.floatValue(), vely.floatValue());
                            }
                        }
                    }
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error moving players.");
                }
            }
        });
    }

    public void updateServer(float dt) {
        timer += dt;
        if (timer >= UPDATE_TIME && connectedToServer) {
            JSONObject data = new JSONObject();
            try {
                if(!renderer.getWorld().isLocked()) {
                    data.put("x", renderer.getIsaac().b2body.getPosition().x);
                    data.put("y", renderer.getIsaac().b2body.getPosition().y);
                    data.put("velx", renderer.getIsaac().b2body.getLinearVelocity().x);
                    data.put("vely", renderer.getIsaac().b2body.getLinearVelocity().y);
                }
                data.put("location", renderer.getIsaac().getLocation());
                socket.emit("playerMoved", data);
            } catch (JSONException e) {
                Gdx.app.log("SocketIO", "Error sending update data");
            }
        }
    }

    public boolean getConnectedToServer() {
        return connectedToServer;
    }

}
