package com.example.demo.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	//get all student
	@GetMapping
	public List<Student> getAllStudents(){
		return this.studentRepository.findAll();
	}
	//get student by id
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable (value = "id") long studentId) {
		return this.studentRepository.findById(studentId)
				.orElseThrow( () -> new ResourceNotFoundException("stduent not found"+ studentId));
				
	}
	//create student 
	@PostMapping
    public Student createStudent(@RequestBody Student student) {
    	return this.studentRepository.save(student);
    }
	//update student  Id, name, class, teacher, subject and marks.
    @PutMapping("{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable ("id") long studentId) {
   Student existingStudent = 	this.studentRepository.findById(studentId)
		.orElseThrow( () -> new ResourceNotFoundException("stduent not found"+ studentId));
   existingStudent.setName(student.getName());
   existingStudent.setGrade(student.getGrade());
   existingStudent.setTeacher(student.getTeacher());
   existingStudent.setSubject(student.getSubject());
    existingStudent.setMarks(student.getMarks());
 return  this.studentRepository.save(existingStudent);
    	
    }
	//delete student by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Student>deleteStudent(@PathVariable ("id") long studentId){
    	 Student existingStudent = 	this.studentRepository.findById(studentId)
    				.orElseThrow( () -> new ResourceNotFoundException("stduent not found"+ studentId));
    	 this.studentRepository.delete(existingStudent);
    	 return ResponseEntity.ok().build();
    }
	

}
