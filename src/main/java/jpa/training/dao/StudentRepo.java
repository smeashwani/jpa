package jpa.training.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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

	public void findAndUpdate(int id) {
		System.out.println(">>>>>>>>>>>>>>> findAndUpdate >>>>>>>>>>>>>");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Student find = entityManager.find(Student.class, Integer.valueOf(id));
		System.out.println("Find: " + find);
		find.setName(find.getName() + "123");
		System.out.println("Update: " + find);
		tx.commit();
		System.out.println("<<<<<<<<<<<<<<  findAndUpdate <<<<<<<<<<<<<<");
	}

	public void findAndDelete(int id) {
		System.out.println(">>>>>>>>>>>>>>> findAndDelete >>>>>>>>>>>>>");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Student find = entityManager.find(Student.class, Integer.valueOf(id));
		System.out.println("Find: " + find);
		find.setName(find.getName() + "123");
		entityManager.remove(find);
		tx.commit();
		System.out.println("<<<<<<<<<<<<<<  findAndDelete <<<<<<<<<<<<<<");
	}
	
	public List<Student> findAll() {
		return entityManager.createQuery("select s from Student s  order by s.age desc", Student.class).getResultList();
	}

}
