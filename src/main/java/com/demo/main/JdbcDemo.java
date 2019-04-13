package com.demo.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.jdbc.JdbcDaoImpl;
import com.demo.jdbc.JdbcDaoSupportImpl;
import com.demo.model.Circle;

public class JdbcDemo {

	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl jdi = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		JdbcDaoSupportImpl jdsi = context.getBean("jdbcDaoSupport", JdbcDaoSupportImpl.class);
//		Circle circle = new Circle(3, "Third Circle");
		//System.out.println(circle.getName());
//		System.out.println(jdi.getCircleCount());
//		System.out.println(jdi.getCircleName(1));
//		System.out.println(jdi.getCircleById(1).getName());
//		System.out.println(jdi.insertCircle(circle));
//		System.out.println(jdi.insertCirclebyNamedParameters(circle));
//		System.out.println(jdi.getrAllCircles().size());
//		System.out.println(jdi.createTriangleTable());
		
		
		System.out.println(jdsi.getCircleCount());
	}

}
