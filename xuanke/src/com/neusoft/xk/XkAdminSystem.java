package com.neusoft.xk;

import java.util.Scanner;

import com.neusoft.xk.view.CourseView;
import com.neusoft.xk.view.TeacherView;
import com.neusoft.xk.view.impl.CourseViewimpl;
import com.neusoft.xk.view.impl.TeacherViewimpl;

public class XkAdminSystem {

	private Scanner input = new Scanner(System.in);
	private TeacherView tview = new TeacherViewimpl();
	private CourseView cview = new CourseViewimpl();
	//启动管理员后台工作方法
	public void work(){
		
		System.out.println("欢迎登陆东软学生选课后台管理系统");
		int num = 0;
		while(num!=3){
			System.out.println("-------------1.课程管理  2.教师管理  3.退出系统-------------");
			num = input.nextInt();
			switch (num) {
				case 1:
					courseManage();
					break;
				case 2:
					teacherManage();			
					break;
				case 3:
					System.out.println("欢迎再次使用本系统");
					break;
		
				default:
					break;
			}
		}
	}
	//课程管理功能模块
	public void courseManage(){
		int num = 0;
		while(num != 6){
			System.out.println("--------------------(课程管理)1.添加课程信息  2.修改课程信息  3.删除课程信息  4.查询课程信息  5.查询课程下的学生信息 6.返回上一级--------------------");
			num = input.nextInt();
			switch (num) {
			case 1:
				cview.saveCourse();
				break;
			case 2:
				cview.updateCourse();			
				break;
			case 3:
				cview.deleteCourse();
				break;
			case 4:
				cview.listCourseAll();
				break;
			case 5:
				cview.listCourse_student();			
				break;
			case 6:
				
				break;

			default:
				break;
			}
		}
	}
	//教师管理功能模块
	public void teacherManage(){
		int num = 0;
		while(num != 6){
			System.out.println("--------------------(教师管理)1.添加教师信息  2.修改教师信息  3.删除教师信息  4.查询教师信息  5.查询教师所教授学生信息 6.返回上一级--------------------");
			num = input.nextInt();
			switch (num) {
			case 1:
				tview.saveTeacher();
				break;
			case 2:
				tview.updateTeacher();			
				break;
			case 3:
				tview.deleteTeacher();
				break;
			case 4:
				tview.listTeacherAll();
				break;
			case 5:
				tview.listTeacher_student();			
				break;
			case 6:
				
				break;

			default:
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new XkAdminSystem().work();
		
	}

}
