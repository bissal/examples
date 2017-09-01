package io.github.bissal.example.fcm;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {
	public static final String		GCM_SEND_ENDPOINT	= "https://android.googleapis.com/gcm/send";
	public static final MediaType	JSON				= MediaType.parse("application/json; charset=utf-8");

	OkHttpClient					client				= new OkHttpClient();

	public String post(String url, String json) throws IOException {
		Request request = newRequest(url, json);
		Builder newBuilder = client.newBuilder();
		client = newBuilder.connectTimeout(1000, TimeUnit.MILLISECONDS).readTimeout(1000, TimeUnit.MILLISECONDS).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	public Response post(Request request) throws IOException {
		try (Response response = client.newCall(request).execute()) {
			return response;
		}
	}

	public Request newRequest(String url, String json) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Authorization", "key=" + "AIzaSyA1jBu4b0A6iP9xagT4jhnujUIDfAjfdrc").post(body).build();
		return request;
	}
	
	public String makeJson(String device) {
		return "{\"registration_ids\": [\"" + device + "\" ],"
				+ "\"data\": {\"tickerText\":\"example test GCM\",\"contentTitle\":\"content title GCM\",\"message\": \"Enter your message\"}"
				+ "\"notification\": {\"title\":\"send to GCM endpoint\",\"body\":\"07월 12일.02.\"}"
				+ "}";
	}

	public static void main(String[] args) throws IOException {
		HttpClient example = new HttpClient();
		
		String device = "dlx7rEIWuRA:APA91bFw5TxUBg5jtD3-fyFjW34snHajAge8MYobll4LqWqXaB6Izqxt3NijckFbN9VC4Ik9husyTGHNPqO_mcsXRzg3Rwvy_ZZTga1Cl-kDyL69yU87ANpiiS24v5pO69wxio3iTHkm";
		
		String json = example.makeJson(device);
		String response = example.post(GCM_SEND_ENDPOINT, json);
		System.out.println(response);
	}
}
