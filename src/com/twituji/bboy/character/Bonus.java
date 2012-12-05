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
package com.twituji.bboy.character;

import com.twituji.bboy.R;
import com.twituji.bboy.ScriptLayer;
import com.twituji.bboy.Util;
import com.twituji.bboy.config.ConstPara;
import com.twituji.bboy.particle.Particle;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.utils.ResolutionIndependent;

/**
 * @author dlqingxi
 * 
 */
public class Bonus extends Actor {

	public static String message;
	public static boolean isFirstBonus;

	public Bonus(float y, Layer layer) {
		size = ResolutionIndependent.resolveDp(16);
		if (y == ConstPara.SCREEN_HEIGHT){
			pos.x = Util.randw(0.1f, 0.8f);
		}
		else{
			pos.x = Util.randw(0.3f, 0.5f);
		}
		pos.y = y + size / 2;
		
		this.layer = layer;
		Sprite sprite = Sprite.make(R.drawable.bonus);
		sprite.setScale(.8f);
		this.sprite = sprite;

		layer.addChild(sprite);
		sprite.setPosition(pos);
		sprite.autoRelease();
		
		checkPosAllowed(this.pos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twituji.bboy.character.Actor#initialize()
	 */
	@Override
	void initialize() {

	}

	private void checkPosAllowed(WYPoint pos){
		for (Actor p : ConstPara.pins) {
			if (WYPoint.distance(p.pos, pos) < size * 4) {
				((Pin) p).isRemoving = true;
			}
		}
	}
	
	@Override
	public boolean update() {
		if (isFirstBonus && pos.y < ConstPara.SCREEN_HEIGHT - 10) {
			// message = addMessage("HIT A TARGET WITH A BALLOON", pos.x, pos.y,
			// 90, PI / 5 * 4);
			checkPosAllowed(this.pos);
			isFirstBonus = false;
		}
		// if (message) message.pos.xy = pos;
		for (Actor b : ConstPara.balloons) {
			if (WYPoint.distance(b.pos, pos) < b.size / 2 + size
					/ 2) {
				if(ConstPara.WITH_AUDIO){
					AudioManager.playEffect(R.raw.effect_ogg_mono);
				}
				
				for (Actor p : ConstPara.pins){
					if (p.pos.y <= pos.y){
						((Pin) p).isRemoving = true;
					}
				}
				// var s = b.size / 16; s = int(s * s) + 1;
				// addNumberBoard(s, pos.x, pos.y); score += s;
				Particle.addParticlesRound(30, pos, 10, 5, 0.95f);
				ConstPara.numberOfBonus ++;
				ScriptLayer sl = (ScriptLayer)(this.layer);
				sl.scoreLabel.setText("Score: " + ConstPara.numberOfBonus + " * Red fruit");
				ConstPara.bonuses.add(new Bonus(ConstPara.SCREEN_HEIGHT, this.layer));
				return false;
			}
		}
		return true;
	}
}
