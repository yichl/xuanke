package com.neusoft.xk.view;

import com.neusoft.xk.po.Student;

public interface StudentView {
	
	public void saveStudent();
	
	public Student studentLogin();
	
	public void liststudent_course(Student student);
	
	public void student_course(Student student);
}
