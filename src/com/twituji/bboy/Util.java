package com.twituji.bboy;

import javax.microedition.khronos.opengles.GL10;

import com.twituji.bboy.config.ConstPara;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.utils.ResolutionIndependent;

public class Util {
	
	private static GL10 gl;
	public static GL10 getGlInstance(){
		if(gl == null){
			gl = Director.getInstance().gl;
		}
		return gl;
	}
	
	public static float randw(float s, float w) {
		return (float) (s + w * Math.random()) * ConstPara.SCREEN_WIDTH;
	}
	
	
	public static float randh(float s, float w) {
		return (float) (s + w * Math.random()) * ConstPara.SCREEN_HEIGHT;
	}
	
	public static boolean isInScreen(WYPoint p) {
	    return (p.x >= 0 && p.x <= ConstPara.SCREEN_WIDTH  && p.y >= 0 && p.y <= ConstPara.SCREEN_HEIGHT );
	}
	
	public static float DP(float v) {
		return ResolutionIndependent.resolveDp(v);
	}
}
