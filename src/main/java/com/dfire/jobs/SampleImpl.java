package com.dfire.jobs;

import org.springframework.stereotype.Component;

@Component
public class SampleImpl implements Sample {
	
	public void print(){
		System.out.println("this is a Sample");
	}
}
