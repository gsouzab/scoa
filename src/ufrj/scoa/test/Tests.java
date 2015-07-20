package ufrj.scoa.test;

import static org.junit.Assert.*;

import java.security.AccessController;
import java.util.ArrayList;

import org.junit.Test;

import ufrj.scoa.controller.*;
import ufrj.scoa.model.DAO.*;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.util.Util;

public class Tests {
	
	@Test
	public void test_generatedPassword() {
		
		String password = "senha123";
		
		assertEquals(Util.generateNewPassword(password),"e7d80ffeefa212b7c5c55700e4f7193e");
	}

	@Test
	public void test_generatedPassword2(){
		String password = "senha123";
		int len = 32;
		
		assertEquals(Util.generateNewPassword(password).length(),len);
	}
	
	@Test
	public void test_comparePasswd() {
		PersonDAO person = new PersonDAO();
		String password =  Util.generateNewPassword("11111111111");
		String password2 = Util.generateNewPassword("2222222222");
		
		String passwordBd = person.getPassword("11111111111");
		
		Boolean passwd = false;
		Boolean passwd2 = false;
		
		passwd = password.equalsIgnoreCase(passwordBd);
		passwd2 = password2.equalsIgnoreCase(passwordBd);
		
		assertEquals(passwd, true);
		assertEquals(passwd2,false);
		
	}
	
	@Test
	public void test_valideteLoginFields(){
				
		String string1 = "123";
		String string2 = "456";
		String string3 = "";
		String string4 = "123";
		
		boolean test = string1.length() > 0 && string2.length() > 0;
		boolean test2 = string3.length() > 0 && string4.length() > 0;
		boolean test3 = string3.length() > 0 && string3.length() > 0;

		assertEquals(test,true);
		assertEquals(test2,false);
		assertEquals(test3,false);
	}
	
	@Test
	public void test_getProfessorById_null(){
		ProfessorDAO professor = new ProfessorDAO();
		Professor prof = null;
		
		prof =  professor.getProfessorById(0);
		
		assertEquals(prof,null);
	}
	@Test
	public void test_getStudentById_null(){
		StudentDAO student = new StudentDAO();
		Student stud = null;
		
		stud =  student.getStudentById(0);
		
		assertEquals(stud,null);
	}

	@Test
	public void test_getCourseById_null(){
		CourseDAO course = new CourseDAO();
		Course c = null;
				
		c =  course.getCourseById(0);
		assertEquals(c,null);
	}	
	
	@Test
	public void test_searchCourseTest(){
		CourseDAO course = new CourseDAO();
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		courseList = course.searchCourse("Teste", "Teste", "Teste");
		
		assertEquals(courseList.get(0).getName(),"Teste");
	}
	
	@Test
	public void test_validateLogin(){
		PersonDAO person = new PersonDAO();
		boolean teste;
		
		teste = person.validateLogin("00000000000", "0000");
		
		assertEquals(teste,true);
	}
	
	@Test
	public void test_PersonPasswdLenght(){
		PersonDAO person = new PersonDAO();
		String passwd;
		
		passwd = person.getPassword("00000000000");
		
		assertEquals(passwd.length(),32);
	}
	
	@Test
	public void test_getRoomById(){
		RoomDAO room = new RoomDAO();
		Room r = null;
		r = room.getRoomById(0);
		assertEquals(r,null);
	}
	

}
