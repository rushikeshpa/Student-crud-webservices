package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "student")
public class Student {
	//  Id, name, class, teacher, subject and marks.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "name")
	private String name;
	
	@Column(name = "grade")
	private int grade;
	
	@Column(name = "teacher")
	private String teacher;
	@Column(name = "subject")
	private String subject;
	@Column(name = "marks")
	private double marks;
	
	public Student() {
		
	}
	
	
	public Student(String name, int grade, String teacher, String subject, double marks) {
		super();
		this.name = name;
		this.grade = grade;
		this.teacher = teacher;
		this.subject = subject;
		this.marks = marks;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	
	
	

}
