package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.sun.corba.se.spi.activation.Server;

import java.util.HashMap;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import gameobjects.Isaac;
import gameobjects.psynergy.Move;
import transitions.MovePsyAccessor;

/**
 * Created by Hareesh on 3/28/2016.
 */
public class Animations {

    public SpriteBatch batch, batch2;
    public Isaac isaac;
    float stateTime, stateTimePsy;
    String isaacVertexShader;
    String isaacFragmentShader;
    ShaderProgram shader;
    int i = 0;
    int j = 0;
    int k = 0;
    TweenManager manager;
    Move move;
    public boolean psyActive;
    private GameRenderer renderer;

    public Animations(SpriteBatch batch, Isaac isaac, GameRenderer renderer) {
        this.batch = batch;
        batch2 = new SpriteBatch();
        this.isaac = isaac;
        this.renderer = renderer;

        psyActive = false;
        move = new Move(isaac, batch, stateTime);
        Tween.registerAccessor(Move.class, new MovePsyAccessor());
        manager = new TweenManager();
        isaacVertexShader = Gdx.files.internal("data/shaders/vertex.glsl").readString();
        isaacFragmentShader = Gdx.files.internal("data/shaders/fragment.glsl").readString();
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(isaacVertexShader, isaacFragmentShader);
        System.out.println(shader.isCompiled() ? "Shader Compiled" : shader.getLog());

    }

    public void run(float runTime) {
        batch.begin();
        if(!renderer.getWorld().isLocked()) {
            if (isaac.notMoving) {
                if (isaac.isPsy) {
                    isaac.disableMovement = true;
                    playPsy(runTime, isaac);
                    if (psyActive) playMove(stateTimePsy, isaac);

                } else {
                    batch.setShader(null);
                    playStanding(runTime, isaac);
                }
            }
            if (isaac.onIce) {
                playSlide(runTime, isaac);
            } else {
                if (isaac.isRunning()) playRun(runTime, isaac);
                if (!isaac.isRunning()) playWalk(runTime, isaac);
            }

            for (HashMap.Entry<String, Isaac> entry : ServerConfig.otherPlayers.entrySet()) {
                if (!entry.getValue().getHidden()) {
                    if (entry.getValue().notMoving) {
                        if (entry.getValue().isPsy) {
                            entry.getValue().disableMovement = true;
                            playPsy(runTime, entry.getValue());
                            if (psyActive) playMove(stateTimePsy, entry.getValue());

                        } else {
                            batch.setShader(null);
                            playStanding(runTime, entry.getValue());
                        }
                    }
                    if (entry.getValue().onIce) {
                        playSlide(runTime, entry.getValue());
                    } else {
                        if (entry.getValue().isRunning()) playRun(runTime, entry.getValue());
                        if (!entry.getValue().isRunning()) playWalk(runTime, entry.getValue());
                    }
                }
            }
        }
        batch.end();
    }


    private void playPsy(float runTime, Isaac isaac) {
        stateTime += Gdx.graphics.getDeltaTime();
        if ((15 * i) % 2 == 0) {
            if (j < 18) {
                if ((15 * i) % 2 == 0) isaac.isFlashingWhite = true;
                j++;
            } else isaac.isFlashingWhite = false;
            isaac.isFlashing = true;
        } else isaac.isFlashing = false;
        i++;
        if (isaac.isFlashing) {
            if (isaac.isFlashingWhite) {
                shader.begin();
                shader.setUniformf("u_distort", 255, 255, 255);
                shader.end();
                batch.setShader(shader);
            } else {
                psyActive = true;
                shader.begin();
                shader.setUniformf("u_distort", MathUtils.random(2), MathUtils.random(2), MathUtils.random(2));
                shader.end();
                batch.setShader(shader);
                batch.setColor(Color.DARK_GRAY);
            }
        } else batch.setShader(null);


        if (isaac.facing.equals("up")) {
            batch.draw(AssetLoader.isaacPsyUAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }
        if (isaac.facing.equals("down")) {
            batch.draw(AssetLoader.isaacPsyDAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }
        if (isaac.facing.equals("left")) {
            batch.draw(AssetLoader.isaacPsyRAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }
        if (isaac.facing.equals("right")) {
            batch.draw(AssetLoader.isaacPsyRAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }
        if (isaac.facing.equals("upLeft")) {
            batch.draw(AssetLoader.isaacPsyURAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }
        if (isaac.facing.equals("upRight")) {
            batch.draw(AssetLoader.isaacPsyURAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }
        if (isaac.facing.equals("downLeft")) {
            batch.draw(AssetLoader.isaacPsyDRAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }
        if (isaac.facing.equals("downRight")) {
            batch.draw(AssetLoader.isaacPsyDRAni.getKeyFrame(stateTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
            batch.setShader(null);
        }

        if (AssetLoader.isaacPsyDRAni.isAnimationFinished(stateTime / 2)) {
            isaac.isPsy = false;
            isaac.disableMovement = false;
            psyActive = false;
            stateTime = 0;
            stateTimePsy = 0;
            i = 0;
            j = 0;
            k = 0;
        }
    }

    private void playMove(float runTime, Isaac isaac) {
        stateTimePsy += Gdx.graphics.getDeltaTime();

        move.setX(isaac.b2body.getPosition().x);
        move.setY(isaac.b2body.getPosition().y);
        move.setWidth(isaac.getWidth());
        move.setHeight(isaac.getHeight());

        if (isaac.facing.equals("up")) {
            Timeline tl = Timeline.createParallel()
                    .beginParallel()
                    .push(Tween.to(move, MovePsyAccessor.Y, 1f).target(move.getY() + 24f))
                    .push(Tween.to(move, MovePsyAccessor.SCALE, 1f).target(0, 0))
                    .end()
                    .start(manager);
            tl.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.drawDisappearing();
        }
        if (isaac.facing.equals("down")) {
            Timeline.createSequence()
                    .push(Tween.to(move, MovePsyAccessor.Y, 0.5f).target(move.getY() - 8f))
                    .pushPause(0.2f)
                    .push(Tween.to(move, MovePsyAccessor.Y, 0.5f).target(move.getY() + 16f))
                    .start(manager);
            manager.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.draw();
        }
        if (isaac.facing.equals("left")) {
            Timeline.createSequence()
                    .push(Tween.to(move, MovePsyAccessor.X, 0.5f).target(move.getX() - 4f))
                    .pushPause(5f)
                    .push(Tween.to(move, MovePsyAccessor.Y, 0.5f).target(move.getY() + 16f))
                    .start(manager);
            manager.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.draw();
        }
        if (isaac.facing.equals("right")) {
            Timeline.createSequence()
                    .push(Tween.to(move, MovePsyAccessor.X, 0.5f).target(move.getX() + 4f))
                    .pushPause(0.2f)
                    .push(Tween.to(move, MovePsyAccessor.Y, 0.5f).target(move.getY() + 16f))
                    .start(manager);
            manager.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.draw();

        }
        if (isaac.facing.equals("upLeft")) {
            Timeline.createSequence()
                    .push(Tween.to(move, MovePsyAccessor.Y, 0.5f).target(0, isaac.b2body.getPosition().y - isaac.getHeight() / 5 + 8f))
                    .pushPause(0.2f)
                    .push(Tween.to(move, MovePsyAccessor.Y, 1f).target(0, isaac.b2body.getPosition().y - isaac.getHeight() / 5 + 8f)).start(manager);
            manager.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.draw();
        }
        if (isaac.facing.equals("upRight")) {
            Timeline.createSequence()
                    .push(Tween.to(move, MovePsyAccessor.X, 0.5f).target(move.getX() + 4f))
                    .pushPause(0.2f)
                    .push(Tween.to(move, MovePsyAccessor.Y, 1f).target(0, isaac.b2body.getPosition().y - isaac.getHeight() / 5 + 8f)).start(manager);
            manager.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.draw();

        }
        if (isaac.facing.equals("downLeft")) {
            Timeline.createSequence()
                    .push(Tween.to(move, MovePsyAccessor.X, 0.5f).target(move.getX() - 4f))
                    .pushPause(0.2f)
                    .push(Tween.to(move, MovePsyAccessor.Y, 1f).target(0, isaac.b2body.getPosition().y - isaac.getHeight() / 5 + 8f)).start(manager);

            manager.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.draw();
        }
        if (isaac.facing.equals("downRight")) {
            Timeline.createSequence()
                    .push(Tween.to(move, MovePsyAccessor.Y, 0.5f).target(0, isaac.b2body.getPosition().y - isaac.getHeight() / 5 - 8f))
                    .pushPause(0.2f)
                    .push(Tween.to(move, MovePsyAccessor.Y, 1f).target(0, isaac.b2body.getPosition().y - isaac.getHeight() / 5 + 8f)).start(manager);
            manager.update(stateTimePsy);
            batch.setColor(1, 1, 1, 1);
            move.draw();
        }


    }

    private void playStanding(float runTime, Isaac isaac) {
        if (isaac.facing.equals("up")) {
            batch.draw(AssetLoader.isaacBackAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.facing.equals("down")) {
            batch.draw(AssetLoader.isaacFrontAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.facing.equals("left")) {
            batch.draw(AssetLoader.isaacRightAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.facing.equals("right")) {
            batch.draw(AssetLoader.isaacRightAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.facing.equals("upLeft")) {
            batch.draw(AssetLoader.isaacUpRightAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.facing.equals("upRight")) {
            batch.draw(AssetLoader.isaacUpRightAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.facing.equals("downLeft")) {
            batch.draw(AssetLoader.isaacDownRightAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.facing.equals("downRight")) {
            batch.draw(AssetLoader.isaacDownRightAni.getKeyFrame(runTime / 6),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
    }

    public void playWalk(float runTime, Isaac isaac) {
        if (isaac.isUp()) {
            batch.draw(AssetLoader.walkUpAni.getKeyFrame(runTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDown()) {
            batch.draw(AssetLoader.walkDownAni.getKeyFrame(runTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isLeft()) {
            batch.draw(AssetLoader.walkRightAni.getKeyFrame(runTime / 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isRight()) {
            batch.draw(AssetLoader.walkRightAni.getKeyFrame(runTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isUpLeft()) {
            batch.draw(AssetLoader.walkUpRightAni.getKeyFrame(runTime / 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDownLeft()) {
            batch.draw(AssetLoader.walkDownRightAni.getKeyFrame(runTime / 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isUpRight()) {
            batch.draw(AssetLoader.walkUpRightAni.getKeyFrame(runTime / 2.5f),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDownRight()) {
            batch.draw(AssetLoader.walkDownRightAni.getKeyFrame(runTime / 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
    }

    public void playRun(float runTime, Isaac isaac) {
        if (isaac.isUp()) {
            batch.draw(AssetLoader.runUpAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDown()) {
            batch.draw(AssetLoader.runDownAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isLeft()) {
            batch.draw(AssetLoader.runRightAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isRight()) {
            batch.draw(AssetLoader.runRightAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isUpLeft()) {
            batch.draw(AssetLoader.runUpRightAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDownLeft()) {
            batch.draw(AssetLoader.runDownRightAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isUpRight()) {
            batch.draw(AssetLoader.runUpRightAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDownRight()) {
            batch.draw(AssetLoader.runDownRightAni.getKeyFrame(runTime / 1.5f),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
    }

    public void playSlide(float runTime, Isaac isaac) {
        if (isaac.isUp()) {
            batch.draw(AssetLoader.walkUpAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDown()) {
            batch.draw(AssetLoader.walkDownAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isLeft()) {
            batch.draw(AssetLoader.walkRightAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isRight()) {
            batch.draw(AssetLoader.walkRightAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isUpLeft()) {
            batch.draw(AssetLoader.walkUpRightAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDownLeft()) {
            batch.draw(AssetLoader.walkDownRightAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x + isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, -isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isUpRight()) {
            batch.draw(AssetLoader.walkUpRightAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
        if (isaac.isDownRight()) {
            batch.draw(AssetLoader.walkDownRightAni.getKeyFrame(runTime * 2),
                    isaac.b2body.getPosition().x - isaac.getWidth() / 2, isaac.b2body.getPosition().y - isaac.getHeight() / 5, isaac.getWidth(), isaac.getHeight());
        }
    }
}
