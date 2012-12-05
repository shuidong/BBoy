/*
 * Copyright (c) 2011 T4Roid Studio.

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.twituji.bboy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.twituji.bboy.config.ConstPara;
import com.twituju.bboy.ui.AboutScene;
import com.twituju.bboy.ui.LayoutParam;
import com.twituju.bboy.ui.SettingScene;
import com.twituju.bboy.ui.SplashScene;
import com.twituju.bboy.ui.WelcomeScene;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.WYGLSurfaceView;

/**
 * @author dlqingxi
 *
 */
public class BBoyActivity extends Activity {
	
	static {
		System.loadLibrary("xml2");
		System.loadLibrary("wiengine");
		//if used wiyun 3.6, you shoud load this lib for sound play
		System.loadLibrary("wisound");
	}
	
	protected WYGLSurfaceView mGLSurfaceView;
	protected Scene aboutScene;
	protected Scene scriptScene;
	protected Scene splashScene;
	protected Scene welcomeScene;
	protected Scene settingScene;

	
	public BBoyActivity(){
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    getWindow().addFlags(LayoutParams.FLAG_FULLSCREEN);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    LayoutParam.initParam();
	    mGLSurfaceView = new WYGLSurfaceView(this, false);
        setContentView(mGLSurfaceView);
        ConstPara.WITH_AUDIO = true;
	    Director.getInstance().setDisplayFPS(ConstPara.SHOW_FPS);
	    Director.getInstance().runWithScene(this.getSplashScene());
    }
    

    
    /**
     * create Script-Scene
     * @return 
     */
    public void createScriptScene(){

        	scriptScene = Scene.make();
        	Sprite background = Sprite.make(R.drawable.backgroud);
        	Layer backgroundLayer = Layer.make();
        	background.setPosition(LayoutParam.SCREEN_CENTER);
        	backgroundLayer.addChild(background);
        	scriptScene.addChild(backgroundLayer);
        	scriptScene.addChild(new ScriptLayer(BBoyActivity.this));
        	scriptScene.autoRelease(true);

    }
    
    @Override
	protected void onPause() {
        super.onPause();
        Director.getInstance().pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Director.getInstance().resume();
    }

    @Override
    protected void onDestroy() {
    	Director.getInstance().end();
        super.onDestroy();
    }
    
    
	/**
	 * @return
	 */
	public Scene getScriptScene() {
    	if(scriptScene == null){
    		createScriptScene();
    	}
		return scriptScene;
	}

	
	public Scene getAboutScene(){
		if(aboutScene == null){
			aboutScene = new AboutScene(this);
		}
		return aboutScene;
	}
	
	public Scene getSettingScene(){
		if(settingScene == null){
			settingScene = new SettingScene(this);
		}
		return settingScene;
	}
	
	public Scene getSplashScene(){
		if(splashScene == null){
			splashScene = new SplashScene(this);
		}
		return splashScene;
	}
	
	public Scene getWelcomeScene(){
		if(welcomeScene == null){
			welcomeScene = new WelcomeScene(this);
		}
		return welcomeScene;
	}
}