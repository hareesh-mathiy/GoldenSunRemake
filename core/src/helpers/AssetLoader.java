package helpers;

/**
 * Created by Hareesh on 3/11/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture isaac1, isaac11, isaac2, isaac21, isaac3, isaac31, alex1, alex2,
            move;

    public static Animation isaacFrontAni, isaacBackAni, isaacRightAni, isaacUpRightAni, isaacDownRightAni,
            walkDownAni, walkUpAni, walkRightAni, walkUpRightAni, walkDownRightAni,
            runDownAni, runUpAni, runRightAni, runUpRightAni, runDownRightAni,
            isaacTurnDownUpAni, isaacTauntAni,
            isaacSlidingUpAni, isaacSlidingDownAni, isaacSlidingLeftAni, isaacSlidingRightAni,
            isaacSlidingUpLeftAni, isaacSlidingUpRightAni, isaacSlidingDownLeftAni, isaacSlidingDownRightAni,
            isaacPsyDRAni, isaacPsyRAni, isaacPsyURAni, isaacPsyUAni, isaacPsyDAni,
            moveUpAni, moveRightAni, moveDownAni, moveDisappearAni,

    alexFrontAni, alexBackAni, alexRightAni, alexUpRightAni, alexDownRightAni,
            alexWalkDownAni, alexWalkUpAni, alexWalkRightAni, alexWalkUpRightAni, alexWalkDownRightAni,
            alexRunDownAni, alexRunUpAni, alexRunRightAni, alexRunUpRightAni, alexRunDownRightAni;


    public static TextureRegion isaac01, isaac02, isaac03, isaac4, isaac5, isaac6, isaac7, isaac8, isaac9, isaac10,
            isaac011, isaac12, isaac13, isaac14, isaac15, isaac16, isaac17, isaac18, isaac19, isaac20,
            isaac021, isaac22, isaac23, isaac24, isaac25, isaac26, isaac27, isaac28, isaac29, isaac30,
            isaac031, isaac32, isaac33, isaac34, isaac35, isaac36, isaac37, isaac38, isaac39, isaac40,
            isaac41, isaac42, isaac43, isaac44, isaac45, isaac46, isaac47, isaac48, isaac49, isaac50,
            isaac51, isaac52, isaac53, isaac54, isaac55, isaac56, isaac57, isaac58, isaac59, isaac60,
            isaac61, isaac62, isaac63, isaac64, isaac65, isaac66, isaac67, isaac68, isaac69, isaac70,
            isaac71, isaac72, isaac73, isaac74, isaac75, isaac76, isaac77, isaac78, isaac79, isaac80,
            isaac81, isaac82, isaac83, isaac84, isaac85, isaac86, isaac87, isaac88, isaac89, isaac90,
            isaac91, isaac92, isaac93, isaac94, isaac95, isaac96, isaac97, isaac98, isaac99, isaac100,
            isaac101, isaac102, isaac103, isaac104, isaac105, isaac106, isaac107, isaac108, isaac109, isaac110,
            isaac111, isaac112, isaac113, isaac114, isaac115, isaac116, isaac117, isaac118, isaac119, isaac120,
            isaac121, isaac122, isaac123, isaac124, isaac125, isaac126, isaac127, isaac128, isaac129, isaac130,
            isaac131, isaac132, isaac133, isaac134, isaac135, isaac136, isaac137, isaac138, isaac139, isaac140,
            isaac141, isaac142, isaac143, isaac144, isaac145, isaac146, isaac147, isaac148, isaac149, isaac150,
            isaac151, isaac152, isaac153, isaac154, isaac155, isaac156, isaac157, isaac158, isaac159, isaac160,
            isaac161, isaac162, isaac163, isaac164, isaac165, isaac166, isaac167, isaac168, isaac169, isaac170,
            isaac171, isaac172, isaac173, isaac174, isaac175, isaac176, isaac177, isaac178, isaac179, isaac180,
            isaac181, isaac182, isaac183, isaac184, isaac185, isaac186, isaac187, isaac188, isaac189, isaac190,
            isaac191, isaac192, isaac193, isaac194, isaac195, isaac196, isaac197, isaac198, isaac199, isaac200,
            isaac201, isaac202, isaac203, isaac204, isaac205, isaac206, isaac207, isaac208, isaac209, isaac210,

    alex01, alex02, alex3, alex4, alex5, alex6, alex7, alex8, alex9, alex10,
            alex11, alex12, alex13, alex14, alex15, alex16, alex17, alex18, alex19, alex20,
            alex21, alex22, alex23, alex24, alex25, alex26, alex27, alex28, alex29, alex30,
            alex31, alex32, alex33, alex34, alex35,

    move1, move2, move3, move4, move5, move6, move7;


    public static void load() {
        isaac1 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/isaac1.png"));
        isaac11 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/isaac1-1.png"));
        isaac2 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/isaac2.png"));
        isaac21 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/isaac2-1.png"));
        isaac3 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/isaac3.png"));
        isaac31 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/isaac3-1.png"));
        alex1 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/alex1.png"));
        alex2 = new Texture(Gdx.files.internal("data/GoldenSun/sprites/alex2.png"));
        move = new Texture(Gdx.files.internal("data/GoldenSun/psynergy/move.png"));

        isaac1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        isaac11.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        isaac2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        isaac21.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        isaac3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        isaac31.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        alex1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        alex2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        move.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        alex01 = new TextureRegion(alex1, 0,0,128,128);
        alex02 = new TextureRegion(alex1, 128,0,128,128);
        alex3 = new TextureRegion(alex1, 256,0,128,128);
        alex4 = new TextureRegion(alex1, 0,128,128,128);
        alex5 = new TextureRegion(alex1, 128,128,128,128);
        alex6 = new TextureRegion(alex1, 256,128,128,128);
        alex7 = new TextureRegion(alex1, 0,256,128,128);
        alex8 = new TextureRegion(alex1, 128,256,128,128);
        alex9 = new TextureRegion(alex1, 256,256,128,128);
        alex10 = new TextureRegion(alex1, 0,384,128,128);
        alex11 = new TextureRegion(alex1, 128,384,128,128);
        alex12 = new TextureRegion(alex1, 256,384,128,128);
        alex13 = new TextureRegion(alex1, 0,512,128,128);
        alex14 = new TextureRegion(alex1, 128,512,128,128);
        alex15 = new TextureRegion(alex1, 256,512,128,128);
        alex16 = new TextureRegion(alex1, 0,640,128,128);
        alex17 = new TextureRegion(alex1, 128,640,128,128);
        alex18 = new TextureRegion(alex1, 256,640,128,128);
        alex19 = new TextureRegion(alex2, 0,0,128,128);
        alex20 = new TextureRegion(alex2, 128,0,128,128);
        alex21 = new TextureRegion(alex2, 256,0,128,128);
        alex22 = new TextureRegion(alex2, 0,128,128,128);
        alex23 = new TextureRegion(alex2, 128,128,128,128);
        alex24 = new TextureRegion(alex2, 256,128,128,128);
        alex25 = new TextureRegion(alex2, 0,256,128,128);
        alex26 = new TextureRegion(alex2, 128,256,128,128);
        alex27 = new TextureRegion(alex2, 256,256,128,128);
        alex28 = new TextureRegion(alex2, 0,384,128,128);
        alex29 = new TextureRegion(alex2, 128,384,128,128);
        alex30 = new TextureRegion(alex2, 256,384,128,128);
        alex31 = new TextureRegion(alex2, 0,512,128,128);
        alex32 = new TextureRegion(alex2, 128,512,128,128);
        alex33 = new TextureRegion(alex2, 256,512,128,128);
        alex34 = new TextureRegion(alex2, 0,640,128,128);
        alex35 = new TextureRegion(alex2, 128,640,128,128);

        //--------------------Alex Standing---------------------------------
        TextureRegion[] alexRight = {alex3,alex3,alex3,alex6,alex3,alex3,alex3,alex6,alex3,alex3,alex12,alex12,alex12,alex12,alex12};
        alexRightAni = new Animation(0.06f, alexRight);
        alexRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] alexUpRight = {alex01,alex01,alex01,alex4,alex01,alex01,alex01,alex4,alex01,alex01,alex10,alex10,alex10,alex10,alex10};
        alexUpRightAni = new Animation(0.06f, alexUpRight);
        alexUpRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] alexDownRight = {alex02,alex02,alex02,alex5,alex02,alex02,alex02,alex5,alex02,alex02,alex11,alex11,alex11,alex11,alex11};
        alexDownRightAni = new Animation(0.06f, alexDownRight);
        alexDownRightAni.setPlayMode(Animation.PlayMode.LOOP);


        //--------------------Alex Walking---------------------------------
        TextureRegion[] alexWalkRight = {alex17, alex20, alex23, alex26, alex29, alex32};
        alexWalkRightAni = new Animation(0.06f, alexWalkRight);
        alexWalkRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] alexWalkUpRight = {alex15, alex18, alex21, alex24, alex27, alex30};
        alexWalkUpRightAni = new Animation(0.06f, alexWalkUpRight);
        alexWalkUpRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] alexWalkDownRight = {alex16, alex19, alex22, alex25, alex28, alex31};
        alexWalkDownRightAni = new Animation(0.06f, alexWalkDownRight);
        alexWalkDownRightAni.setPlayMode(Animation.PlayMode.LOOP);

        isaac01 = new TextureRegion(isaac1, 0,0,128,128);
        isaac02 = new TextureRegion(isaac1, 128,0,128,128);
        isaac03 = new TextureRegion(isaac1, 256,0,128,128);
        isaac4 = new TextureRegion(isaac1, 384,0,128,128);
        isaac5 = new TextureRegion(isaac1, 512,0,128,128);
        isaac6 = new TextureRegion(isaac1, 0,128,128,128);
        isaac7 = new TextureRegion(isaac1, 128,128,128,128);
        isaac8 = new TextureRegion(isaac1, 256,128,128,128);
        isaac9 = new TextureRegion(isaac1, 384,128,128,128);
        isaac10 = new TextureRegion(isaac1, 512,128,128,128);
        isaac011 = new TextureRegion(isaac1, 0,256,128,128);
        isaac12 = new TextureRegion(isaac1, 128,256,128,128);
        isaac13 = new TextureRegion(isaac1, 256,256,128,128);
        isaac14 = new TextureRegion(isaac1, 384,256,128,128);
        isaac15 = new TextureRegion(isaac1, 512,256,128,128);
        isaac16 = new TextureRegion(isaac1, 0,384,128,128);
        isaac17 = new TextureRegion(isaac1, 128,384,128,128);
        isaac18 = new TextureRegion(isaac1, 256,384,128,128);
        isaac19 = new TextureRegion(isaac1, 384,384,128,128);
        isaac20 = new TextureRegion(isaac1, 512,384,128,128);
        isaac021 = new TextureRegion(isaac1, 0,512,128,128);
        isaac22 = new TextureRegion(isaac1, 128,512,128,128);
        isaac23 = new TextureRegion(isaac1, 256,512,128,128);
        isaac24 = new TextureRegion(isaac1, 384,512,128,128);
        isaac25 = new TextureRegion(isaac1, 512,512,128,128);
        isaac26 = new TextureRegion(isaac1, 0,641,128,128);
        isaac27 = new TextureRegion(isaac1, 128,640,128,128);
        isaac28 = new TextureRegion(isaac1, 256,640,128,128);
        isaac29 = new TextureRegion(isaac1, 384,640,128,128);
        isaac30 = new TextureRegion(isaac1, 512,640,128,128);
        isaac031 = new TextureRegion(isaac1, 0,768,128,128);
        isaac32 = new TextureRegion(isaac1, 128,768,128,128);
        isaac33 = new TextureRegion(isaac1, 256,768,128,128);
        isaac34 = new TextureRegion(isaac1, 384,768,128,128);
        isaac35 = new TextureRegion(isaac1, 512,768,128,128);
        isaac36 = new TextureRegion(isaac1, 0,896,128,128);
        isaac37 = new TextureRegion(isaac1, 128,896,128,128);
        isaac38 = new TextureRegion(isaac1, 256,896,128,128);
        isaac39 = new TextureRegion(isaac1, 384,896,128,128);
        isaac40 = new TextureRegion(isaac1, 512,896,128,128);
        isaac41 = new TextureRegion(isaac11, 0,0,128,128);
        isaac42 = new TextureRegion(isaac11, 128,0,128,128);
        isaac43 = new TextureRegion(isaac11, 256,0,128,128);
        isaac44 = new TextureRegion(isaac11, 384,0,128,128);
        isaac45 = new TextureRegion(isaac11, 512,0,128,128);
        isaac46 = new TextureRegion(isaac11, 0,128,128,128);
        isaac47 = new TextureRegion(isaac11, 128,128,128,128);
        isaac48 = new TextureRegion(isaac11, 256,128,128,128);
        isaac49 = new TextureRegion(isaac11, 384,128,128,128);
        isaac50 = new TextureRegion(isaac11, 512,128,128,128);
        isaac51 = new TextureRegion(isaac11, 0,256,128,128);
        isaac52 = new TextureRegion(isaac11, 128,256,128,128);
        isaac53 = new TextureRegion(isaac11, 256,256,128,128);
        isaac54 = new TextureRegion(isaac11, 384,256,128,128);
        isaac55 = new TextureRegion(isaac11, 512,256,128,128);
        isaac56 = new TextureRegion(isaac11, 0,384,128,128);
        isaac57 = new TextureRegion(isaac11, 128,384,128,128);
        isaac58 = new TextureRegion(isaac11, 256,384,128,128);
        isaac59 = new TextureRegion(isaac11, 384,384,128,128);
        isaac60 = new TextureRegion(isaac11, 512,384,128,128);
        isaac61 = new TextureRegion(isaac11, 0,512,128,128);
        isaac62 = new TextureRegion(isaac11, 128,512,128,128);
        isaac63 = new TextureRegion(isaac11, 256,512,128,128);
        isaac64 = new TextureRegion(isaac11, 384,512,128,128);
        isaac65 = new TextureRegion(isaac11, 512,512,128,128);
        isaac66 = new TextureRegion(isaac11, 0,640,128,128);
        isaac67 = new TextureRegion(isaac11, 128,640,128,128);
        isaac68 = new TextureRegion(isaac11, 256,640,128,128);
        isaac69 = new TextureRegion(isaac11, 384,640,128,128);
        isaac70 = new TextureRegion(isaac11, 512,640,128,128);
        isaac71 = new TextureRegion(isaac11, 0,768,128,128);
        isaac72 = new TextureRegion(isaac11, 128,768,128,128);
        isaac73 = new TextureRegion(isaac11, 256,768,128,128);
        isaac74 = new TextureRegion(isaac11, 384,768,128,128);
        isaac75 = new TextureRegion(isaac11, 512,768,128,128);
        isaac76 = new TextureRegion(isaac11, 0,896,128,128);
        isaac77 = new TextureRegion(isaac11, 128,896,128,128);
        isaac78 = new TextureRegion(isaac11, 256,896,128,128);
        isaac79 = new TextureRegion(isaac11, 384,896,128,128);
        isaac80 = new TextureRegion(isaac11, 512,896,128,128);
        isaac81 = new TextureRegion(isaac2, 0,0,128,128);
        isaac82 = new TextureRegion(isaac2, 128,0,128,128);
        isaac83 = new TextureRegion(isaac2, 256,0,128,128);
        isaac84 = new TextureRegion(isaac2, 384,0,128,128);
        isaac85 = new TextureRegion(isaac2, 512,0,128,128);
        isaac86 = new TextureRegion(isaac2, 0,128,128,128);
        isaac87 = new TextureRegion(isaac2, 128,128,128,128);
        isaac88 = new TextureRegion(isaac2, 256,128,128,128);
        isaac89 = new TextureRegion(isaac2, 384,128,128,128);
        isaac90 = new TextureRegion(isaac2, 512,128,128,128);
        isaac91 = new TextureRegion(isaac2, 0,256,128,128);
        isaac92 = new TextureRegion(isaac2, 128,256,128,128);
        isaac93 = new TextureRegion(isaac2, 256,256,128,128);
        isaac94 = new TextureRegion(isaac2, 384,256,128,128);
        isaac95 = new TextureRegion(isaac2, 512,256,128,128);
        isaac96 = new TextureRegion(isaac2, 0,384,128,128);
        isaac97 = new TextureRegion(isaac2, 128,384,128,128);
        isaac98 = new TextureRegion(isaac2, 256,384,128,128);
        isaac99 = new TextureRegion(isaac2, 384,384,128,128);
        isaac100 = new TextureRegion(isaac2, 512,384,128,128);
        isaac101 = new TextureRegion(isaac2, 0,512,128,128);
        isaac102 = new TextureRegion(isaac2, 128,512,128,128);
        isaac103 = new TextureRegion(isaac2, 256,512,128,128);
        isaac104 = new TextureRegion(isaac2, 384,512,128,128);
        isaac105 = new TextureRegion(isaac2, 512,512,128,128);
        isaac106 = new TextureRegion(isaac2, 0,640,128,128);
        isaac107 = new TextureRegion(isaac2, 128,640,128,128);
        isaac108 = new TextureRegion(isaac2, 256,640,128,128);
        isaac109 = new TextureRegion(isaac2, 384,640,128,128);
        isaac110 = new TextureRegion(isaac2, 512,640,128,128);
        isaac111 = new TextureRegion(isaac2, 0,768,128,128);
        isaac112 = new TextureRegion(isaac2, 128,768,128,128);
        isaac113 = new TextureRegion(isaac2, 256,768,128,128);
        isaac114 = new TextureRegion(isaac2, 384,768,128,128);
        isaac115 = new TextureRegion(isaac2, 512,768,128,128);
        isaac116 = new TextureRegion(isaac2, 0,896,128,128);
        isaac117 = new TextureRegion(isaac2, 128,896,128,128);
        isaac118 = new TextureRegion(isaac2, 256,896,128,128);
        isaac119 = new TextureRegion(isaac2, 384,896,128,128);
        isaac120 = new TextureRegion(isaac2, 512,896,128,128);
        isaac121 = new TextureRegion(isaac21, 0,0,128,128);
        isaac122 = new TextureRegion(isaac21, 128,0,128,128);
        isaac123 = new TextureRegion(isaac21, 256,0,128,128);
        isaac124 = new TextureRegion(isaac21, 384,0,128,128);
        isaac125 = new TextureRegion(isaac21, 512,0,128,128);
        isaac126 = new TextureRegion(isaac21, 0,128,128,128);
        isaac127 = new TextureRegion(isaac21, 128,128,128,128);
        isaac128 = new TextureRegion(isaac21, 256,128,128,128);
        isaac129 = new TextureRegion(isaac21, 384,128,128,128);
        isaac130 = new TextureRegion(isaac21, 512,128,128,128);
        isaac131 = new TextureRegion(isaac21, 0,256,128,128);
        isaac132 = new TextureRegion(isaac21, 128,256,128,128);
        isaac133 = new TextureRegion(isaac21, 256,256,128,128);
        isaac134 = new TextureRegion(isaac21, 384,256,128,128);
        isaac135 = new TextureRegion(isaac21, 512,256,128,128);
        isaac136 = new TextureRegion(isaac21, 0,384,128,128);
        isaac137 = new TextureRegion(isaac21, 128,384,128,128);
        isaac138 = new TextureRegion(isaac21, 256,384,128,128);
        isaac139 = new TextureRegion(isaac21, 384,384,128,128);
        isaac140 = new TextureRegion(isaac21, 512,384,128,128);
        isaac141 = new TextureRegion(isaac21, 0,512,128,128);
        isaac142 = new TextureRegion(isaac21, 128,512,128,128);
        isaac143 = new TextureRegion(isaac21, 256,512,128,128);
        isaac144 = new TextureRegion(isaac21, 384,512,128,128);
        isaac145 = new TextureRegion(isaac21, 512,512,128,128);
        isaac146 = new TextureRegion(isaac21, 0,640,128,128);
        isaac147 = new TextureRegion(isaac21, 128,640,128,128);
        isaac148 = new TextureRegion(isaac21, 256,640,128,128);
        isaac149 = new TextureRegion(isaac21, 384,640,128,128);
        isaac150 = new TextureRegion(isaac21, 512,640,128,128);
        isaac151 = new TextureRegion(isaac21, 0,768,128,128);
        isaac152 = new TextureRegion(isaac21, 128,768,128,128);
        isaac153 = new TextureRegion(isaac21, 256,768,128,128);
        isaac154 = new TextureRegion(isaac21, 384,768,128,128);
        isaac155 = new TextureRegion(isaac21, 512,768,128,128);
        isaac156 = new TextureRegion(isaac21, 0,896,128,128);
        isaac157 = new TextureRegion(isaac21, 128,896,128,128);
        isaac158 = new TextureRegion(isaac21, 256,896,128,128);
        isaac159 = new TextureRegion(isaac21, 384,896,128,128);
        isaac160 = new TextureRegion(isaac21, 512,896,128,128);
        isaac161 = new TextureRegion(isaac3, 0,0,128,128);
        isaac162 = new TextureRegion(isaac3, 128,0,128,128);
        isaac163 = new TextureRegion(isaac3, 256,0,128,128);
        isaac164 = new TextureRegion(isaac3, 384,0,128,128);
        isaac165 = new TextureRegion(isaac3, 512,0,128,128);
        isaac166 = new TextureRegion(isaac3, 0,128,128,128);
        isaac167 = new TextureRegion(isaac3, 128,128,128,128);
        isaac168 = new TextureRegion(isaac3, 256,128,128,128);
        isaac169 = new TextureRegion(isaac3, 384,128,128,128);
        isaac170 = new TextureRegion(isaac3, 512,128,128,128);
        isaac171 = new TextureRegion(isaac3, 0,256,128,128);
        isaac172 = new TextureRegion(isaac3, 128,256,128,128);
        isaac173 = new TextureRegion(isaac3, 256,256,128,128);
        isaac174 = new TextureRegion(isaac3, 384,256,128,128);
        isaac175 = new TextureRegion(isaac3, 512,256,128,128);
        isaac176 = new TextureRegion(isaac3, 0,384,128,128);
        isaac177 = new TextureRegion(isaac3, 128,384,128,128);
        isaac178 = new TextureRegion(isaac3, 256,384,128,128);
        isaac179 = new TextureRegion(isaac3, 384,384,128,128);
        isaac180 = new TextureRegion(isaac3, 512,384,128,128);
        isaac181 = new TextureRegion(isaac3, 0,512,128,128);
        isaac182 = new TextureRegion(isaac3, 128,512,128,128);
        isaac183 = new TextureRegion(isaac3, 256,512,128,128);
        isaac184 = new TextureRegion(isaac3, 384,512,128,128);
        isaac185 = new TextureRegion(isaac3, 512,512,128,128);
        isaac186 = new TextureRegion(isaac3, 0,640,128,128);
        isaac187 = new TextureRegion(isaac3, 128,640,128,128);
        isaac188 = new TextureRegion(isaac3, 256,640,128,128);
        isaac189 = new TextureRegion(isaac3, 384,640,128,128);
        isaac190 = new TextureRegion(isaac3, 512,640,128,128);
        isaac191 = new TextureRegion(isaac3, 0,768,128,128);
        isaac192 = new TextureRegion(isaac3, 128,768,128,128);
        isaac193 = new TextureRegion(isaac3, 256,768,128,128);
        isaac194 = new TextureRegion(isaac3, 384,768,128,128);
        isaac195 = new TextureRegion(isaac3, 512,768,128,128);
        isaac196 = new TextureRegion(isaac3, 0,896,128,128);
        isaac197 = new TextureRegion(isaac3, 128,896,128,128);
        isaac198 = new TextureRegion(isaac3, 256,896,128,128);
        isaac199 = new TextureRegion(isaac3, 384,896,128,128);
        isaac200 = new TextureRegion(isaac3, 512,896,128,128);
        isaac201 = new TextureRegion(isaac31, 0,0,128,128);
        isaac202 = new TextureRegion(isaac31, 128,0,128,128);
        isaac203 = new TextureRegion(isaac31, 256,0,128,128);
        isaac204 = new TextureRegion(isaac31, 384,0,128,128);
        isaac205 = new TextureRegion(isaac31, 512,0,128,128);
        isaac206 = new TextureRegion(isaac31, 0,128,128,128);
        isaac207 = new TextureRegion(isaac31, 128,128,128,128);
        isaac208 = new TextureRegion(isaac31, 256,128,128,128);
        isaac209 = new TextureRegion(isaac31, 384,128,128,128);
        isaac210 = new TextureRegion(isaac31, 512,128,128,128);

        move1 = new TextureRegion(move, 0,0,128,128);
        move2 = new TextureRegion(move, 128,0,128,128);
        move3 = new TextureRegion(move, 0,128,128,128);
        move4 = new TextureRegion(move, 128,128,128,128);
        move5 = new TextureRegion(move, 0,256,128,128);
        move6 = new TextureRegion(move, 128,256,128,128);
        move7 = new TextureRegion(move, 0,0,128,128);
        move7.flip(true,false);

        //--------------------Move Ani---------------------------------
        TextureRegion[] moveup = {move5, move6};
        moveUpAni = new Animation(0.06f, moveup);
        moveUpAni.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        TextureRegion[] movedown = {move3, move4};
        moveDownAni = new Animation(0.06f, movedown);
        moveDownAni.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        TextureRegion[] moveright = {move1, move2};
        moveRightAni = new Animation(0.06f, moveright);
        moveRightAni.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        TextureRegion[] movedisappear = {move1, move7};
        moveDisappearAni = new Animation(0.06f, movedisappear);
        moveDisappearAni.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        //--------------------Taunting---------------------------------
        TextureRegion[] isaactaunt = {isaac200,isaac200,isaac199,isaac198,isaac198,isaac200,isaac201,isaac202,isaac202,isaac202,isaac202,isaac202,
                isaac202,isaac202,isaac202,isaac202,isaac202,isaac201};
        isaacTauntAni = new Animation(0.06f, isaactaunt);
        isaacTauntAni.setPlayMode(Animation.PlayMode.NORMAL);

        //--------------------Isaac Psy---------------------------------
        TextureRegion[] isaacpsydr = {isaac160,isaac165,isaac165,isaac165,isaac165,isaac170,isaac175,isaac175,isaac175,isaac175,isaac175,isaac175,isaac175,isaac175,isaac175,isaac175,isaac175};
        isaacPsyDRAni = new Animation(0.06f, isaacpsydr);
        isaacPsyDRAni.setPlayMode(Animation.PlayMode.NORMAL);
        TextureRegion[] isaacpsyr = {isaac161,isaac166,isaac166,isaac166,isaac166,isaac171,isaac176,isaac176,isaac176,isaac176,isaac176,isaac176,isaac176,isaac176,isaac176,isaac176,isaac176};
        isaacPsyRAni = new Animation(0.06f, isaacpsyr);
        isaacPsyRAni.setPlayMode(Animation.PlayMode.NORMAL);
        TextureRegion[] isaacpsyur = {isaac162,isaac167,isaac167,isaac167,isaac167,isaac172,isaac177,isaac177,isaac177,isaac177,isaac177,isaac177,isaac177,isaac177,isaac177,isaac177,isaac177};
        isaacPsyURAni = new Animation(0.06f, isaacpsyur);
        isaacPsyURAni.setPlayMode(Animation.PlayMode.NORMAL);
        TextureRegion[] isaacpsyd = {isaac163,isaac168,isaac168,isaac168,isaac168,isaac173,isaac178,isaac178,isaac178,isaac178,isaac178,isaac178,isaac178,isaac178,isaac178,isaac178,isaac178};
        isaacPsyDAni = new Animation(0.06f, isaacpsyd);
        isaacPsyDAni.setPlayMode(Animation.PlayMode.NORMAL);
        TextureRegion[] isaacpsyu = {isaac164,isaac169,isaac169,isaac169,isaac169,isaac174,isaac179,isaac179,isaac179,isaac179,isaac179,isaac179,isaac179,isaac179,isaac179,isaac179,isaac179};
        isaacPsyUAni = new Animation(0.06f, isaacpsyu);
        isaacPsyUAni.setPlayMode(Animation.PlayMode.NORMAL);

        //--------------------Standing---------------------------------
        TextureRegion[] isaacfront = {isaac4,isaac4,isaac4,isaac9,isaac14,isaac14,isaac14,isaac29,isaac14,isaac29,isaac14,isaac9,isaac4,isaac4,isaac4};
        isaacFrontAni = new Animation(0.06f, isaacfront);
        isaacFrontAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] isaacBack = {isaac5,isaac5,isaac5,isaac10,isaac15,isaac15,isaac15,isaac30,isaac15,isaac30,isaac15,isaac10,isaac5,isaac5,isaac5};
        isaacBackAni = new Animation(0.06f, isaacBack);
        isaacBackAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] isaacRight = {isaac02,isaac02,isaac02,isaac7,isaac12,isaac12,isaac12,isaac27,isaac12,isaac27,isaac12,isaac7,isaac02,isaac02,isaac02};
        isaacRightAni = new Animation(0.06f, isaacRight);
        isaacRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] isaacUpRight = {isaac03,isaac03,isaac03,isaac8,isaac13,isaac13,isaac13,isaac28,isaac13,isaac28,isaac13,isaac8,isaac03,isaac03,isaac03};
        isaacUpRightAni = new Animation(0.06f, isaacUpRight);
        isaacUpRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] isaacDownRight = {isaac01,isaac01,isaac01,isaac6,isaac011,isaac011,isaac011,isaac26,isaac011,isaac26,isaac011,isaac6,isaac01,isaac01,isaac01};
        isaacDownRightAni = new Animation(0.06f, isaacDownRight);
        isaacDownRightAni.setPlayMode(Animation.PlayMode.LOOP);

        //--------------------Isaac Walking---------------------------------
        TextureRegion[] walkUp = {isaac35, isaac40, isaac45, isaac50, isaac55, isaac60};
        walkUpAni = new Animation(0.06f, walkUp);
        walkUpAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] walkDown = {isaac34, isaac39, isaac44, isaac49, isaac54, isaac59};
        walkDownAni = new Animation(0.06f, walkDown);
        walkDownAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] walkRight = {isaac32, isaac37, isaac42, isaac47, isaac52, isaac57};
        walkRightAni = new Animation(0.06f, walkRight);
        walkRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] walkUpRight = {isaac33, isaac38, isaac43, isaac48, isaac53, isaac58};
        walkUpRightAni = new Animation(0.06f, walkUpRight);
        walkUpRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] walkDownRight = {isaac031, isaac36, isaac41, isaac46, isaac51, isaac56};
        walkDownRightAni = new Animation(0.06f, walkDownRight);
        walkDownRightAni.setPlayMode(Animation.PlayMode.LOOP);


        //--------------------Running---------------------------------
        TextureRegion[] runUp = {isaac90,isaac95,isaac100,isaac105,isaac110,isaac115};
        runUpAni = new Animation(0.06f, runUp);
        runUpAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] runDown = {isaac89,isaac94,isaac99,isaac104,isaac109,isaac114};
        runDownAni = new Animation(0.06f, runDown);
        runDownAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] runRight = {isaac87,isaac92,isaac97,isaac102,isaac107,isaac112};
        runRightAni = new Animation(0.06f, runRight);
        runRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] runUpRight = {isaac88,isaac93,isaac98,isaac103,isaac108,isaac113};
        runUpRightAni = new Animation(0.06f, runUpRight);
        runUpRightAni.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion[] runDownRight = {isaac86,isaac91,isaac96,isaac101,isaac106,isaac111};
        runDownRightAni = new Animation(0.06f, runDownRight);
        runDownRightAni.setPlayMode(Animation.PlayMode.LOOP);

        //--------------------Turning---------------------------------
        TextureRegion[] downUp = {isaac4,isaac02,isaac5};
        isaacTurnDownUpAni = new Animation(2f, downUp);
        isaacTurnDownUpAni.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void dispose() {
        isaac1.dispose();
        isaac11.dispose();
        isaac2.dispose();
        isaac21.dispose();
        isaac3.dispose();
        isaac31.dispose();
        alex1.dispose();
        alex2.dispose();
        move.dispose();
    }
}
