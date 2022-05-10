package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

//Id, name, class, teacher, subject and marks.


//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@TestMethodOrder(OrderAnnotation.class)

@DataJpaTest
public class studentTests {
	
	@Autowired
	private StudentRepository repo;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateStudent() {
		Student student = new Student("shikha", 5, "ram", "eng", 85);
	Student saveStudent =	repo.save(student);
	assertNotNull (saveStudent);
	}
	
	@Test
	@Order(2)
	public void testFindStudentByNameExist() {
		String name = "shikha";
		Student student = repo.findByName(name);
		
		assertThat(student.getName()).isEqualTo(name);
		}
	@Test
	@Order(3)
	public void testFindStudentByNameNotExist() {
		String name = "Ash";
		Student student = repo.findByName(name);
		
		assertNull(student);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	public void testUpdateStudent() {
		String studentName = "Rama";
		Student student = new Student (studentName,8,"Tony","Hindi",75);
		student.setId(3);
		
		repo.save(student);
		Student updateStudent = repo.findByName(studentName);
		
		assertThat(updateStudent.getName()).isEqualTo(studentName);
	}
	
	@Test
	@Order(5)
	public void testListStudents () {
		List<Student> students = (List<Student>) repo.findAll();
		
	/*	for (Student student : students) {
			System.out.println(student);
		} */
		
		assertThat(students).size().isGreaterThan(0);
		
		
	}
	@Test
    @Rollback(false)
	@Order(6)
	public void testDeleteStudent () {
		long id = 1;
		boolean isExistBeforeDelete = repo.findById(id).isPresent();
		repo.deleteById(id);
		boolean notExistAfterDelete = repo.findById(id).isPresent();
		
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
	}
	
	
}
