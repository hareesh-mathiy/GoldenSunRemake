package transitions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by Hareesh on 3/27/2016.
 */
public class OverlayAccessor implements TweenAccessor<Sprite> {

    public static final int ALPHA = 0;
    public static final int COLOR = 1;
    public static final int Y = 2;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch(tweenType) {
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            case COLOR:
                returnValues[1] = target.getColor().a;
                return 1;
            case Y:
                returnValues[0] = target.getY();
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch(tweenType){
            case ALPHA:
                target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
                break;
            case COLOR:
                target.setColor(MathUtils.random(255), MathUtils.random(255), MathUtils.random(255), 1f);
                break;
            case Y:
                target.setY(newValues[0]);
                break;
            default:
                assert false;
        }
    }
}