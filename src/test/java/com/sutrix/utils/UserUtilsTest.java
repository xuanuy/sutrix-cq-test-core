package com.sutrix.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.sutrix.core.MainTest;

public class UserUtilsTest extends MainTest{
	public UserUtilsTest() {
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void loginUser(){
		System.out.println("Case 1: user = pass = null");
		String username = null;
		String password = null;
		assertFalse(UserUtils.loginUser(session, username, password));

		System.out.println("\nCase 2: user is wrong, pass is true");
		username = "user";
		password = "Password@123";
		assertFalse(UserUtils.loginUser(session, username, password));

		System.out.println("\nCase 3: user is true, pass is wrong");
		username = "xuanuy";
		password = "Password@111";
		assertFalse(UserUtils.loginUser(session, username, password));

		System.out.println("\nCase 4: user is true, pass is true");
		username = "xuanuy";
		password = "Password@123";
		assertTrue(UserUtils.loginUser(session, username, password));
	}
}
