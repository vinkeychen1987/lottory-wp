package com.xhcms.lottery.pb.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xhcms.lottery.pb.model.Constants;

public class HttpClientTest {
	private static Logger logger = LoggerFactory
			.getLogger(HttpClientTest.class);

	public static void main(String[] args) {
		HttpClient httpclient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(
				"http://localhost:80/lottery-partner-bet/issue-info.do");
//		 "http://58.83.235.132:28080/partner_api/bet");

		File file = new File("src/test/resources/testXmlContent.xml");
		String xmlContent = FileUtil.file2String(file, "utf-8");
		System.out.println(xmlContent);
		String md5 = MD5Util.md5("youyuanwang" + "!@8W6#2E52$43DS4%1^" + xmlContent);

		httpPost.setHeader(Constants.MSG_TYPE, "001");
		httpPost.setHeader(Constants.PARTNER_ID, "youyuanwang");
		httpPost.setHeader(Constants.SIGNATURE, md5);

		StringEntity xmlEntity = new StringEntity(xmlContent,
				ContentType.create("text/plain", "UTF-8"));
		httpPost.setEntity(xmlEntity);
		HttpResponse response;
		try {
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			Header[] hs = response.getAllHeaders();
			for (Header header : hs) {
				System.out.println(header.getName() + ":" + header.getValue());
			}
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					String x = FileUtil.inputStreamToString(instream).trim();
					System.out.println(x);
					System.out.println("signiture:"
							+ MD5Util.md5("youyuanwang" + Constants.PARTNER_KEYS.get("youyuanwang") + x));
				} finally {
					instream.close();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
}
