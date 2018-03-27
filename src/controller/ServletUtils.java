package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServletUtils {

	private static final String secret = "6LeUP08UAAAAAMIpEs7DmHat0RDVz3ZOhc9LLPQl";
	private static final String SITE_VERIFY_URL = //
			"https://www.google.com/recaptcha/api/siteverify";

	public static boolean verify(String gRecaptchaResponse) {
		if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
			return false;
		}

		try {
			URL verifyUrl = new URL(SITE_VERIFY_URL);

			// Open a Connection to URL above.
			HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();

			// Add the Header informations to the Request to prepare send to the server.
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			// Data will be sent to the server.
			String postParams = "secret=" + secret //
					+ "&response=" + gRecaptchaResponse;

			// Send Request
			conn.setDoOutput(true);

			// Get the output stream of Connection.
			// Write data in this stream, which means to send data to Server.
			OutputStream outStream = conn.getOutputStream();
			outStream.write(postParams.getBytes());

			outStream.flush();
			outStream.close();

			// Response code return from Server.
			int responseCode = conn.getResponseCode();

			// Get the Input Stream of Connection to read data sent from the Server.
			// InputStream is = conn.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			JSONObject jsonObj = new JSONObject(content.toString());

			boolean success = jsonObj.getBoolean("success");

			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String headWithTitle(String title) {
		return ("<!DOCTYPE html>\n" + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n");
	}

	private static String filter(String input) {
		if (!hasSpecialChars(input)) {
			return (input);
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	private static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				switch (c) {
				case '<':
					flag = true;
					break;
				case '>':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return (flag);
	}

	public static String validateInput(String str, String replacement) {

		if (str == null || str.equals("")) {
			str = replacement;
		}
		str = filter(str);

		return str;
	}

	public static void validateAllInput(String[] strArr) {

		for (String str : strArr) {
			str = filter(str);
		}
	}

	public static boolean validMatch(String str1, String str2) {

		return str1.equals(str2);
	}
}
