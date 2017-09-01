package io.github.bissal.example.fcm;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GcmHttpClient {
	public static final String		GCM_SEND_ENDPOINT	= "https://android.googleapis.com/gcm/send";

	public static void main(String[] args) throws IOException {
		GcmHttpClient example = new GcmHttpClient();
		
		String device = "dlx7rEIWuRA:APA91bFw5TxUBg5jtD3-fyFjW34snHajAge8MYobll4LqWqXaB6Izqxt3NijckFbN9VC4Ik9husyTGHNPqO_mcsXRzg3Rwvy_ZZTga1Cl-kDyL69yU87ANpiiS24v5pO69wxio3iTHkm";
		
		GcmNotificationBuilder notificationBuilder = new GcmNotificationBuilder();
		String json = notificationBuilder.makeJson(device);
		System.out.println(json);
		
		HttpClient httpClient = new HttpClient();
		Request request = httpClient.newRequest(GCM_SEND_ENDPOINT, json);
		Response response = httpClient.post(request);
		
		System.out.println(response.body().string());
	}
}
