  
package com.twituju.bboy.ui;    

import com.twituji.bboy.BBoyActivity;
import com.twituji.bboy.R;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;

	/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Tsiannian 
 * @date 2011-10-20 上午3:05:11
 */
public class AboutScene extends Scene{
	private AboutLayer myLayer;
	private Layer backgroundLayer;
	private BBoyActivity mActivity;
	
	public AboutScene(BBoyActivity mActivity){
		this.mActivity = mActivity;
	
		myLayer = new AboutLayer();
		addChild(myLayer);
		
		
	}
	
	class AboutLayer extends Layer{
		public AboutLayer(){
			Sprite background = Sprite.make(R.drawable.backgroud);
			background.setPosition(LayoutParam.SCREEN_CENTER);
			addChild(background);
			
			Label text = Label.make("内部测试版V0.10");
			text.setPosition(LayoutParam.SCREEN_CENTER);
			addChild(text);
			
			setKeyEnabled(true);
		}
		
		@Override
		public boolean onBackButton(){
			Director.getInstance().replaceScene(new WelcomeScene(mActivity));
			return true;
		}
	}

}
  