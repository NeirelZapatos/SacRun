package com.csus.csc133;

//determines if game object can move
public interface IMoveable {
	public void move(ViewMap viewMap, double MsToSec, double timeSecond);
}