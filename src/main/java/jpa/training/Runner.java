package jpa.training;

import java.util.List;

import jpa.training.dao.StudentRepo;
import jpa.training.entity.Student;

public class Runner {
	static StudentRepo stuRepo = new StudentRepo();
	
	public static void main(String[] args) {
		save();
		//stuRepo.findAndUpdate(1);
		stuRepo.findAndDelete(3);
		System.out.println("Operation completed");
		
		List<Student> findAll = stuRepo.findAll();
		findAll.forEach(System.out::println);
	}

	private static void save() {
		Student stu1 = new Student("Gaurav",2400,24);
		Student stu2 = new Student("Ronit",2500,25);
		Student stu3 = new Student("Rahul",2600,26);
		stuRepo.persist(stu1);
		System.out.println("Successully save in db" + stu1);
		stuRepo.persist(stu2);
		System.out.println("Successully save in db" + stu2);
		stuRepo.persist(stu3);
		System.out.println("Successully save in db" + stu3);
	}
}
