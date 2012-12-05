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
import com.twituji.bboy.Util;
import com.twituji.bboy.config.ConstPara;
import com.twituji.bboy.particle.Particle;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.utils.ResolutionIndependent;

/**
 * @author dlqingxi
 * 
 */
public class Pin extends Actor {

	boolean isRemoving;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twituji.bboy.character.Actor#initialize()
	 */
	@Override
	void initialize() {
		// TODO Auto-generated method stub
	}

	public Pin(float y, Layer layer) {
		size = ResolutionIndependent.resolveDp(20);
		pos.x = Util.randw(0.1f, 0.8f);
		pos.y = y + ConstPara.SCREEN_HEIGHT * .3f;
		//Log.v("Pin.new()", String.valueOf(pos.y));
		
		this.layer = layer;
		Sprite sprite = Sprite.make(R.drawable.pin);
	//	sprite.setScale(.8f);
		this.sprite = sprite;

		layer.addChild(sprite);
		sprite.setPosition(pos);
		sprite.autoRelease();
	}

	@Override
	public boolean update() {
		if (isRemoving) {
			Particle.addParticlesRound(30, pos, 15f, 5, 0.95f);
			return false;
		}
		if (pos.y <= ConstPara.LIMIT_Y) {
			//Particle.addParticlesRound(300, pos, 5, 10, 0.99f);
			// startGameOver();
			for (Actor p : ConstPara.pins) {
				((Pin) p).isRemoving = true;
			}
			return false;
		}
		if (ConstPara.pinMaxY > pos.y){
			ConstPara.pinMaxY = pos.y;
		}
		return true;
	}
}
