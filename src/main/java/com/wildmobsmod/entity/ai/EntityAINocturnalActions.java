package com.wildmobsmod.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;

/**
 * Wrapper to make any {@link EntityAIBase} night-only
 */
public class EntityAINocturnalActions extends EntityAIBase {
	private final EntityCreature taskOwner;
	private final EntityAIBase task;
	private final float nightBrightness;
	private final boolean interruptAtDawn;
	
	public EntityAINocturnalActions(EntityCreature taskOwner, EntityAIBase task, float nightBrightness, boolean interruptAtDawn) {
		this.taskOwner = taskOwner;
		this.task = task;
		this.nightBrightness = nightBrightness;
		this.interruptAtDawn = interruptAtDawn;
	}
	
	protected boolean isNight() {
		return taskOwner.getBrightness(1.0F) < nightBrightness;
	}
	
	protected boolean shouldInterrupt() {
		return interruptAtDawn && !isNight();
	}

	@Override
    public boolean shouldExecute() {
    	return isNight() && task.shouldExecute();
    }

	@Override
    public boolean continueExecuting() {
    	return shouldInterrupt() ? false : task.continueExecuting();
    }

	@Override
    public boolean isInterruptible() {
		return task.isInterruptible();
	}

    @Override
    public void startExecuting() {
    	task.startExecuting();
    }

    @Override
    public void resetTask() {
    	task.resetTask();
    }

    @Override
    public void updateTask() {
    	task.updateTask();
    }

    @Override
    public void setMutexBits(int mutex) {
    	task.setMutexBits(mutex);
    }

    @Override
    public int getMutexBits() {
    	return task.getMutexBits();
    }
}