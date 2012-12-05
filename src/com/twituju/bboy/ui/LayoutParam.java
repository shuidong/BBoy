  
package com.twituju.bboy.ui;    

import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;

	/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Tsiannian 
 * @date 2011-10-9 下午7:28:17
 */
public class LayoutParam {
	
	public static void initParam(){
		WINDOW_SIZE = Director.getInstance().getWindowSize();
		SCREEN_HEIGHT = WINDOW_SIZE.height;
		SCREEN_WIDTH = WINDOW_SIZE.width;
		SCREEN_CENTER = WYPoint.make(SCREEN_WIDTH/2, SCREEN_HEIGHT /2);
		
		space = ResolutionIndependent.resolveDp(50);
		BEGIN_BUTTON_POINT = WYPoint.make(
				SCREEN_WIDTH /2, SCREEN_HEIGHT /2);
		
		SETTING_BUTTON_POINT = WYPoint.make(
				SCREEN_WIDTH /2, SCREEN_HEIGHT /2 - space * 1);
		ABOUT_BUTTON_POINT = WYPoint.make(
				SCREEN_WIDTH /2, SCREEN_HEIGHT /2 - space * 2);
		
		QUIT_BUTTON_POINT = WYPoint.make(
				SCREEN_WIDTH /2, SCREEN_HEIGHT /2 - space * 3);
		

		AUDIO_LABEL_POINT = WYPoint.make(
				SCREEN_WIDTH /4, SCREEN_HEIGHT *3/4);
		AUDIO_BUTTON_POINT = WYPoint.make(
				SCREEN_WIDTH *3/4, SCREEN_HEIGHT *3/4);
		
		space = ResolutionIndependent.resolveDp(50);
		
		SOUND_LABEL_POINT = WYPoint.make(
				SCREEN_WIDTH /4, SCREEN_HEIGHT *3/4 - space);
		SOUND_BUTTON_POINT = WYPoint.make(
				SCREEN_WIDTH *3/4, SCREEN_HEIGHT *3/4 - space);
		space += ResolutionIndependent.resolveDp(50);
		
		
		VOLUME_LABEL_POINT = WYPoint.make(
				SCREEN_WIDTH /4 , SCREEN_HEIGHT *3/4 - space);
		VOLUME_VALUE_LABEL_POINT = WYPoint.make(
				SCREEN_WIDTH *3/4 , SCREEN_HEIGHT *3/4 - space);
		space += ResolutionIndependent.resolveDp(50);
		
		VOLUME_SLIDER_POINT	 = WYPoint.make(
				SCREEN_WIDTH /5 , SCREEN_HEIGHT *3/4 - space);
		
		
	}
	
	public static WYSize WINDOW_SIZE;
	public static float SCREEN_HEIGHT;
	public static float SCREEN_WIDTH;
	public static WYPoint SCREEN_CENTER;
	
	public static WYPoint SETTING_BUTTON_POINT;
	public static WYPoint ABOUT_BUTTON_POINT;
	public static WYPoint BEGIN_BUTTON_POINT;
	public static WYPoint QUIT_BUTTON_POINT;
	
	public static String audio_label = "音乐:";
	public static String sound_label = "音效:";
	public static String volume_label = "音量:";
	
	public static WYPoint AUDIO_LABEL_POINT;
	public static WYPoint SOUND_LABEL_POINT;
	public static WYPoint VOLUME_LABEL_POINT; 
	public static WYPoint VOLUME_VALUE_LABEL_POINT;
	
	public static WYPoint AUDIO_BUTTON_POINT;
	public static WYPoint SOUND_BUTTON_POINT;
	public static WYPoint VOLUME_SLIDER_POINT;

	
	public static float VOLUME_VALUE = 50 ;
	
	public static float half = 2;
	public static float space;
	
	
	
	
	

}
  