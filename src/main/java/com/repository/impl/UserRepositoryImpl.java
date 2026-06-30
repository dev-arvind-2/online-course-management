package com.repository.impl;

import org.hibernate.Session;

import com.entity.User;
import com.repository.UserRepository;
import com.util.HibernateUtil;

public class UserRepositoryImpl implements UserRepository{

	@Override
	public User login(String username, String password) {
		
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			
			return session.createQuery(
			        "FROM User WHERE username = :u AND password = :p",
			        User.class)
			        .setParameter("u", username)
			        .setParameter("p", password)
			        .uniqueResult();
					
			
		}
		
		
	}

}
