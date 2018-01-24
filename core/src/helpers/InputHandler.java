package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import gameobjects.Isaac;

/**
 * Created by Hareesh on 2016-03-20.
 */
public class InputHandler {

    private Isaac isaac;
    boolean activeButtonTouch = false;
    private float isaacSpeed = 35;
    private Touchpad touchpad;
    private Button button1, button2;
    private World world;
    private GameRenderer renderer;
    private FixtureDef fdef = new FixtureDef();
    private EdgeShape coll = new EdgeShape();
    public Color randcolor;
    public static Color color;

    public InputHandler(GameRenderer renderer) {
        this.renderer = renderer;
        isaac = renderer.getIsaac();
        world = renderer.getWorld();
        color = new Color(1, 1, 1, 0.6f);
    }

    public void updateAndroid(final float delta) {
        if(!renderer.getWorld().isLocked()) {
            touchpad = renderer.getTouchpad();
            button1 = renderer.getButton1();
            button2 = renderer.getButton2();
            //Gdx.app.log("GW", "X " + Float.toString(isaac.b2body.getPosition().x) + " | " + "Y " + Float.toString(isaac.b2body.getPosition().y));

            if (!isaac.disableMovement) {
                if (button1.isPressed() && !isaac.isSliding) {
                    isaacSpeed = 55;
                    isaac.setRunning(true);
                } else {
                    isaac.setRunning(false);
                    isaac.setNotMoving(true);
                    activeButtonTouch = false;
                    isaacSpeed = 35;
                }
                if (button2.isPressed()) {
                    color = getRandColor();
                    isaac.isPsy = true;
                }
                isaac.setNotMoving(true);
                isaac.b2body.setLinearVelocity(0, 0);

                if (isaac.onIce) {
                    if (isaac.colliding) {
                        isaac.isSliding = false;
                    } else
                        isaac.isSliding = true;
                    if (!isaac.isSliding) {
                        if ((touchpad.getKnobY() > 75 && (touchpad.getKnobX() >= 55 && touchpad.getKnobX() <= 95)) && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                            coll.set(new Vector2(-0.5f, 4.5f), new Vector2(0.5f, 4.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setUp(true);
                            isaac.setFacing("up");
                            isaac.b2body.setLinearVelocity(new Vector2(0, isaacSpeed));

                        }
                        if ((touchpad.getKnobY() < 75 && (touchpad.getKnobX() >= 55 && touchpad.getKnobX() <= 95)) && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                            coll.set(new Vector2(-0.5f, -4.5f), new Vector2(0.5f, -4.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setDown(true);
                            isaac.setFacing("down");
                            isaac.b2body.setLinearVelocity(new Vector2(0, -isaacSpeed));

                        }
                        if ((touchpad.getKnobX() < 75 && (touchpad.getKnobY() >= 55 && touchpad.getKnobY() <= 95)) && isaac.b2body.getLinearVelocity().x >= -isaacSpeed) {
                            coll.set(new Vector2(-4.5f, 0.5f), new Vector2(-4.5f, -0.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setLeft(true);
                            isaac.setFacing("left");
                            isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, 0));

                        }
                        if ((touchpad.getKnobX() > 75 && (touchpad.getKnobY() >= 55 && touchpad.getKnobY() <= 95)) && isaac.b2body.getLinearVelocity().x <= isaacSpeed) {
                            coll.set(new Vector2(4.5f, 0.5f), new Vector2(4.5f, -0.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setRight(true);
                            isaac.setFacing("right");
                            isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, 0));

                        }
                    }
                    if (isaac.isSliding) {
                        if (isaac.getFacing().equals("left"))
                            isaac.b2body.setLinearVelocity(new Vector2(2 * -isaacSpeed, 0));
                        if (isaac.getFacing().equals("right"))
                            isaac.b2body.setLinearVelocity(new Vector2(2 * isaacSpeed, 0));
                        if (isaac.getFacing().equals("up"))
                            isaac.b2body.setLinearVelocity(new Vector2(0, 2 * isaacSpeed));
                        if (isaac.getFacing().equals("down"))
                            isaac.b2body.setLinearVelocity(new Vector2(0, 2 * -isaacSpeed));
                    }
                } else {
                    if (touchpad.getKnobY() > 75 && (touchpad.getKnobX() >= 55 && touchpad.getKnobX() <= 95) && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                        coll.set(new Vector2(-0.5f, 4.5f), new Vector2(0.5f, 4.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setUp(true);
                        isaac.setFacing("up");
                        isaac.b2body.setLinearVelocity(new Vector2(0, isaacSpeed));

                    }
                    if (touchpad.getKnobY() < 75 && (touchpad.getKnobX() >= 55 && touchpad.getKnobX() <= 95) && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                        coll.set(new Vector2(-0.5f, -4.5f), new Vector2(0.5f, -4.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setDown(true);
                        isaac.setFacing("down");
                        isaac.b2body.setLinearVelocity(new Vector2(0, -isaacSpeed));

                    }
                    if (touchpad.getKnobX() < 75 && (touchpad.getKnobY() >= 55 && touchpad.getKnobY() <= 95) && isaac.b2body.getLinearVelocity().x >= -isaacSpeed) {
                        coll.set(new Vector2(-4.5f, 0.5f), new Vector2(-4.5f, -0.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setLeft(true);
                        isaac.setFacing("left");
                        isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, 0));

                    }
                    if (touchpad.getKnobX() > 75 && (touchpad.getKnobY() >= 55 && touchpad.getKnobY() <= 95) && isaac.b2body.getLinearVelocity().x <= isaacSpeed) {
                        coll.set(new Vector2(4.5f, 0.5f), new Vector2(4.5f, -0.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setRight(true);
                        isaac.setFacing("right");
                        isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, 0));

                    }
                    if ((touchpad.getKnobX() < 55 && touchpad.getKnobY() > 95) && isaac.b2body.getLinearVelocity().x >= -isaacSpeed && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                        coll.set(new Vector2(-3.5f, 2.5f), new Vector2(-2.5f, 3.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setUpLeft(true);
                        isaac.setFacing("upLeft");
                        isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, isaacSpeed));
                    }
                    if (touchpad.getKnobX() < 55 && touchpad.getKnobY() < 55 && isaac.b2body.getLinearVelocity().x >= -isaacSpeed && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                        coll.set(new Vector2(-3.5f, -2.5f), new Vector2(-2.5f, -3.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setDownLeft(true);
                        isaac.setFacing("downLeft");
                        isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, -isaacSpeed));
                    }
                    if (touchpad.getKnobX() > 95 && touchpad.getKnobY() > 95 && isaac.b2body.getLinearVelocity().x <= isaacSpeed && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                        coll.set(new Vector2(3.5f, 2.5f), new Vector2(2.5f, 3.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setUpRight(true);
                        isaac.setFacing("upRight");
                        isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, isaacSpeed));
                    }
                    if (touchpad.getKnobX() > 95 && touchpad.getKnobY() < 55 && isaac.b2body.getLinearVelocity().x <= isaacSpeed && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                        coll.set(new Vector2(3.5f, -2.5f), new Vector2(2.5f, -3.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setDownRight(true);
                        isaac.setFacing("downRight");
                        isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, -isaacSpeed));
                    }

                }
            }
            if (isaac.isSliding) {
                if (isaac.getFacing().equals("left") || isaac.getFacing().equals("upLeft") || isaac.getFacing().equals("downLeft")) {
                    coll.set(new Vector2(-4.5f, 0.5f), new Vector2(-4.5f, -0.5f));
                    fdef.shape = coll;
                    fdef.isSensor = false;
                    isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                    isaac.b2body.createFixture(fdef).setUserData("coll");

                }
                if (isaac.getFacing().equals("right") || isaac.getFacing().equals("upRight") || isaac.getFacing().equals("downRight")) {
                    coll.set(new Vector2(4.5f, 0.5f), new Vector2(4.5f, -0.5f));
                    fdef.shape = coll;
                    fdef.isSensor = false;
                    isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                    isaac.b2body.createFixture(fdef).setUserData("coll");

                }
            }
        }
    }

    public void updateDesktop(final float delta) {
        if(!renderer.getWorld().isLocked()) {
            if (!isaac.disableMovement) {
                //Gdx.app.log("GW", "X " + Float.toString(isaac.b2body.getPosition().x) + " | " + "Y " + Float.toString(isaac.b2body.getPosition().y));
                if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && !isaac.isSliding) {
                    isaacSpeed = 55;
                    isaac.setRunning(true);
                } else {
                    isaac.setRunning(false);
                    isaac.setNotMoving(true);
                    activeButtonTouch = false;
                    isaacSpeed = 35;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    color = getRandColor();
                    isaac.isPsy = true;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    Gdx.app.log("IH", "s");
                }
                isaac.setNotMoving(true);
                isaac.b2body.setLinearVelocity(0, 0);

                if (isaac.onIce) {
                    if (isaac.colliding) {
                        isaac.isSliding = false;
                    } else
                        isaac.isSliding = true;
                    if (!isaac.isSliding) {
                        if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.I)) && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                            coll.set(new Vector2(-0.5f, 4.5f), new Vector2(0.5f, 4.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setUp(true);
                            isaac.setFacing("up");
                            isaac.b2body.setLinearVelocity(new Vector2(0, isaacSpeed));

                        }
                        if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.K)) && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                            coll.set(new Vector2(-0.5f, -4.5f), new Vector2(0.5f, -4.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setDown(true);
                            isaac.setFacing("down");
                            isaac.b2body.setLinearVelocity(new Vector2(0, -isaacSpeed));

                        }
                        if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.J)) && isaac.b2body.getLinearVelocity().x >= -isaacSpeed) {
                            coll.set(new Vector2(-4.5f, 0.5f), new Vector2(-4.5f, -0.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setLeft(true);
                            isaac.setFacing("left");
                            isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, 0));

                        }
                        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.L)) && isaac.b2body.getLinearVelocity().x <= isaacSpeed) {
                            coll.set(new Vector2(4.5f, 0.5f), new Vector2(4.5f, -0.5f));
                            fdef.shape = coll;
                            fdef.isSensor = false;
                            isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                            isaac.b2body.createFixture(fdef).setUserData("coll");

                            isaac.setNotMoving(false);
                            isaac.setRight(true);
                            isaac.setFacing("right");
                            isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, 0));

                        }
                    }
                    if (isaac.isSliding) {
                        if (isaac.getFacing().equals("left"))
                            isaac.b2body.setLinearVelocity(new Vector2(2 * -isaacSpeed, 0));
                        if (isaac.getFacing().equals("right"))
                            isaac.b2body.setLinearVelocity(new Vector2(2 * isaacSpeed, 0));
                        if (isaac.getFacing().equals("up"))
                            isaac.b2body.setLinearVelocity(new Vector2(0, 2 * isaacSpeed));
                        if (isaac.getFacing().equals("down"))
                            isaac.b2body.setLinearVelocity(new Vector2(0, 2 * -isaacSpeed));
                    }
                } else {
                    if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.I)) && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                        coll.set(new Vector2(-0.5f, 4.5f), new Vector2(0.5f, 4.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setUp(true);
                        isaac.setFacing("up");
                        isaac.b2body.setLinearVelocity(new Vector2(0, isaacSpeed));

                    }
                    if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.K)) && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                        coll.set(new Vector2(-0.5f, -4.5f), new Vector2(0.5f, -4.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setDown(true);
                        isaac.setFacing("down");
                        isaac.b2body.setLinearVelocity(new Vector2(0, -isaacSpeed));

                    }
                    if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.J)) && isaac.b2body.getLinearVelocity().x >= -isaacSpeed) {
                        coll.set(new Vector2(-4.5f, 0.5f), new Vector2(-4.5f, -0.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setLeft(true);
                        isaac.setFacing("left");
                        isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, 0));

                    }
                    if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.L)) && isaac.b2body.getLinearVelocity().x <= isaacSpeed) {
                        coll.set(new Vector2(4.5f, 0.5f), new Vector2(4.5f, -0.5f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setRight(true);
                        isaac.setFacing("right");
                        isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, 0));

                    }
                    if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.I)) && (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.J)) && isaac.b2body.getLinearVelocity().x >= -isaacSpeed && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                        coll.set(new Vector2(-4f, 3.0f), new Vector2(-3.0f, 4f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setUpLeft(true);
                        isaac.setFacing("upLeft");
                        isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, isaacSpeed));
                    }
                    if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.K)) && (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.J)) && isaac.b2body.getLinearVelocity().x >= -isaacSpeed && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                        coll.set(new Vector2(-4f, -3.0f), new Vector2(-3.0f, -4f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setDownLeft(true);
                        isaac.setFacing("downLeft");
                        isaac.b2body.setLinearVelocity(new Vector2(-isaacSpeed, -isaacSpeed));
                    }
                    if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.I)) && (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.L)) && isaac.b2body.getLinearVelocity().x <= isaacSpeed && isaac.b2body.getLinearVelocity().y <= isaacSpeed) {
                        coll.set(new Vector2(4f, 3.0f), new Vector2(3.0f, 4f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setUpRight(true);
                        isaac.setFacing("upRight");
                        isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, isaacSpeed));
                    }
                    if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.K)) && (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.L)) && isaac.b2body.getLinearVelocity().x <= isaacSpeed && isaac.b2body.getLinearVelocity().y >= -isaacSpeed) {
                        coll.set(new Vector2(4f, -3.0f), new Vector2(3.0f, -4f));
                        fdef.shape = coll;
                        fdef.isSensor = false;
                        isaac.b2body.destroyFixture(isaac.b2body.getFixtureList().get(1));
                        isaac.b2body.createFixture(fdef).setUserData("coll");

                        isaac.setNotMoving(false);
                        isaac.setDownRight(true);
                        isaac.setFacing("downRight");
                        isaac.b2body.setLinearVelocity(new Vector2(isaacSpeed, -isaacSpeed));
                    }
                }
            }
        }
    }

    public void updateOtherPlayers(float delta, Isaac otherPlayer){
        if(!renderer.getWorld().isLocked()) {
            if ((otherPlayer.b2body.getLinearVelocity().x <= -55 || otherPlayer.b2body.getLinearVelocity().x >= 55 ||
                    otherPlayer.b2body.getLinearVelocity().y <= -55 || otherPlayer.b2body.getLinearVelocity().y >= 55) && !otherPlayer.isSliding) {
                otherPlayer.setRunning(true);
            } else {
                otherPlayer.setRunning(false);
                otherPlayer.setNotMoving(true);
            }
            if (otherPlayer.b2body.getLinearVelocity().x < 0 && otherPlayer.b2body.getLinearVelocity().y > 0) {
                otherPlayer.setNotMoving(false);
                otherPlayer.setUpLeft(true);
                otherPlayer.setFacing("upLeft");
            }
            if (otherPlayer.b2body.getLinearVelocity().x < 0 && otherPlayer.b2body.getLinearVelocity().y < 0) {
                otherPlayer.setNotMoving(false);
                otherPlayer.setDownLeft(true);
                otherPlayer.setFacing("downLeft");
            }
            if (otherPlayer.b2body.getLinearVelocity().x > 0 && otherPlayer.b2body.getLinearVelocity().y < 0) {
                otherPlayer.setNotMoving(false);
                otherPlayer.setDownRight(true);
                otherPlayer.setFacing("downRight");
            }
            if (otherPlayer.b2body.getLinearVelocity().x > 0 && otherPlayer.b2body.getLinearVelocity().y > 0) {
                otherPlayer.setNotMoving(false);
                otherPlayer.setUpRight(true);
                otherPlayer.setFacing("upRight");
            }
            if (otherPlayer.b2body.getLinearVelocity().x == 0 && otherPlayer.b2body.getLinearVelocity().y < 0) {
                otherPlayer.setFacing("down");
                otherPlayer.setNotMoving(false);
                otherPlayer.setDown(true);
            }
            if (otherPlayer.b2body.getLinearVelocity().x == 0 && otherPlayer.b2body.getLinearVelocity().y > 0) {
                otherPlayer.setFacing("up");
                otherPlayer.setNotMoving(false);
                otherPlayer.setUp(true);
            }
            if (otherPlayer.b2body.getLinearVelocity().x > 0 && otherPlayer.b2body.getLinearVelocity().y == 0) {
                otherPlayer.setFacing("right");
                otherPlayer.setNotMoving(false);
                otherPlayer.setRight(true);
            }
            if (otherPlayer.b2body.getLinearVelocity().x < 0 && otherPlayer.b2body.getLinearVelocity().y == 0) {
                otherPlayer.setFacing("left");
                otherPlayer.setNotMoving(false);
                otherPlayer.setLeft(true);
            }
            if (otherPlayer.b2body.getLinearVelocity().x == 0 && otherPlayer.b2body.getLinearVelocity().y == 0) {
                otherPlayer.setNotMoving(true);
            }
        }
    }

    public Color getRandColor() {
        Color defc = new Color(1, 1, 1, 1f);
        randcolor = new Color(1, 1, 1, 0.6f);
        int rand = MathUtils.random(8);
        switch (rand) {
            case 1:
                randcolor.set(Color.GREEN.r, Color.GREEN.g, Color.GREEN.b, 0.6f);
                break;
            case 2:
                randcolor.set(Color.RED.r, Color.RED.g, Color.RED.b, 0.6f);
                break;
            case 3:
                randcolor.set(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b, 0.6f);
                break;
            case 4:
                randcolor.set(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, 0.6f);
                break;
            case 5:
                randcolor.set(Color.GRAY.r, Color.GRAY.g, Color.GRAY.b, 0.6f);
                break;
            case 6:
                randcolor.set(Color.CYAN.r, Color.CYAN.g, Color.CYAN.b, 0.6f);
                break;
            case 7:
                randcolor.set(Color.MAGENTA.r, Color.MAGENTA.g, Color.MAGENTA.b, 0.6f);
                break;
            case 8:
                randcolor.set(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, 0.6f);
                break;
        }
        return randcolor;
    }

    public static Color getColor() {
        return color;
    }
}
