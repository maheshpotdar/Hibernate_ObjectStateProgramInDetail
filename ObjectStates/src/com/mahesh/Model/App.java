package com.mahesh.Model;

import java.beans.Transient;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {

		Employee employee = new Employee();
		employee.setEname("Mahesh");
		employee.setAddress("Khotwadi");

		// he sessionfactory mi tula database deto hibernate.cfg.xml ani table pan deto
		// (Class) hibernate madhe table manaje Class.
		SessionFactory factor = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factor.openSession();
		org.hibernate.Transaction t1 = session.beginTransaction();
		// operation kara mananyasathi trasaction cha object lagato insert,update,delete
		// madhe transaction.commit matalyavar
		// opration execute hoto. transaction.commit manaje executeUpdate/execute()
		// select madhe transaction chi garaj nahi.Automatically operation hoto.

		// Object states
		// 1.Transient State(ekadhya class cha object tayar kela manaje transient
		// state.)

		System.out.println("Object-Transient state.");
		// table tayar kara object tayar kara ani session madhe insert la dya
		// run kara table tayar.
		// transient state.
		session.save(employee);// query add keli
		System.out.println("Object-Persistent state.");
		// session(hibernate) madhe object dilavar transient to Persistent state.
//		// 2.Persistant state
//		hibernate employee chi copy database Madhe copy insert karato.so original object
//      remove karanasathi evict use kara.after commit .copy insert honar ani original delete honar .so object now in detached state.
		// original object remove// copy insert honar compulsary.

		t1.commit();// executeUpdate();
		session.evict(employee);
		System.out.println("Table created successfully.");
//		operation execute kara.
//		System.out.println("Table created successfully.");
//		persistant manaje to object hibernate madhe ahe ata insert zalyavar
//		to object hibernate madhun kadun ghetlyavar to detached state.

		session.close();
		factor.close();

	}

}
