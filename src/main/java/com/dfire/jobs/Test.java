package com.dfire.jobs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./META-INF/jobs.xml");
		Object object =ctx.getBean(com.dfire.jobs.Sample.class);
		System.out.println(object);
	}

}
