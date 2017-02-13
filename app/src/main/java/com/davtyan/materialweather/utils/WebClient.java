package com.davtyan.materialweather.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
	public byte[] getBytes(String urlString) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			InputStream stream = connection.getInputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] bytes = new byte[4096];
			int readBytesCount;
			while ((readBytesCount = stream.read(bytes, 0, bytes.length)) != -1) {
				byteArrayOutputStream.write(bytes, 0, readBytesCount);
			}

			stream.close();
			byteArrayOutputStream.close();

			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public String getString(String url) {
		return new String(getBytes(url));
	}
}
