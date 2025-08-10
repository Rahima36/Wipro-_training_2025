package com.wipro.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    	
    	
    	College college = (College) context.getBean("collegeBean");
        System.out.println(college);
        }
}
