  
package com.twituju.bboy.ui;    

import com.twituji.bboy.BBoyActivity;
import com.twituji.bboy.R;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Slider;
import com.wiyun.engine.nodes.Slider.ISliderCallback;
import com.wiyun.engine.nodes.Sprite;

	/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Tsiannian 
 * @date 2011-10-9 下午8:30:42
 */
public class SettingScene extends Scene{
	protected Layer backgroundLayer;
	protected SettingLayer settingLayer;
	private BBoyActivity mActivity;
	
	public SettingScene(BBoyActivity mActivity){
		this.mActivity = mActivity;
		backgroundLayer = Layer.make();
		Sprite background = Sprite.make(R.drawable.backgroud);
		background.setPosition(LayoutParam.SCREEN_CENTER);
		backgroundLayer.addChild(background);
		backgroundLayer.autoRelease(true);
		addChild(backgroundLayer);
		
		settingLayer = new SettingLayer();
		addChild(settingLayer);
	}
	
	class SettingLayer extends Layer implements ISliderCallback{
		private Label audioLabel;
		private Label soundLabel;
		private Label volumeLabel;
		private Label volumeValueLabel;
		private Button audioButton;
		private Button soundButton;
		private Slider volumeSlider;
		
		public SettingLayer(){
			audioLabel = Label.make(LayoutParam.audio_label);
			audioLabel.setPosition(LayoutParam.AUDIO_LABEL_POINT);
			addChild(audioLabel);
			
			soundLabel = Label.make(LayoutParam.sound_label);
			soundLabel.setPosition(LayoutParam.SOUND_LABEL_POINT);
			addChild(soundLabel);
			
			volumeLabel = Label.make(LayoutParam.volume_label);
			volumeLabel.setPosition(LayoutParam.VOLUME_LABEL_POINT);
			addChild(volumeLabel);
			
			volumeValueLabel = Label.make(String.valueOf(LayoutParam.VOLUME_VALUE));
			volumeValueLabel.setPosition(LayoutParam.VOLUME_VALUE_LABEL_POINT);
			addChild(volumeLabel);
			
			
			
			audioButton = Button.make(R.drawable.btn_send_score_normal, 
            		R.drawable.btn_send_score_pressed, this, "OnAudioButtonClicked");
			audioButton.setPosition(LayoutParam.AUDIO_BUTTON_POINT);
			addChild(audioButton);
			
			soundButton = Button.make(R.drawable.btn_send_score_normal, 
            		R.drawable.btn_send_score_pressed, this, "OnSettingButtonClicked");
			soundButton.setPosition(LayoutParam.SOUND_BUTTON_POINT);
			addChild(soundButton);
			
			
			
			Sprite bar = Sprite.make(R.drawable.bar);
    		Sprite thumb1 = Sprite.make(R.drawable.thumb);
    		volumeSlider = Slider.make(null, bar, thumb1);
    		volumeSlider.setValue(50);
    		volumeSlider.setShowFullBar(true);
    		volumeSlider.setAnchorPercent(0, 0);
    		volumeSlider.setPosition(LayoutParam.VOLUME_SLIDER_POINT);
    		volumeSlider.setCallback(this);
    		addChild(volumeSlider);
			
			setKeyEnabled(true);
		}
		
		public void OnAudioButtonClicked(){
			
		}
		
		public void OnSettingButtonClicked(){
			
		}

		@Override
		public void onSliderValueChanged(int pointer, float value) {
			// TODO Auto-generated method stub
			LayoutParam.VOLUME_VALUE = volumeSlider.getValue();
			volumeValueLabel.setText(String.format("%.2f", LayoutParam.VOLUME_VALUE));
		}
		
		@Override
		public boolean onBackButton(){
			Director.getInstance().replaceScene(mActivity.getWelcomeScene());
			return true;
		}
		
		
		
	}


}
  