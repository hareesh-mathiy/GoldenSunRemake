package gameobjects;

/**
 * Created by Hareesh on 3/11/2016.
 */

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Isaac {

    private Vector2 position;
    private float rotation;
    private int width;
    private int height;
    public boolean gameOver;
    public boolean res;
    public boolean isUp, isDown, isLeft, isRight, isUpRight, isDownRight, isUpLeft, isDownLeft,
            notMoving, isRunning, colliding, isSliding, onIce;
    public String facing, turn;
    public World world;
    public Body b2body;
    public int hpos;
    public boolean wallCollideUp, wallCollideDown, wallCollideLeft, wallCollideRight;
    public boolean isTaunting;
    public boolean disableMovement;
    public boolean isFlashing;
    public boolean isMovePsy;
    public boolean isPsy;
    public boolean isFlashingWhite;
    public boolean onIceMoving;
    public boolean HC;
    private String location;
    private boolean hidden;

    public Isaac(World world, float x, float y, int width, int height) {
        this.world = world;
        this.width = width;
        this.height = height;
        this.world = world;
        position = new Vector2(x, y);
        gameOver = false;
        res = false;
        isUp = false;
        isDown = false;
        isLeft = false;
        isRight = false;
        isUpLeft = false;
        isDownLeft = false;
        isUpRight = false;
        isDownRight = false;
        notMoving = true;
        isRunning = false;
        colliding = false;
        isSliding = false;
        onIce = false;
        wallCollideUp = false;
        wallCollideDown = false;
        wallCollideLeft = false;
        wallCollideRight = false;
        isTaunting = false;
        isFlashing = false;
        isFlashingWhite = false;
        disableMovement = false;
        onIceMoving = false;
        isMovePsy = false;
        rotation = 0;
        facing = "down";
        turn = null;
        hpos = 0;
        HC = false;
        isPsy = false;
        location = null;
        hidden = false;
        defineIsaac();
    }

    public void defineIsaac(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x+width/2-1.5f,position.y+height/2-1.5f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(4f);
        fdef.shape = shape;
        fdef.friction = 0f;
        b2body.createFixture(fdef).setUserData("isaac");

        EdgeShape temp = new EdgeShape();
        temp.set(new Vector2(-0.05f, -4), new Vector2(0.05f, -4));
        fdef.isSensor = false;
        fdef.shape = temp;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("temp");
        shape.dispose();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setX(float x) { position.x = x; }

    public void setY(float y) { position.y = y; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Boolean isRunning(){ return isRunning;}
    public void setRunning(Boolean running){ isRunning = running;}

    public Boolean isUp(){ return isUp;}
    public void setUp(Boolean up){ isUp = up;}

    public Boolean isDown(){ return isDown;}
    public void setDown(Boolean down){ isDown = down;}

    public Boolean isLeft(){ return isLeft;}
    public void setLeft(Boolean left){ isLeft = left;}

    public Boolean isRight(){ return isRight;}
    public void setRight(Boolean right){ isRight = right;}

    public Boolean isUpLeft(){ return isUpLeft;}
    public void setUpLeft(Boolean upleft){ isUpLeft = upleft;}

    public Boolean isDownLeft(){ return isDownLeft;}
    public void setDownLeft(Boolean downleft){ isDownLeft = downleft;}

    public Boolean isUpRight(){ return isUpRight;}
    public void setUpRight(Boolean upright){ isUpRight = upright;}

    public Boolean isDownRight(){ return isDownRight;}
    public void setDownRight(Boolean downright){ isDownRight = downright;}

    public Boolean notMoving(){ return notMoving; }

    public void setNotMoving(Boolean notmoving){
        notMoving = notmoving;
        isUp = false;
        isDown = false;
        isLeft = false;
        isRight = false;
        isDownLeft = false;
        isDownRight = false;
        isUpLeft = false;
        isUpRight = false;
    }

    public void setFacing(String fcg){
        facing = fcg;
    }

    public String getFacing(){
        return facing;
    }

    public void setTurn(String t) { turn = t; }

    public String getTurn(){ return turn; }

    public int getHPos() {
        return hpos;
    }

    public void setHPos(int hpos) {
        this.hpos = hpos;
    }

    public void setHC(Boolean HC) {
        this.HC = HC;
    }

    public void setLocation(String location){ this.location = location;}
    public String getLocation(){ return location; }

    public void setHidden(boolean hidden){ this.hidden = hidden; }
    public Boolean getHidden(){ return hidden; }
}
