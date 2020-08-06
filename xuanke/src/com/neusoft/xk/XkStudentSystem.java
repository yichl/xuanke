package com.neusoft.xk;

import java.util.Scanner;

import com.neusoft.xk.po.Student;
import com.neusoft.xk.view.StudentView;
import com.neusoft.xk.view.impl.StudentViewimpl;

public class XkStudentSystem {

	private Scanner input = new Scanner(System.in);
	private StudentView sview = new StudentViewimpl();
	
	public void work() {
		System.out.println("��ӭ��½����ѧ��ѡ�ι���ϵͳ");
		int num = 0;
		while(num != 3) {
			System.out.println("-----------------1.ѧ��ע��  2.ѧ����¼  3.�˳�ϵͳ-----------------");
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
						System.out.println("ѧ��ѧ�ź�ѧ��������ƥ�䣬��¼ʧ�ܣ�");
					}
					break;
				case 3:
					System.out.println("ллʹ�ö���ѧ��ѡ��ϵͳ");
					break;
	
				default:
					break;
			}
		}
	}
	
	public void studentMange(Student student) {
		int num = 0;
		while(num != 3) {
			System.out.println("-----------------��ѧ��ѡ�ι���1.ѧ��ѡ��  2.��ѯ��ѡ�γ�  3.������һ��-----------------");
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
