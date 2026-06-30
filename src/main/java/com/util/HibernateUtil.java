package com.util;

import org.hibernate.cfg.Configuration;

import org.hibernate.SessionFactory;


public class HibernateUtil {
	
	
	private static SessionFactory sessionFactory;
	
	
//	static block
	static {
		
		try {
//			create SessionFactory from hibernate.cfg.xml
			sessionFactory=new Configuration()
					           .configure("hibernate.cfg.xml")
					           .buildSessionFactory();
			
		
		}
		catch(Exception e)
		{
			System.out.println("SessionFactory Failed :"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	
//	Global access method
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

}
