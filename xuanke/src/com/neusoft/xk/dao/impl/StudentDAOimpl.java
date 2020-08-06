package com.neusoft.xk.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.xk.dao.StudentDAO;
import com.neusoft.xk.po.Course;
import com.neusoft.xk.po.Student;
import com.neusoft.xk.po.Teacher;
import com.neusoft.xk.util.BaseDAO;

public class StudentDAOimpl extends BaseDAO implements StudentDAO {
	
	public int saveStudent(Student student) {
		String sql = "insert into Student (StudentName,Studentclass) values(?,?) ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = getConn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, student.getStudentName());
			pstmt.setString(2, student.getStudentClass());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
		return count;
	}
	
	public Student studentLogin(int studentId,String studentName) {
		String sql = "select studentid ,studentName,studentclass from student where studentid = ? and studentname = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, studentId );
			pstmt.setString(2, studentName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				student = new Student();
				student.setStudentId(rs.getInt("studentid"));
				student.setStudentName(rs.getString("studentName"));
				student.setStudentClass(rs.getString("studentClass"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return student;
	}
	
	public List<Course> liststudent_course(Student student) {
		String sql = "select c.courseid ,c.courseName,c.coursehour,c.teacherid from course c,stucou sc where sc.courseid = c.courseid and sc.studentid = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> clist = new ArrayList<Course>();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, student.getStudentId() );
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseid"));
				course.setCourseHour(rs.getInt("coursehour"));
				course.setCourseName(rs.getString("CourseName"));
				course.setTeacherId(rs.getInt("teacherid"));
				clist.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return clist;
	}
	
	public List<Course> student_course(String courseName,String teacherName){
		StringBuffer sql = new StringBuffer("select c.courseid ,c.courseName,c.coursehour,c.teacherid from course c left outer join teacher t on (c.teacherId = t.teacherid) where 1 = 1  ") ;
		if(courseName != null){
			sql.append(" and c.courseName like '%"+courseName+"%' ");
		}
		if(teacherName != null){
			sql.append(" and t.teacherName like '%"+teacherName+"%' ");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> clist = new ArrayList<Course>();
		try {
			
			pstmt = getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseid"));
				course.setCourseHour(rs.getInt("coursehour"));
				course.setCourseName(rs.getString("CourseName"));
				course.setTeacherId(rs.getInt("teacherid"));
				clist.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return clist;
	}
	
	public boolean courseByCidSid(int courseId,int studentId) {
		String sql = "select id,courseid,studentId from stucou where courseid = ? and studentid = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, courseId );
			pstmt.setInt(2, studentId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return false;
	}
	
	public int saveStudent_Course(int studentId,int courseId) {
		String sql = "insert into stucou (courseId,studentId) values(?,?) ";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, courseId);
			pstmt.setInt(2, studentId);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
		return count;
	}
}
