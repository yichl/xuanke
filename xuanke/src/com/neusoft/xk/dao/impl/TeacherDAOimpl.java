package com.neusoft.xk.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.xk.dao.TeacherDAO;
import com.neusoft.xk.po.Student;
import com.neusoft.xk.po.Teacher;
import com.neusoft.xk.util.BaseDAO;

public class TeacherDAOimpl extends BaseDAO implements TeacherDAO {
	
	public List<Teacher> listTeacherAll(){
		String sql = "select teacherid ,teachername,technology from teacher ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Teacher> tlist = new ArrayList<Teacher>();
		try {
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Teacher t = new Teacher();
				t.setTeacherId(rs.getInt("teacherid"));
				t.setTeacherName(rs.getString("teachername"));
				t.setTechnology(rs.getString("technology"));
				tlist.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return tlist;
	}
	
	public int saveTeacher(Teacher teacher){
		String sql = "insert into teacher (teachername,technology) values(?,?) ";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherName());
			pstmt.setString(2, teacher.getTechnology());
			count = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
		return count;
	}
	
	
	public Teacher teacherById(int teacherId){
		String sql = "select teacherid ,teachername,technology from teacher where teacherid = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher t = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, teacherId);;
			rs = pstmt.executeQuery();
			if(rs.next()){
				t = new Teacher();
				t.setTeacherId(rs.getInt("teacherid"));
				t.setTeacherName(rs.getString("teachername"));
				t.setTechnology(rs.getString("technology"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return t;
	}
	
	public int updateteacher(Teacher teacher){
		String sql = "update teacher set teachername = ?,technology = ? where teacherid = ? ";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherName());
			pstmt.setString(2, teacher.getTechnology());
			pstmt.setInt(3, teacher.getTeacherId());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
		return count;
	}
	
	//数据库事务(设计三张表)
	public int deleteTeacher(int teacherId){
		String sql1 = "delete from stucou where courseid in (select courseid from course where teacherid = ?)";
		String sql2 = "delete from course where teacherid = ?";
		String sql3 = "delete from teacher where teacherid = ? ";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			getConn().setAutoCommit(false);
			pstmt = getConn().prepareStatement(sql1);
			pstmt.setInt(1, teacherId);
			count = pstmt.executeUpdate();
			
			pstmt = getConn().prepareStatement(sql2);
			pstmt.setInt(1, teacherId);
			count = pstmt.executeUpdate();
			
			pstmt = getConn().prepareStatement(sql3);
			pstmt.setInt(1, teacherId);
			count = pstmt.executeUpdate();
			
			getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				getConn().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			close(null, pstmt, null);
		}
		return count;
	}
	
	public List<Student> listTeacher_student(int teacherId){
		String sql = "select distinct s.Studentid ,s.Studentname,s.StudentClass from student s,stucou sc,course c,teacher t "
				+ "where t.teacherId = c.teacherId and c.courseId = sc.courseId and sc.studentid = s.studentid and t.teacherid = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> slist = new ArrayList<Student>();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, teacherId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Student student = new Student();
				student.setStudentId(rs.getInt("studentid"));
				student.setStudentName(rs.getString("studentname"));
				student.setStudentClass(rs.getString("studentclass"));
				slist.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return slist;
	}
}
