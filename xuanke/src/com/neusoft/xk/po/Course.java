package com.neusoft.xk.po;

public class Course {
	private Integer courseId;
	private String courseName;
	private Integer courseHour ;
	private Integer teacherId;
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(Integer courseId, String courseName, Integer courseHour, Integer teacherId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseHour = courseHour;
		this.teacherId = teacherId;
	}
	public String toString(){
		return "�γ̱��:"+courseId+"\t�γ�����:"+courseName+"\t�γ̿�ʱ:"+courseHour+"\t���ڽ�ʦ���:"+teacherId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseHour() {
		return courseHour;
	}
	public void setCourseHour(Integer courseHour) {
		this.courseHour = courseHour;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
}
