package com.neusoft.xk.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.xk.dao.CourseDAO;
import com.neusoft.xk.dao.StudentDAO;
import com.neusoft.xk.dao.TeacherDAO;
import com.neusoft.xk.dao.impl.CourseDAOimpl;
import com.neusoft.xk.dao.impl.StudentDAOimpl;
import com.neusoft.xk.dao.impl.TeacherDAOimpl;
import com.neusoft.xk.po.Course;
import com.neusoft.xk.po.Student;
import com.neusoft.xk.view.StudentView;

public class StudentViewimpl implements StudentView {
	private StudentDAO sdao = new StudentDAOimpl();
	private Scanner input = new Scanner(System.in);
	private CourseDAO cdao = new CourseDAOimpl();
	private TeacherDAO tdao = new TeacherDAOimpl();
	
	public void saveStudent() {
		System.out.println("请输入学生姓名:");
		String studentName = input.next();
		System.out.println("请输入学生班级:");
		String studentClass = input.next();
		Student student = new Student();
		student.setStudentName(studentName);
		student.setStudentClass(studentClass);
		
		int count = sdao.saveStudent(student);
		if(count > 0) {
			System.out.println("学生信息注册成功！您的学号为："+count);
		}else {
			System.out.println("学生信息注册失败！");
		}
	}
	
	public Student studentLogin() {
		System.out.println("请输入学生学号:");
		int studentId = input.nextInt();
		System.out.println("请输入学生姓名:");
		String studentName = input.next();
		
		Student student = sdao.studentLogin(studentId, studentName);
		return student;
		
	}
	
	public void liststudent_course(Student student) {
		List<Course> clist = sdao.liststudent_course(student);
		if(clist.size() == 0) {
			System.out.println("您还没有选择课程内容！");
		}else {
			System.out.println("课程编号\t课程名称\t课程课时\t讲授教师");
			for(Course c:clist) {
				System.out.println(c.getCourseId()+"\t"+c.getCourseName()+"\t"+c.getCourseHour()+"\t"+tdao.teacherById(c.getTeacherId()).getTeacherName());
			}
		}
	}
	
	public void student_course(Student student) {
		String inStr = null;
		String courseName = null;
		String teacherName = null;
		System.out.println("是否根据课程名称进行选课?(y/n)");
		inStr = input.next();
		if(inStr.equalsIgnoreCase("y")) {
			System.out.println("请输入课程名称:");
			courseName = input.next();
		}
		System.out.println("是否根据教师姓名进行选课?(y/n)");
		inStr = input.next();
		if(inStr.equalsIgnoreCase("y")) {
			System.out.println("请输入教师姓名:");
			teacherName = input.next();
		}
		List<Course> clist = sdao.student_course(courseName, teacherName);
	
		if(clist.size() == 0) {
			System.out.println("没有符合您要求的课程内容！");
		}else {
			System.out.println("课程编号\t课程名称\t课程课时\t讲授教师");
			for(Course c:clist) {
				System.out.println(c.getCourseId()+"\t"+c.getCourseName()+"\t"+c.getCourseHour()+"\t"+tdao.teacherById(c.getTeacherId()).getTeacherName());
			}
		}
		System.out.println("请选择您要学习的课程编号：");
		int courseId = input.nextInt();
		if(sdao.courseByCidSid(courseId, student.getStudentId())) {
			System.out.println("此课程您已经选过，请继续选择其他课程学习");
		}else {
			Course course = cdao.courseById(courseId);
			System.out.println(course.toString());
			System.out.println("是否确认选择此课程学习？(y/n)");
			String str = input.next();
			if(str.equalsIgnoreCase("y")) {
				int count = sdao.saveStudent_Course(student.getStudentId(), courseId);
				if(count > 0) {
					System.out.println("该课程已经选课成功！");
				}else {
					System.out.println("该课程选课失败！");
				}
			}
		}
	}
}
