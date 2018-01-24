package helpers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import gameobjects.InteractiveTileObject;

/**
 * Created by Hareesh on 3/25/2016.
 */
public class WorldContactListener implements ContactListener {

    GameRenderer renderer;

    public WorldContactListener(GameRenderer renderer){
        this.renderer = renderer;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "coll" || fixB.getUserData() == "coll"){
            Fixture isaac = fixA.getUserData() == "coll" ? fixA : fixB;
            Fixture object = isaac == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject){
                ((InteractiveTileObject) object.getUserData()).onHit(renderer);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "coll" || fixB.getUserData() == "coll"){
            Fixture isaac = fixA.getUserData() == "coll" ? fixA : fixB;
            Fixture object = isaac == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject){
                ((InteractiveTileObject) object.getUserData()).onEndHit(renderer);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
