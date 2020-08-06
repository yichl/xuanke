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
		System.out.println("������ѧ������:");
		String studentName = input.next();
		System.out.println("������ѧ���༶:");
		String studentClass = input.next();
		Student student = new Student();
		student.setStudentName(studentName);
		student.setStudentClass(studentClass);
		
		int count = sdao.saveStudent(student);
		if(count > 0) {
			System.out.println("ѧ����Ϣע��ɹ�������ѧ��Ϊ��"+count);
		}else {
			System.out.println("ѧ����Ϣע��ʧ�ܣ�");
		}
	}
	
	public Student studentLogin() {
		System.out.println("������ѧ��ѧ��:");
		int studentId = input.nextInt();
		System.out.println("������ѧ������:");
		String studentName = input.next();
		
		Student student = sdao.studentLogin(studentId, studentName);
		return student;
		
	}
	
	public void liststudent_course(Student student) {
		List<Course> clist = sdao.liststudent_course(student);
		if(clist.size() == 0) {
			System.out.println("����û��ѡ��γ����ݣ�");
		}else {
			System.out.println("�γ̱��\t�γ�����\t�γ̿�ʱ\t���ڽ�ʦ");
			for(Course c:clist) {
				System.out.println(c.getCourseId()+"\t"+c.getCourseName()+"\t"+c.getCourseHour()+"\t"+tdao.teacherById(c.getTeacherId()).getTeacherName());
			}
		}
	}
	
	public void student_course(Student student) {
		String inStr = null;
		String courseName = null;
		String teacherName = null;
		System.out.println("�Ƿ���ݿγ����ƽ���ѡ��?(y/n)");
		inStr = input.next();
		if(inStr.equalsIgnoreCase("y")) {
			System.out.println("������γ�����:");
			courseName = input.next();
		}
		System.out.println("�Ƿ���ݽ�ʦ��������ѡ��?(y/n)");
		inStr = input.next();
		if(inStr.equalsIgnoreCase("y")) {
			System.out.println("�������ʦ����:");
			teacherName = input.next();
		}
		List<Course> clist = sdao.student_course(courseName, teacherName);
	
		if(clist.size() == 0) {
			System.out.println("û�з�����Ҫ��Ŀγ����ݣ�");
		}else {
			System.out.println("�γ̱��\t�γ�����\t�γ̿�ʱ\t���ڽ�ʦ");
			for(Course c:clist) {
				System.out.println(c.getCourseId()+"\t"+c.getCourseName()+"\t"+c.getCourseHour()+"\t"+tdao.teacherById(c.getTeacherId()).getTeacherName());
			}
		}
		System.out.println("��ѡ����Ҫѧϰ�Ŀγ̱�ţ�");
		int courseId = input.nextInt();
		if(sdao.courseByCidSid(courseId, student.getStudentId())) {
			System.out.println("�˿γ����Ѿ�ѡ���������ѡ�������γ�ѧϰ");
		}else {
			Course course = cdao.courseById(courseId);
			System.out.println(course.toString());
			System.out.println("�Ƿ�ȷ��ѡ��˿γ�ѧϰ��(y/n)");
			String str = input.next();
			if(str.equalsIgnoreCase("y")) {
				int count = sdao.saveStudent_Course(student.getStudentId(), courseId);
				if(count > 0) {
					System.out.println("�ÿγ��Ѿ�ѡ�γɹ���");
				}else {
					System.out.println("�ÿγ�ѡ��ʧ�ܣ�");
				}
			}
		}
	}
}
