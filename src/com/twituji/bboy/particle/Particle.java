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
package com.twituji.bboy.particle;

import javax.microedition.khronos.opengles.GL10;

import com.twituji.bboy.Util;
import com.twituji.bboy.config.ConstPara;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.opengl.Primitives;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYRect;

/**
 * @author dlqingxi
 *
 */
public class Particle {
    public static WYRect rect = WYRect.makeZero();
    public static int drawIndex, color;
    public WYPoint pos = WYPoint.make(0f, 0f), vel = WYPoint.make(0f, 0f);
    public float size, attenuation;
    
    private float red,green,blue;
    
    public Particle(WYPoint p, float vx, float vy, float size, float attenuation) {
        pos.x = p.x; pos.y = p.y; 
        vel.x = vx; vel.y = vy;
        this.size = size + 0.9f;
        this.attenuation = attenuation;
        red = 0f;//(float)Math.random();
        green = 0f;//(float)Math.random();
        blue = 1f;//(float)Math.random();
    }
    
    public boolean update() {
        pos.x += vel.x; 
        pos.y += vel.y;
        size *= attenuation;
        return Util.isInScreen(pos) && size >= 1.0;
    }

    public static void setDrawIndex(int i) {
        drawIndex = i;
//        int bright = 0xff - i * 0x55;
//        color = bright * 0x10000 + bright * 0x100 + bright;
    }
    
    public void draw() {
        float sz = size * (1.0f + drawIndex * 0.5f);
        rect.origin.x = pos.x - sz / 2; rect.origin.y = pos.y - sz / 2;
        rect.size.width = rect.size.height = sz;
        
        GL10 gl = Util.getGlInstance();
        gl.glColor4f(red, green, blue, (float)(.3f + 0.7f * Math.random()));
        Primitives.drawRect(rect);
//        Primitives.drawCircle(pos.x, pos.y, sz/2, 0, 8, false);
    }
    
    public static void updateParticles() {
        int i;
        for (i = 0; i < ConstPara.particles.size(); i++){
        	if (!ConstPara.particles.get(i).update()) {
        		ConstPara.particles.remove(i);
        	}
        }
        	
        for (i = 0; i >= 0; i--) {
            setDrawIndex(i);
            for (Particle p : ConstPara.particles){
            	p.draw();
            }
        }
    }
    
    public static void addParticles(int n, WYPoint p, float vx, float vy, float size, float attenuation, float spreading) {
        float bv = (Math.abs(vx) + Math.abs(vy)) * spreading;
        for (int i = 0; i < n; i++) {
            double a = (Math.random() * (Math.PI * 2));
            float v = (float) (Math.random() * bv);
            ConstPara.particles.add(new Particle(p, (float) (vx + Math.sin(a) * v), (float) (vy + Math.cos(a) * v), (float) (size * (0.5 + Math.random())), attenuation));
        }
    }
    
    public static void addParticlesAngle(int n, WYPoint p, double a, float s, float size, float attenuation, float spreading) {
        addParticles(n, p, (float)Math.sin(a) * s, (float)Math.cos(a) * s, size, attenuation, spreading);
    }
    
    public static void addParticlesRound(int n, WYPoint p, float mv, float size, float attenuation) {
        for (int i = 0; i < n; i++) {
        	double a = (Math.random() * (Math.PI * 2));
        	float v = (float) (Math.random() * mv);
        	 ConstPara.particles.add(new Particle(p, (float)Math.sin(a) * v, (float)Math.cos(a) * v, (float)(size * (0.5 + Math.random())), attenuation));
        }
    }
    
    public static void scrollParticles(float vx, float vy) {
        for (Particle p : ConstPara.particles) {
            p.pos.x += vx; p.pos.y -= vy;
        }
    }
}
