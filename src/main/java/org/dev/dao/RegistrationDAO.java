package org.dev.dao;

import org.dev.dto.UserDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationDAO {

	@Autowired
	private SessionFactory factory;

	public Long saveUser(UserDTO userDTO) {
		Long identifier = null;
		Transaction transaction = null;

		try (Session session = factory.openSession();) {
			transaction = session.beginTransaction();
			identifier = (Long) session.save(userDTO);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return identifier;
	}
}
