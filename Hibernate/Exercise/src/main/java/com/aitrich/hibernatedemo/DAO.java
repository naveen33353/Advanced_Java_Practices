package com.aitrich.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO {

	
	public void saveStudents(String name, int age) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Students s = new Students();
		
		s.setName(name);
		s.setAge(age);
		
		session.persist(s);
		
		tx.commit();
		
		session.close();
	}
	
	
	
	public void readStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Students students = session.get(Students.class,2);
		System.out.println(students.getName());
		
		session.close();
	}
	
	
	
	
	public void updateStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Students student = session.get(Students.class, 1);
		
		session.merge(student); 
		
		tx.commit();
		session.close();
	}
	
	
	
	public void deleteStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Students student = session.get(Students.class, 2);
		session.remove(student);

		tx.commit();
		session.close();

	}
	
	
}
