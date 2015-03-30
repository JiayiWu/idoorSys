package com.idoorSys;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String args[]) throws Exception {
		new ClassPathXmlApplicationContext("contextTest.xml");
	}

}
