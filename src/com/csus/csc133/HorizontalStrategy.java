package com.csus.csc133;
import java.util.*;

public class HorizontalStrategy implements Strategy{
	private StudentStrategy strategyStudent;
	private Random random = new Random();
	
	//constructor
	public HorizontalStrategy(StudentStrategy strategyStudent) {
		this.strategyStudent = strategyStudent;
	}

	//applys strategy to studentStrategy
	@Override
	public void apply() {
		// TODO Auto-generated method stub
		if(strategyStudent.getHead() != 90 && strategyStudent.getHead() != 270) {
			if(random.nextInt(2) == 0) {
				strategyStudent.setHead(90);
			}
			else {
				strategyStudent.setHead(270);
			}
		}
	}
}