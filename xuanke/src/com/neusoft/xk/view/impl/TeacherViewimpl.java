package com.neusoft.xk.view.impl;


import java.util.List;
import java.util.Scanner;

import com.neusoft.xk.dao.TeacherDAO;
import com.neusoft.xk.dao.impl.TeacherDAOimpl;
import com.neusoft.xk.po.Student;
import com.neusoft.xk.po.Teacher;
import com.neusoft.xk.view.TeacherView;

public class TeacherViewimpl  implements TeacherView {
	private Scanner input = new Scanner(System.in);
	private TeacherDAO tdao = new TeacherDAOimpl();
	
	public void listTeacherAll(){
		List<Teacher> tlist = tdao.listTeacherAll();
		if(tlist.size() == 0){
			System.out.println("Ŀǰ���޽�ʦ��¼");
		}else{
			System.out.println("��ʦ���\t��ʦ����\t��������");
			for(Teacher t:tlist){
				System.out.println(t.getTeacherId()+"\t"+t.getTeacherName()+"\t"+t.getTechnology());
			}
		}
	}
	public void saveTeacher(){
		System.out.println("�������ʦ����:");
		String teacherName = input.next();
		System.out.println("�������ʦ��������");
		String technology = input.next();
		
		Teacher teacher = new Teacher();
		teacher.setTeacherName(teacherName);
		teacher.setTechnology(technology);
		int count = tdao.saveTeacher(teacher);
		if(count > 0){
			System.out.println("��ʦ��ӳɹ���");
		}else{
			System.out.println("��ʦ���ʧ�ܣ�");
		}
	}
	
	public void updateTeacher(){
		System.out.println("��������Ҫ�޸ĵĽ�ʦ���:");
		int teacherId = input.nextInt();
		Teacher teacher = tdao.teacherById(teacherId);
		
		if(teacher == null){
			System.out.println("û����Ҫ�޸ĵĽ�ʦ");
		}else{
			String str = null;
			System.out.println(teacher.toString());
			System.out.println("�Ƿ��޸Ľ�ʦ����?(y/n)");
			str = input.next();
			if(str.equalsIgnoreCase("y")){
				System.out.println("�������µĽ�ʦ����:");
				String teacherName = input.next();
				teacher.setTeacherName(teacherName);
			}
			System.out.println("�Ƿ��޸Ľ�ʦ��������?(y/n)");
			str = input.next();
			if(str.equalsIgnoreCase("y")){
				System.out.println("�������µĽ�ʦ��������:");
				String technology = input.next();
				teacher.setTechnology(technology);
			}
			
			int count = tdao.updateteacher(teacher);
			if(count > 0){
				System.out.println("��ʦ��Ϣ�޸ĳɹ���");
			}else{
				System.out.println("��ʦ��Ϣ�޸�ʧ�ܣ�");
			}
		}
		
	}
	
	public void deleteTeacher(){
		System.out.println("��������Ҫɾ���Ľ�ʦ���:");
		int teacherId = input.nextInt();
		Teacher teacher = tdao.teacherById(teacherId);
		System.out.println("�Ƿ�ȷ��Ҫɾ���˿γ�?(y/n)");
		String str = input.next();
		if(str.equalsIgnoreCase("y")){
			if(teacher == null){
				System.out.println("û����Ҫɾ���Ľ�ʦ");
			}else{
				int count = tdao.deleteTeacher(teacherId);
				if(count > 0){
					System.out.println("��ʦ��Ϣɾ���ɹ���");
				}else{
					System.out.println("��ʦ��Ϣɾ��ʧ�ܣ�");
				}
			}
		}
	}
	
	public void listTeacher_student(){
		System.out.println("��������Ҫ�鿴�Ľ�ʦ���:");
		int teacherId = input.nextInt();
		Teacher teacher = tdao.teacherById(teacherId);
		if(teacher == null){
			System.out.println("û����Ҫ�鿴�Ľ�ʦ");
		}else{
			List<Student> slist = tdao.listTeacher_student(teacherId);
			System.out.println("ѧ�����\tѧ������\tרҵ�༶");
			for(Student s:slist){
				System.out.println(s.getStudentId()+"\t"+s.getStudentName()+"\t"+s.getStudentClass());
			}
		}
	}
}
