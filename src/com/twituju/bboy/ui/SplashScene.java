  
package com.twituju.bboy.ui;    


import android.graphics.Bitmap;

import com.twituji.bboy.BBoyActivity;
import com.twituji.bboy.R;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.opengl.TextureManager;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;

	/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Tsiannian 
 * @date 2011-10-18 上午12:05:28
 */
public class SplashScene extends Scene{
	private BBoyActivity mActivity;
	private SplashLayer myLayer;
	public SplashScene(BBoyActivity mActivity){
		this.mActivity = mActivity;
		myLayer = new SplashLayer();
		addChild(myLayer);
	}
	
	class SplashLayer extends Layer{
		public SplashLayer(){
			Texture2D tex = Texture2D.makePNG(R.drawable.splash,
					TextureManager.TEXTURE_PIXEL_FORMAT_RGBA8888 );
			Sprite backgroundSprite = Sprite.make(tex);
			LayoutParam.initParam();
			backgroundSprite.setPosition(LayoutParam.SCREEN_CENTER);
			addChild(backgroundSprite);
			Object[] arrayOfObject = new Object[1];
			TargetSelector mSelector = new TargetSelector(this,
					"gotoMenu(float)", arrayOfObject);
			scheduleOnce(mSelector, 3);

			autoRelease(true);
		}
		
		public void gotoMenu(float f){
			Director.getInstance().replaceScene(mActivity.getWelcomeScene());
		}
	}

}
  