package com.neusoft.xk.po;

public class StuCou {
	private Integer id;
	private Integer studentId;
	private Integer courseId;
	public StuCou() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StuCou(Integer id, Integer studentId, Integer courseId) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	
}
