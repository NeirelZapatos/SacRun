package com.csus.csc133;
import java.util.*;

public class ConfusedStrategy implements Strategy{
	private StudentStrategy strategyStudent;
	private Random random = new Random();
	
	//constructor
	public ConfusedStrategy(StudentStrategy strategyStudent) {
		this.strategyStudent = strategyStudent;
	}
	
	//apply's confused strategy to strategyStudent
	@Override
	public void apply() {
		// TODO Auto-generated method stub
		strategyStudent.setHead(strategyStudent.getHead() + random.nextInt(50));
	}
}
