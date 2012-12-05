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

import android.util.Log;

import com.twituji.bboy.R;
import com.twituji.bboy.config.ConstPara;
import com.twituji.bboy.particle.Particle;
import com.twituji.bboy.particle.ParticleMeteor;
import com.twituju.bboy.ui.LayoutParam;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.utils.ResolutionIndependent;

/**
 * @author dlqingxi
 * 
 */
public class Balloon extends Actor {

	public boolean isBlowingUp = true;
	public WYPoint vel = WYPoint.make(0f, 0f);
	//the base_size's values should equal with the sprite's size
	final public float BASE_SIZE = ResolutionIndependent.resolveDp(20);
	final public float MOST_LARGE_SIZE = BASE_SIZE * 3f;

	public ParticleSystem emitter;
	
	public Balloon(WYPoint p, Layer layer) {
		this.pos = p;
		this.layer = layer;
		this.size = BASE_SIZE / 2;
		Sprite sprite = Sprite.make(R.drawable.balloon);
		sprite.setAlpha(128);
		this.sprite = sprite;

		layer.addChild(sprite);
		sprite.setPosition(pos);
		sprite.autoRelease();
		
		{
			emitter = ParticleMeteor.make();
			layer.addChild(emitter);
			emitter.autoRelease(true);
		}
	}

	@Override
	void initialize() {
	}

	@Override
	public boolean update() {
		if (isBlowingUp) {
			//control the largest size of the balloon
			if(size ++ > MOST_LARGE_SIZE){
				destroy();
				return false;
			}
			sprite.setScale(size / BASE_SIZE);
			pos = ConstPara.mouse;
//			vel.y += 1f;
			vel.y = 7f;
			if (!ConstPara.isMousePressed || pos.y > ConstPara.LIMIT_Y) {
				isBlowingUp = false;
				sprite.setAlpha(255);
			}
		} else {
			vel.y += .1f;
		}
		float px = pos.x, py = pos.y;
		float vel_value = WYPoint.length(vel);
		if (vel_value > 20){
			vel = WYPoint.mul(vel, 20 / vel_value);
		}
		pos = WYPoint.add(pos, vel);
		
		for (Actor p : ConstPara.pins) {
			float distance = WYPoint.distance(p.pos, pos);
			float vel_len = WYPoint.length(vel);
			if (distance < size / 2 + p.size / 2) {
				if (isBlowingUp) {
					destroy();
					return false;
				}
				vel.x += vel_len * (pos.x - p.pos.x)
						/ distance;
				vel.y -= vel_len * (p.pos.y - pos.y)
						/ distance;
				pos.x = px;
				pos.y = py;
			}
		}
		for (Actor b : ConstPara.balloons) {
			if (b == this || ((Balloon)b).isBlowingUp){
				continue;
			}
			
			float distance = WYPoint.distance(b.pos, pos);
			float vel_len = WYPoint.length(vel);
			if (distance < size / 2 + b.size / 2) {
				if (isBlowingUp) {
					destroy();
					return false;
				}
				vel.x += vel_len * (pos.x - b.pos.x)
						/ distance;
				vel.y -= vel_len * (b.pos.y - pos.y)
						/ distance;
				pos.x = px;
				pos.y = py;
			}
		}
		if (pos.x < size / 2 || pos.x > ConstPara.SCREEN_WIDTH - size / 2) {
			if (isBlowingUp) {
				destroy();
				return false;
			}
			if ((pos.x < size / 2 && vel.x < 0)
					|| (pos.x > ConstPara.SCREEN_WIDTH - size / 2 && vel.x > 0)) {
				vel.x *= -0.2;
				pos.x = px;
				pos.y = py;
			}
		}
		size -= .03f;

		sprite.setScale(size / BASE_SIZE);
		
		{//for interest
			//size = 10f;
			//Particle.addParticlesAngle(5, pos, (float)(Math.random() * (Math.PI * 2)), size / 10 , 15f, 0.65f, 0.45f);
			
			emitter.setPosition(pos);
		}
		Log.v("ball-pos", String.valueOf(pos.y));
		return size > 8 && pos.y < ConstPara.SCREEN_HEIGHT + 2 * size;
	}

	public void destroy() {
		this.layer.removeChild(emitter, false);
        for (int i = 0; i < 16; i++) {
            double a = (Math.random() * (Math.PI * 2));
            Particle.addParticlesAngle(10, pos, (float)a, size / 10, 5f, 0.95f, 0.9f);
        }
        
	}
}
