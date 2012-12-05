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

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Typeface;
import android.view.MotionEvent;

import com.twituji.bboy.character.Actor;
import com.twituji.bboy.character.Balloon;
import com.twituji.bboy.character.Bonus;
import com.twituji.bboy.character.Pin;
import com.twituji.bboy.config.ConstPara;
import com.twituji.bboy.particle.Particle;
import com.twituji.bboy.particle.ParticleGalaxy;
import com.twituji.bboy.particle.ParticleSnow;
import com.wiyun.engine.nodes.ColorLayer;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.INodeVirtualMethods;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Scheduler;
import com.wiyun.engine.nodes.Timer;
import com.wiyun.engine.opengl.Primitives;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;

/**
 * @author dlqingxi
 * 
 */
public class ScriptLayer extends ColorLayer implements INodeVirtualMethods{

	private BBoyActivity bboyActivity;

	private float nextPinDist = 0f;
	private boolean isGameOver;

	public Label scoreLabel;
	/**
	 * @param bboyActivity
	 */
	public ScriptLayer(BBoyActivity bboyActivity) {
		//super(new WYColor4B(0, 0, 0, 255));
		if (ConstPara.WITH_AUDIO) {
			AudioManager.playBackgroundMusic(R.raw.boy,
					AudioManager.FORMAT_OGG, -1);
		}

		this.bboyActivity = bboyActivity;
		setNoDraw(true);
		setJavaVirtualMethods(this);
		WYSize s = Director.getInstance().getWindowSize();
		ConstPara.SCREEN_WIDTH = s.width;
		ConstPara.SCREEN_HEIGHT = s.height;
		ConstPara.LIMIT_Y = s.height * .2f;
		ConstPara.LOW_V_AREA = s.height * .6f;
		ConstPara.BASE_LINE_L = WYPoint.make(0, ConstPara.LIMIT_Y);
		ConstPara.BASE_LINE_R = WYPoint.make(ConstPara.SCREEN_WIDTH,
				ConstPara.LIMIT_Y);

		ConstPara.balloons.clear();
		ConstPara.pins.clear();
		ConstPara.bonuses.clear();
		ConstPara.particles.clear();

		ConstPara.numberOfBonus = 0;
		ConstPara.bonuses.add(new Bonus(ConstPara.SCREEN_HEIGHT * 1.6f,
				ScriptLayer.this));
		// add the first Bonus at SCREEN_HEIGHT * 1.6f
		/*BJoystick bvj = new BJoystick(this);
		bvj.setPosition(Util.DP(20), Util.DP(20));
		this.addChild(bvj);*/
		
		setTouchEnabled(true);
		{//score
			scoreLabel = Label.make("Score:", 12, "DroidSans", Typeface.BOLD, 0);
			scoreLabel.setPosition(ConstPara.SCREEN_WIDTH / 4, ConstPara.LIMIT_Y);
            addChild(scoreLabel);
		}

		{//ParticleSystem
			ParticleSystem emitter;
			emitter = ParticleSnow.make();
			addChild(emitter);
			emitter.setPosition(s.width / 2, s.height);
			
			emitter = ParticleGalaxy.make();
			addChild(emitter);
			emitter.setPosition(s.width * .6f, s.height * .6f);
		}
		
		
		//function update() will be called every frame
		//because we used jDraw method in the layer,
		//it will update automic ,so here is not need to scheduler it
		/*TargetSelector mSelector1 = new TargetSelector(this, "update(float)",
				new Object[] { 0f });
		Timer t = new Timer(mSelector1, 1);
		Scheduler.getInstance().schedule(t);*/
	}

	
	@Override
	public boolean wyTouchesBegan(MotionEvent event) {
		WYPoint touchLocation = WYPoint.make(event.getX(), event.getY());
		ConstPara.mouse = Director.getInstance().convertToGL(touchLocation.x,
				touchLocation.y);
		ConstPara.isMouseClicked = true;
		ConstPara.isMousePressed = true;

		if (ConstPara.isMouseClicked && ConstPara.mouse.y < ConstPara.LIMIT_Y) {
			ConstPara.balloons.add(new Balloon(ConstPara.mouse,
					ScriptLayer.this));
			ConstPara.isMouseClicked = false;
		}
		return true;
	}

	@Override
	public boolean wyTouchesEnded(MotionEvent event) {
		ConstPara.isMousePressed = false;
		return true;
	}

	@Override
	public boolean wyTouchesMoved(MotionEvent event) {
		if (ConstPara.isMousePressed) {
			WYPoint touchLocation = WYPoint.make(event.getX(), event.getY());
			ConstPara.mouse = Director.getInstance().convertToGL(
					touchLocation.x, touchLocation.y);
		}
		return true;
	}

	/**
	 * @param actors
	 */
	public void updateActors(List<Actor> actors) {
		int actors_size = actors.size();
		Actor actor;
		for (int i = actors_size - 1; i > -1; i--) {
			actor = actors.get(i);
			if (actor.update()) {
				if (actor.sprite != null) {
					actor.sprite.setPosition(actor.pos);
				}
			} else {
				if(Balloon.class.isInstance(actor)){
					Balloon bl = (Balloon)actor;
					bl.layer.removeChild(bl.emitter, false);
				}
				if (actor.sprite != null) {
					actor.removeSprite();
				}
				actors.remove(i);
			}
		}
	}

	/**
	 * @param y
	 */
	private void scroll(float y) {
		for (Actor b : ConstPara.balloons) {
			b.pos.y -= y;
		}
		for (Actor p : ConstPara.pins) {
			p.pos.y -= y;
		}
		for (Actor b : ConstPara.bonuses) {
			b.pos.y -= y;
		}
		// Particle.scrollParticles(0, y);
		nextPinDist -= y;
		while (nextPinDist < 0) {
			ConstPara.pins.add(new Pin(ConstPara.SCREEN_HEIGHT
					+ nextPinDist, ScriptLayer.this));
//			ConstPara.pins.add(new Pin(ConstPara.SCREEN_HEIGHT
//					- Math.abs(nextPinDist), ScriptLayer.this));
			nextPinDist += Util.randh(.025f, .025f);
		}
	}


	@Override
	public void jDraw() {
		GL10 gl = Util.getGlInstance();
		gl.glColor4f(1.0f, 0.0f, 0.0f, .5f);
		gl.glLineWidth(1.0f);
		Primitives.drawLine(ConstPara.BASE_LINE_L, ConstPara.BASE_LINE_R);

		updateActors(ConstPara.balloons);
		ConstPara.pinMaxY = ConstPara.SCREEN_HEIGHT;
		updateActors(ConstPara.bonuses);
		updateActors(ConstPara.pins);
		

		Particle.updateParticles();
		if (!isGameOver) {
			float dlt = ConstPara.pinMaxY - ConstPara.LOW_V_AREA;
			if (dlt > 0) {
				scroll(.1f * dlt);
			}

			scroll(ConstPara.rank * .5f);
		}
	}


	@Override
	public void jOnEnter() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void jOnEnterTransitionDidFinish() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void jOnExit() {
		// TODO Auto-generated method stub
		
	}

}
