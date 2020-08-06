package com.neusoft.xk;

import java.util.Scanner;

import com.neusoft.xk.po.Student;
import com.neusoft.xk.view.StudentView;
import com.neusoft.xk.view.impl.StudentViewimpl;

public class XkStudentSystem {

	private Scanner input = new Scanner(System.in);
	private StudentView sview = new StudentViewimpl();
	
	public void work() {
		System.out.println("欢迎登陆东软学生选课管理系统");
		int num = 0;
		while(num != 3) {
			System.out.println("-----------------1.学生注册  2.学生登录  3.退出系统-----------------");
			num = input.nextInt();
			switch (num) {
				case 1:
					sview.saveStudent();
					break;
				case 2:
					Student student = sview.studentLogin();
					if(student != null) {
						studentMange(student);
					}else {
						System.out.println("学生学号和学生姓名不匹配，登录失败！");
					}
					break;
				case 3:
					System.out.println("谢谢使用东软学生选课系统");
					break;
	
				default:
					break;
			}
		}
	}
	
	public void studentMange(Student student) {
		int num = 0;
		while(num != 3) {
			System.out.println("-----------------（学生选课管理）1.学生选课  2.查询已选课程  3.返回上一级-----------------");
			num = input.nextInt();
			switch (num) {
			case 1:
				sview.student_course(student);
				break;
			case 2:
				sview.liststudent_course(student);
				break;
			case 3:
				
				break;
			default:
				break;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new XkStudentSystem().work();
	}

}
