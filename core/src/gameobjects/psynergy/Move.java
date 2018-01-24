package gameobjects.psynergy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import gameobjects.Isaac;
import helpers.AssetLoader;

/**
 * Created by Hareesh on 2016-03-30.
 */
public class Move {
    Vector2 position;
    Isaac isaac;
    SpriteBatch batch;
    float stateTime;
    public boolean isEnding;
    private float width;
    private float height;

    public Move(Isaac isaac, SpriteBatch batch, float stateTime){
        this.isaac = isaac;
        this.batch = batch;
        this.stateTime = stateTime;
        isEnding = true;
        position = new Vector2();
        position.set(isaac.b2body.getPosition().x, isaac.b2body.getPosition().y);
        width = isaac.getWidth();
        height = isaac.getHeight();
    }

    public void draw(){
        if (isaac.facing.equals("up") || isaac.facing.equals("upLeft")) {
            batch.draw(AssetLoader.moveUpAni.getKeyFrame(stateTime / 2), position.x - isaac.getWidth() / 2, position.y, width, height);
        }
        if (isaac.facing.equals("down") || isaac.facing.equals("downRight")) {
            batch.draw(AssetLoader.moveRightAni.getKeyFrame(stateTime / 2), position.x-isaac.getWidth()/2, position.y-isaac.getHeight()/2, width, height);
        }
        if (isaac.facing.equals("left") || isaac.facing.equals("downLeft")) {
            batch.draw(AssetLoader.moveRightAni.getKeyFrame(stateTime / 2), position.x, position.y-7f, -width, height);
        }
        if (isaac.facing.equals("right") || isaac.facing.equals("upRight")) {
            batch.draw(AssetLoader.moveRightAni.getKeyFrame(stateTime / 2), position.x, position.y-7f, width, height);
        }
    }

    public void drawDisappearing(){
        if (isaac.facing.equals("up") || isaac.facing.equals("upLeft")) {
            batch.draw(AssetLoader.moveDisappearAni.getKeyFrame(stateTime / 2), position.x-isaac.getWidth()/2, position.y, width, height);
        }
        if (isaac.facing.equals("down") || isaac.facing.equals("downRight")) {
            batch.draw(AssetLoader.moveDisappearAni.getKeyFrame(stateTime / 2), position.x-isaac.getWidth()/2, position.y-isaac.getHeight()/2, width, height);
        }
        if (isaac.facing.equals("left") || isaac.facing.equals("downLeft")) {
            batch.draw(AssetLoader.moveDisappearAni.getKeyFrame(stateTime / 2), position.x, position.y-7f, -width, height);
        }
        if (isaac.facing.equals("right") || isaac.facing.equals("upRight")) {
            batch.draw(AssetLoader.moveDisappearAni.getKeyFrame(stateTime / 2), position.x, position.y-7f, width, height);
        }
    }

    public float getX(){
        return position.x;
    }

    public void setX(float x){
        position.x = x;
    }

    public float getY(){
        return position.y;
    }

    public void setY(float y){
        position.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
