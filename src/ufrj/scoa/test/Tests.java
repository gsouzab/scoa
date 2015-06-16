package ufrj.scoa.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ufrj.scoa.util.Util;

public class Tests {

	@Test
	public void test_generatedPassword() {
		
		String password = "senha123";
		 
		assertEquals(Util.generateNewPassword(password),"e7d80ffeefa212b7c5c55700e4f7193e");
		
	}

}
