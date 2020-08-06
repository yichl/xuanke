package com.neusoft.xk.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.xk.dao.CourseDAO;
import com.neusoft.xk.dao.TeacherDAO;
import com.neusoft.xk.dao.impl.CourseDAOimpl;
import com.neusoft.xk.dao.impl.TeacherDAOimpl;
import com.neusoft.xk.po.Course;
import com.neusoft.xk.po.Student;
import com.neusoft.xk.po.Teacher;
import com.neusoft.xk.view.CourseView;
import com.neusoft.xk.view.TeacherView;

public class CourseViewimpl implements CourseView {
	private Scanner input = new Scanner(System.in);
	private CourseDAO cdao = new CourseDAOimpl();
	private TeacherDAO tdao = new TeacherDAOimpl();
	private TeacherView tview = new TeacherViewimpl();
	//��ӿγ�ҵ�񷽷�
	public void saveCourse(){
		System.out.println("������γ�����:");
		String courseName = input.next();
		System.out.println("�������ʱ");
		int courseHour = input.nextInt();
		tview.listTeacherAll(); //���ý�ʦȫ����ʾ��ҵ�񷽷�
		System.out.println("������˿γ̽��ڽ�ʦ���:");
		int teacherId = input.nextInt();
		
		Course course = new Course();
		course.setCourseName(courseName);
		course.setCourseHour(courseHour);
		course.setTeacherId(teacherId);
		int count = cdao.saveCourse(course);
		if(count > 0){
			System.out.println("�γ���ӳɹ���");
		}else{
			System.out.println("�γ����ʧ�ܣ�");
		}
	}
	//��ѯ���пγ�ҵ�񷽷�
	public void listCourseAll(){
		List<Course> clist = cdao.listCourseAll();
		if(clist.size() == 0){
			System.out.println("Ŀǰ���޿γ���Ϣ");
		}else{
			System.out.println("�γ̱��\t�γ�����\t\t�γ̿�ʱ\t���ڽ�ʦ");
			for(Course c:clist){
				System.out.println(c.getCourseId()+"\t"+c.getCourseName()+"\t\t"+c.getCourseHour()+"\t"+tdao.teacherById(c.getTeacherId()).getTeacherName());
			}
		}
	}
	//�޸Ŀγ���Ϣҵ�񷽷�
	@Override
	public void updateCourse() {
		// TODO Auto-generated method stub
		listCourseAll();
		System.out.println("��������Ҫ�޸ĵĿγ̱��:");
		int courseId = input.nextInt();
		Course course = cdao.courseById(courseId);
		
		if(course == null){
			System.out.println("û����Ҫ�޸ĵĿγ���Ϣ");
		}else{
			String str = null;
			System.out.println(course.toString());
			System.out.println("�Ƿ��޸Ŀγ�����?(y/n)");
			str = input.next();
			if(str.equalsIgnoreCase("y")){
				System.out.println("�������µĿγ�����:");
				String courseName = input.next();
				course.setCourseName(courseName);
			}
			System.out.println("�Ƿ�˿γ̶�Ӧ��ʱ?(y/n)");
			str = input.next();
			if(str.equalsIgnoreCase("y")){
				System.out.println("�������µĿγ̿�ʱ:");
				int courseHour = input.nextInt();
				course.setCourseHour(courseHour);
			}
			
			int count = cdao.updateCourse(course);
			if(count > 0){
				System.out.println("�γ���Ϣ�޸ĳɹ���");
			}else{
				System.out.println("�γ���Ϣ�޸�ʧ�ܣ�");
			}
		}
	}
	//ɾ���γ�ҵ�񷽷�
	@Override
	public void deleteCourse() {
		// TODO Auto-generated method stub
		listCourseAll();
		System.out.println("��������Ҫɾ���Ŀγ̱��:");
		int courseId = input.nextInt();
		Course course = cdao.courseById(courseId);
		System.out.println("�Ƿ�ȷ��Ҫɾ���˿γ�?(y/n)");
		String str = input.next();
		if(str.equalsIgnoreCase("y")){
			if(course == null){
				System.out.println("û����Ҫɾ���Ŀγ���Ϣ");
			}else{
				int count = cdao.deleteCourse(courseId);
				if(count > 0){
					System.out.println("�γ���Ϣɾ���ɹ���");
				}else{
					System.out.println("�γ���Ϣɾ��ʧ�ܣ�");
				}
			}
		}
		
	}
	//��ѯĳһ�γ���ѧ����Ϣҵ�񷽷�
	@Override
	public void listCourse_student() {
		// TODO Auto-generated method stub
		System.out.println("��������Ҫ�鿴�Ŀγ̱��:");
		int courseId = input.nextInt();
		Course course= cdao.courseById(courseId);
		if(course == null){
			System.out.println("û����Ҫ�鿴�Ŀγ���Ϣ");
		}else{
			List<Student> slist = cdao.listCourse_student(course.getCourseId());
			System.out.println("ѧ�����\tѧ������\tרҵ�༶");
			for(Student s:slist){
				System.out.println(s.getStudentId()+"\t"+s.getStudentName()+"\t"+s.getStudentClass());
			}
		}
	}
}
