package com.neusoft.xk.po;

public class Teacher {
	private Integer teacherId;
	private String teacherName;
	private String technology;
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teacher(Integer teacherId, String teacherName, String technology) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.technology = technology;
	}
	
	public String toString(){
		return "教师编号:"+teacherId +"\t教师姓名:"+teacherName+"\t技术方向:"+technology;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	
}
