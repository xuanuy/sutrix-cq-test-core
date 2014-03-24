package com.sutrix.core;

import static org.junit.Assert.assertNotNull;

import javax.jcr.Credentials;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.commons.testing.sling.MockSlingHttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mock;

public class MainTest {
	@Mock
	private SlingHttpServletRequest slingRequest;

	@Mock
	private ResourceResolver resourceResolver;

	@Mock
	private SlingBindings slingBindings;

	@Mock
	private SlingScriptHelper sling;

	private Repository repository;

	public static Session session;

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
		slingRequest = new MockSlingHttpServletRequest("/content/US/en/home-page", null, "html", null, null);
		
		connectToCrx();
	}

	@After
	public void tearDown() {
	}

	public void connectToCrx() {
		try {
			repository = org.apache.jackrabbit.commons.JcrUtils.getRepository("http://localhost:4602/crx/server");
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		assertNotNull(repository);

		Credentials tokenCreds = new SimpleCredentials("admin", "admin".toCharArray());
		try {
			session = repository.login(tokenCreds, "crx.default");
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		assertNotNull(session);

		Resource resource = slingRequest.getResource();
		assertNotNull(resource);
	}
}
