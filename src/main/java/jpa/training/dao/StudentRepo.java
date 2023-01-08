package jpa.training.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jpa.training.entity.Student;
import jpa.training.utils.PersistenceUtils;

public class StudentRepo {

	EntityManager entityManager = PersistenceUtils.getEntityManager();

	public void persist(Student stu) {
		System.out.println(">>>>>>>>>>>>>>> persist >>>>>>>>>>>>>");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(stu);
		tx.commit();
		System.out.println("<<<<<<<<<<<<<<  persist <<<<<<<<<<<<<<");
	}

	public List<Student> findAll() {
		return entityManager.createQuery("select s from Student s  order by s.age desc", Student.class).getResultList();
	}
	
}
