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
			System.out.println("目前暂无教师记录");
		}else{
			System.out.println("教师编号\t教师名称\t技术方向");
			for(Teacher t:tlist){
				System.out.println(t.getTeacherId()+"\t"+t.getTeacherName()+"\t"+t.getTechnology());
			}
		}
	}
	public void saveTeacher(){
		System.out.println("请输入教师姓名:");
		String teacherName = input.next();
		System.out.println("请输入教师技术方向");
		String technology = input.next();
		
		Teacher teacher = new Teacher();
		teacher.setTeacherName(teacherName);
		teacher.setTechnology(technology);
		int count = tdao.saveTeacher(teacher);
		if(count > 0){
			System.out.println("教师添加成功！");
		}else{
			System.out.println("教师添加失败！");
		}
	}
	
	public void updateTeacher(){
		System.out.println("请输入您要修改的教师编号:");
		int teacherId = input.nextInt();
		Teacher teacher = tdao.teacherById(teacherId);
		
		if(teacher == null){
			System.out.println("没有您要修改的教师");
		}else{
			String str = null;
			System.out.println(teacher.toString());
			System.out.println("是否修改教师姓名?(y/n)");
			str = input.next();
			if(str.equalsIgnoreCase("y")){
				System.out.println("请输入新的教师姓名:");
				String teacherName = input.next();
				teacher.setTeacherName(teacherName);
			}
			System.out.println("是否修改教师技术方向?(y/n)");
			str = input.next();
			if(str.equalsIgnoreCase("y")){
				System.out.println("请输入新的教师技术方向:");
				String technology = input.next();
				teacher.setTechnology(technology);
			}
			
			int count = tdao.updateteacher(teacher);
			if(count > 0){
				System.out.println("教师信息修改成功！");
			}else{
				System.out.println("教师信息修改失败！");
			}
		}
		
	}
	
	public void deleteTeacher(){
		System.out.println("请输入您要删除的教师编号:");
		int teacherId = input.nextInt();
		Teacher teacher = tdao.teacherById(teacherId);
		System.out.println("是否确认要删除此课程?(y/n)");
		String str = input.next();
		if(str.equalsIgnoreCase("y")){
			if(teacher == null){
				System.out.println("没有您要删除的教师");
			}else{
				int count = tdao.deleteTeacher(teacherId);
				if(count > 0){
					System.out.println("教师信息删除成功！");
				}else{
					System.out.println("教师信息删除失败！");
				}
			}
		}
	}
	
	public void listTeacher_student(){
		System.out.println("请输入您要查看的教师编号:");
		int teacherId = input.nextInt();
		Teacher teacher = tdao.teacherById(teacherId);
		if(teacher == null){
			System.out.println("没有您要查看的教师");
		}else{
			List<Student> slist = tdao.listTeacher_student(teacherId);
			System.out.println("学生编号\t学生姓名\t专业班级");
			for(Student s:slist){
				System.out.println(s.getStudentId()+"\t"+s.getStudentName()+"\t"+s.getStudentClass());
			}
		}
	}
}
