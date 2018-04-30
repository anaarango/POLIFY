package com.polify.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.polify.entity.Student;

@ManagedBean @SessionScoped
public class StudentBean {

	Student student = new Student();

	public String createStudentForm() {
		System.out.println("Reading Student Details - Name: " + student.getFirstName() + " " + student.getLastName() + ", Standard: " + student.getStandard());
		return "output";
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}