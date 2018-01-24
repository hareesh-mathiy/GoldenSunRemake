package gamelocations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import gameobjects.HeightChange;
import gameobjects.Ice;
import gameobjects.Isaac;
import gameobjects.Ladder;
import gameobjects.UpperWall;
import gameobjects.Wall;
import gameobjects.Warp;
import helpers.Animations;
import helpers.GameRenderer;
import helpers.InputHandler;
import screens.GameScreen;
import transitions.OverlayAccessor;

/**
 * Created by Hareesh on 2016-03-23.
 */
public class LevelLoader {
    int[] image = {0};
    int[] ground = {1};
    int[] below = {2,3};
    int[] over = {5,6};
    int[] walkable = {7};
    int[] overWalkable = {8};
    TiledMapRenderer tiledMapRenderer;
    TiledMap tiledMap;
    SpriteBatch batch;
    Isaac isaac;
    OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private World world;
    private Array<Body> bodies, upperBodies;
    private TweenManager tweenManager, tweenManager2, tweenManager3;
    private Sprite overlay, overlay2, overlay3;
    private Boolean isEntering = false;
    private Animations ani;
    String screenVertexShader;
    String screenFragmentShader;
    ShaderProgram shader2;
    Vector3 pos;

    public LevelLoader(TiledMap tiledMap, GameRenderer r){
        this.tiledMap = tiledMap;
        batch = r.getBatch();
        world = r.getWorld();
        isaac = r.getIsaac();
        camera = r.getCam();
        pos = new Vector3(0, camera.position.y/2, 0);

        ani = new Animations(batch, isaac, r);
        tweenManager = new TweenManager();
        tweenManager2 = new TweenManager();
        tweenManager3 = new TweenManager();
        Texture ov = new Texture(Gdx.files.internal("data/GoldenSun/sprites/overlay.png"));
        overlay = new Sprite(ov);
        overlay.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        overlay3 = new Sprite(ov);
        overlay3.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
        overlay3.setAlpha(0.7f);
        Tween.registerAccessor(Sprite.class, new OverlayAccessor());
        Tween.set(overlay, OverlayAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(overlay, OverlayAccessor.ALPHA, 0.5f).target(1).start(tweenManager);
        Tween.set(overlay, OverlayAccessor.ALPHA).target(1).start(tweenManager2);
        Tween.to(overlay, OverlayAccessor.ALPHA, 0.5f).target(0).start(tweenManager2);

        Texture ov2 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/overlay2.png"));
        overlay2 = new Sprite(ov2);
        overlay2.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        overlay2.setAlpha(0.7f);
        screenVertexShader = Gdx.files.internal("data/shaders/psyVert.glsl").readString();
        screenFragmentShader = Gdx.files.internal("data/shaders/psyFrag.glsl").readString();
        ShaderProgram.pedantic = false;
        shader2 = new ShaderProgram(screenVertexShader,screenFragmentShader);
        System.out.println(shader2.isCompiled() ? "Shader2 Compiled" : shader2.getLog());

        bodies = new Array<Body>();
        upperBodies = new Array<Body>();
        Gdx.app.log("LL", "creating tiledMapRenderer");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 0.30f);
        Gdx.app.log("LL", "created tiledMapRenderer");
        b2dr = new Box2DDebugRenderer();
        createObjects();
    }

    public void render(float runTime){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        renderImage();
        renderGround();
        renderBelow();
        if(ani.psyActive){
            renderOver();
            renderWalkable();
            renderOverWalkable();
            batch.begin();
            batch.setColor(InputHandler.getColor());
            batch.draw(overlay2, -50, -50, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            batch.setColor(1, 1, 1, 1f);
            ani.run(runTime);

        }
        else{
            batch.setColor(1,1,1,1f);
            if(isaac.getHPos() == 1){
                renderWalkable();
                ani.run(runTime);
                renderOver();
                renderOverWalkable();
            }
            else if(isaac.getHPos() == 0 || isaac.getHPos() == -1){
                ani.run(runTime);
                renderOver();
                renderWalkable();
                renderOverWalkable();
            }
        }
        batch.begin();
        overlay.draw(batch);
        batch.end();
        b2dr.render(world, camera.combined);
    }


    public void renderImage(){
        tiledMapRenderer.render(image);
    }

    public void renderGround(){
        tiledMapRenderer.render(ground);
    }

    public void renderBelow() {
        tiledMapRenderer.render(below);
    }

    public void renderOver() {
        tiledMapRenderer.render(over);
    }

    public void renderWalkable() {
        tiledMapRenderer.render(walkable);
    }

    public void renderOverWalkable() {
        tiledMapRenderer.render(overWalkable);
    }

    public void drawUpper() {
        for(MapObject object : tiledMap.getLayers().get(13).getObjects()) {
            Gdx.app.log("LL", "creating object");
            if (object instanceof TextureMapObject) {
                continue;
            }
            Shape shape;

            if (object instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject)object);
            }
            else if (object instanceof PolygonMapObject) {
                shape = getPolygon((PolygonMapObject)object);
            }
            else if (object instanceof PolylineMapObject) {
                shape = getPolyline((PolylineMapObject)object);
            }
            else if (object instanceof CircleMapObject) {
                shape = getCircle((CircleMapObject)object);
            }
            else {
                continue;
            }

            new UpperWall(world, tiledMap, shape, this, object);

            isaac.setHC(false);
        }
    }


    public void createObjects(){
        Gdx.app.log("LL", "creating object");

        //Load boundaries
        for(MapObject object : tiledMap.getLayers().get(4).getObjects()) {
            if (object instanceof TextureMapObject) {
                continue;
            }
            Shape shape;

            if (object instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject)object);
            }
            else if (object instanceof PolygonMapObject) {
                shape = getPolygon((PolygonMapObject)object);
            }
            else if (object instanceof PolylineMapObject) {
                shape = getPolyline((PolylineMapObject)object);
            }
            else if (object instanceof CircleMapObject) {
                shape = getCircle((CircleMapObject)object);
            }
            else {
                continue;
            }


            new Wall(world, tiledMap, shape, this, object);
        }

        //Load Warp
        for(MapObject object : tiledMap.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Warp(world, tiledMap, rect, this, object);
        }

        //Load Ice
        for(MapObject object : tiledMap.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Ice(world, tiledMap, rect, this, object);
        }

        //Load Height Change
        for(MapObject object : tiledMap.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new HeightChange(world, tiledMap, rect, this, object);
        }

        //Load Ladder
        for(MapObject object : tiledMap.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Ladder(world, tiledMap, rect, this, object);
        }

    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x * 0.30f + rectangle.width * 0.5f * 0.30f),
                (rectangle.y * 0.30f + rectangle.height * 0.5f  * 0.30f));
        polygon.setAsBox(rectangle.width * 0.5f * 0.30f,
                rectangle.height * 0.5f * 0.30f,
                size,
                0.0f);
        return polygon;
    }

    private static CircleShape getCircle(CircleMapObject circleObject) {
        Circle circle = circleObject.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius);
        circleShape.setPosition(new Vector2(circle.x * 0.30f, circle.y * 0.30f));
        return circleShape;
    }

    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i];
        }

        polygon.set(worldVertices);
        return polygon;
    }

    private static ChainShape getPolyline(PolylineMapObject polylineObject) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2]*0.30f;
            worldVertices[i].y = vertices[i * 2 + 1]*0.30f;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }

    public World getWorld(){
        return world;
    }

    public Array<Body> getBodies() { return bodies; }

    public Array<Body> getUpperBodies() { return upperBodies; }

    public void setEntering(Boolean ent){ isEntering = ent; }

    public TweenManager getTM() { return tweenManager; }
    public TweenManager getTM2() { return tweenManager2; }
    public TweenManager getTM3() { return tweenManager3; }

    public Boolean getEntering() { return isEntering; }

    public Sprite getOverlay() { return overlay; }

    public int getHeightChange(){
        return isaac.getHPos();
    }
}
