package jpa.training;

import java.util.List;

import jpa.training.dao.StudentRepo;
import jpa.training.entity.Student;

public class Runner {
	static StudentRepo stuRepo = new StudentRepo();
	
	public static void main(String[] args) {
		save();
		List<Student> findAll = stuRepo.findAll();
		findAll.forEach(System.out::println);
		
		System.out.println("Operation completed");
		
	}

	private static void save() {
		Student stu1 = new Student("Gaurav",2400,24);
		stuRepo.persist(stu1);
		System.out.println("Successully save in db" + stu1);
	}
}
