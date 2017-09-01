package io.github.bissal.example.fcm;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Post2GcmExample {
	public static final String		GCM_SEND_ENDPOINT	= "https://android.googleapis.com/gcm/send";
	public static final MediaType	JSON				= MediaType.parse("application/json; charset=utf-8");

	OkHttpClient					client				= new OkHttpClient();

	public String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Authorization", "key=" + "AIzaSyA1jBu4b0A6iP9xagT4jhnujUIDfAjfdrc").post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	public String makeJson(String device) {
		return "{\"registration_ids\": [\"" + device + "\" ],"
				+ "\"data\": {\"tickerText\":\"example test GCM\",\"contentTitle\":\"content title GCM\",\"message\": \"Enter your message\"}"
				+ "\"notification\": {\"title\":\"send to GCM endpoint\",\"body\":\"이게 가네.\"}"
				+ "}";
	}

	public static void main(String[] args) throws IOException {
		Post2GcmExample example = new Post2GcmExample();
		
		String device = "dlx7rEIWuRA:APA91bFw5TxUBg5jtD3-fyFjW34snHajAge8MYobll4LqWqXaB6Izqxt3NijckFbN9VC4Ik9husyTGHNPqO_mcsXRzg3Rwvy_ZZTga1Cl-kDyL69yU87ANpiiS24v5pO69wxio3iTHkm";
		
		String json = example.makeJson(device);
		String response = example.post(GCM_SEND_ENDPOINT, json);
		System.out.println(response);
	}
}
