package com.sutrix.utils;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.jcr.resource.JcrResourceUtil;

import com.sutrix.user.UserDTO;

public class UserUtils {
	public static UserDTO getUser(Session session, String username) {
		UserDTO user = null;
		
		if(StringUtils.isBlank(username)){
			return user;
		}
		
		try {
			String path = "/content/unittest/user01";
			Node nodePage = JcrResourceUtil.createPath(path , "nt:unstructured", "nt:unstructured", session, false);
			
			if(nodePage.hasProperty("username")&& nodePage.getProperty("username").getString().equals(username)){
				user = new UserDTO(username, nodePage.getProperty("password").getString());
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean loginUser(Session session, String username, String password) {
		boolean success = false;
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			System.out.println("Please input information.");
			return success;
		}
		
		UserDTO user = getUser(session, username);
		if(user == null){
			System.out.println("Username is wrong.");
		}else if(!password.equals(user.getPassword())){
			System.out.println("Password is wrong.");
		}else{
			System.out.println("Login success.");
			success = true;
		}
		return success;
	}
}
