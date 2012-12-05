  
package com.twituju.bboy.ui;    

import com.twituji.bboy.BBoyActivity;
import com.twituji.bboy.R;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYRect;

	/**
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @author Tsiannian 
	 * @date 2011-10-9 下午7:29:15
	 */
public class WelcomeScene extends Scene{
	
	private Layer welcomeLayer;
	private Layer backgroundLayer;
	private BBoyActivity mActivity;
	
	public WelcomeScene(BBoyActivity mActivity){
		this.mActivity = mActivity;
		
		welcomeLayer = new WelcomeLayer();
		backgroundLayer = Layer.make();
		Sprite background = Sprite.make(R.drawable.welcome);
		background.setPosition(LayoutParam.SCREEN_CENTER);
		backgroundLayer.addChild(background);
		backgroundLayer.autoRelease(true);
		
		addChild(backgroundLayer);
		addChild(welcomeLayer);
	}

	class WelcomeLayer extends Layer{
		private Button settingButton;
		private Button aboutButton;
		private Button beginButton;
		private Button quitButton;

		Sprite spriteNormal;
		Sprite spriteSlected;
		
		Texture2D buttonTexture;
		public WelcomeLayer(){
			buttonTexture = Texture2D.makePNG(R.drawable.button2);
			
			spriteNormal = Sprite.make(buttonTexture,
					WYRect.make(0, 95*0, 412, 95));
			spriteSlected = Sprite.make(buttonTexture, 
					WYRect.make(412, 95*0, 412, 95));
			beginButton = Button.make(spriteNormal, 
					spriteSlected, null, null, this, "OnBeginButtonClicked");
			beginButton.setPosition(LayoutParam.BEGIN_BUTTON_POINT);
			addChild(beginButton);
			
			spriteNormal = Sprite.make(buttonTexture,
					WYRect.make(0, 95*1, 412, 95));
			spriteSlected = Sprite.make(buttonTexture, 
					WYRect.make(412, 95*1, 412, 95));
			settingButton = Button.make(spriteNormal, 
					spriteSlected, null, null, this, "OnSettingButtonClicked");
			settingButton.setPosition(LayoutParam.SETTING_BUTTON_POINT);
			addChild(settingButton);
			
			spriteNormal = Sprite.make(buttonTexture,
					WYRect.make(0, 95*3, 412, 95));
			spriteSlected = Sprite.make(buttonTexture, 
					WYRect.make(412, 95*3, 412, 95));
			aboutButton = Button.make(spriteNormal, 
					spriteSlected, null, null, this, "OnAboutButtonClicked");
			aboutButton.setPosition(LayoutParam.ABOUT_BUTTON_POINT);
			addChild(aboutButton);
			
			spriteNormal = Sprite.make(buttonTexture,
					WYRect.make(0, 95*2, 412, 95));
			spriteSlected = Sprite.make(buttonTexture, 
					WYRect.make(412, 95*2, 412, 95));
			quitButton = Button.make(spriteNormal, 
					spriteSlected, null, null, this, "OnQuitButtonClicked");
			quitButton.setPosition(LayoutParam.QUIT_BUTTON_POINT);
			addChild(quitButton);
			

			
		}
		
		
		public void OnSettingButtonClicked(){
			mActivity.runOnUiThread(new Runnable(){
				public void run(){
					Director.getInstance().replaceScene(mActivity.getSettingScene());
				}
			});
		}
		
		public void OnAboutButtonClicked(){
			mActivity.runOnUiThread(new Runnable(){
				public void run(){
					Director.getInstance().replaceScene(mActivity.getAboutScene());
				}
			});
		}
		
		public void OnBeginButtonClicked(){
			mActivity.runOnUiThread(new Runnable(){
				public void run(){
					Director.getInstance().replaceScene(mActivity.getScriptScene());
				}
			});
		}
		
		public void OnQuitButtonClicked(){
			mActivity.runOnUiThread(new Runnable(){
				public void run(){
					Director.getInstance().end();
				}
			});
			
		}
	}

}
  