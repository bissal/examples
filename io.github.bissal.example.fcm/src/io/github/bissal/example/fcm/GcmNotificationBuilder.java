package io.github.bissal.example.fcm;

public class GcmNotificationBuilder {
	public String makeJson(String device) {
		return "{\"registration_ids\": [\"" + device + "\" ],"
				+ "\"data\": {\"tickerText\":\"example test GCM\",\"contentTitle\":\"content title GCM\",\"message\": \"Enter your message\"}"
				+ "\"notification\": {\"title\":\"send to GCM endpoint\",\"body\":\"이게 가네.\"}"
				+ "}";
	}
}
