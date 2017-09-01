package io.github.bissal.example.fcm;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpClientTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendToGcm() throws Exception {
//		for (int i = 0; i < 5; i++) 
		aa();
	}

	private void aa() throws IOException {
		HttpClient httpClient = new HttpClient();
		
		String device = "dlx7rEIWuRA:APA91bFw5TxUBg5jtD3-fyFjW34snHajAge8MYobll4LqWqXaB6Izqxt3NijckFbN9VC4Ik9husyTGHNPqO_mcsXRzg3Rwvy_ZZTga1Cl-kDyL69yU87ANpiiS24v5pO69wxio3iTHkm";
		
		String json = httpClient.makeJson(device);
		System.out.println(json);
		
		String response = httpClient.post(HttpClient.GCM_SEND_ENDPOINT, json);
		System.out.println(response);
	}
}
