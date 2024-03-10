package com.csus.csc133;
import java.util.*;

public class VerticalStrategy implements Strategy{
	private StudentStrategy strategyStudent;
	private Random random = new Random();
	
	public VerticalStrategy(StudentStrategy strategyStudent) {
		this.strategyStudent = strategyStudent;
	}

	@Override
	public void apply() {
		// TODO Auto-generated method stub
		if(strategyStudent.getHead() != 0 && strategyStudent.getHead() != 180) {
			if(random.nextInt(2) == 0) {
				strategyStudent.setHead(0);
			}
			else {
				strategyStudent.setHead(180);
			}
		}
	}
}