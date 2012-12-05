package com.twituji.bboy.config;

import java.util.ArrayList;
import java.util.List;

import com.twituji.bboy.character.Actor;
import com.twituji.bboy.particle.Particle;
import com.wiyun.engine.types.WYPoint;

/**
 * @author dlqingxi
 *
 */
public class ConstPara {
	final public static boolean SHOW_FPS = true;
	public static boolean WITH_AUDIO = true;
	
	final public static int RANK_CYCLE_SCORE = 1000;
	final public static int RANK_CYCLE_INCREMENT = 2;
	final public static float RANK_NEXT_CYCLE_INCREMENT = 0.2f;
	
	final public static String gameTitle = "＝気泡戦士＝";
	final public static String startTxt = "開始○";
	final public static String endTxt = "退出×";
	final public static String settingTxt = "設定♪";
	
	public static float LIMIT_Y, LOW_V_AREA;
	public static float SCREEN_HEIGHT, SCREEN_WIDTH;
	public static WYPoint mouse,mouseVel;
	public static WYPoint BASE_LINE_L, BASE_LINE_R;
	public static boolean isMousePressed;
	public static float pinMaxY;
	
	public static float nextPinDist = 0;
	public static float rank = 1;
	public static float score  = 0;
	public static float ticks = 0;
	
	public static boolean isMouseClicked = false;
	
	public static List <Actor> balloons = new ArrayList<Actor>(10);
	public static List <Actor> pins = new ArrayList<Actor>(20);
	public static List <Actor> bonuses = new ArrayList<Actor>(10);
	public static List <Particle> particles = new ArrayList<Particle>(50);
	
	public static boolean addBalloon = false;
	public static int numberOfBonus;
	
}
