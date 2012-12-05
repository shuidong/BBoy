package com.twituji.bboy.character;

import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYPoint;

/**
 * @author dlqingxi
 * 
 */
public abstract class Actor {
	public Sprite sprite;
	public WYPoint pos = WYPoint.make(0f, 0f);
	public Layer layer;
	public float size;

	void initialize() {
	}

	/**
	 * This method should be ovrride, it updates actor's status.
	 * @param For Scheduler Calling, this param is need, can be set as 0f.
	 * @return If the value is true, main thread will repaint the actor, 
	 * or it will remove the actor.
	 */
	abstract public boolean update();

	/**
	 * Once the object is destroyed, sprite should be remove
	 */
	public void removeSprite() {
		layer.removeChild(sprite, false);
	}
}
