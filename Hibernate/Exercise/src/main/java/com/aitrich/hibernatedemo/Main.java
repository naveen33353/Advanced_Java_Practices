package com.aitrich.hibernatedemo;

public class Main {
public static void main(String[] args) {
	DAO dao = new DAO();
	
//	dao.saveStudents("Naveen",21);
	
//	System.out.println("SessionFactory = " + HibernateUtil.getSessionFactory());
//	ClassLoader cl = Thread.currentThread().getContextClassLoader();
//	System.out.println("CFG FILE = " + cl.getResource("hibernate.cfg.xml"));
//  dao.readStudents();
	
	dao.deleteStudents();
	
}

}
