package jpa.training.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
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
	
	public Student find(int id) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Student find = entityManager.find(Student.class, id);
		tx.commit();
		return find;
	}

	public List<Student> findAll() {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		TypedQuery<Student> createQuery = entityManager.createQuery("select s from Student s  order by s.age desc", Student.class);
		createQuery.setLockMode(LockModeType.WRITE);
		List<Student> resultList = createQuery.getResultList();
		resultList.get(0).setAge(25);
		tx.commit();
		return resultList;
	}
	
}
