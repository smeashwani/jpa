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
	
	
	public List<Student> findByCriteria() {
		System.out.println(">>>>>>>>>>>>>>> findByCriteria >>>>>>>>>>>>>");
		EntityTransaction tx = entityManager.getTransaction();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> stud = query.from(Student.class);
		query.orderBy(builder.desc(stud.get("name")));
		
		
		query.where(builder.lessThan(stud.get("id"), 2));
		//query.where(builder.between(stud.get("id"), 1, 3)) ;  
		//query.where(builder.like(stud.get("name"), "R%")); 
		//query.where(builder.in(stud.get("id")).value(1).value(2));  

		CriteriaQuery<Student> multiselect = query.multiselect(stud.get("id"),
		stud.get("name"),stud.get("fees"),stud.get("age"));
		TypedQuery<Student> createQuery = entityManager.createQuery(multiselect);
		List<Student> resultList = createQuery.getResultList();
		System.out.println("<<<<<<<<<<<<<<  findByCriteria <<<<<<<<<<<<<<");
		return resultList;
		}


}
