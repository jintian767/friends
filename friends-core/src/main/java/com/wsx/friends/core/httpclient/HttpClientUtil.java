package com.wsx.friends.core.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.wsx.friends.core.util.CommonFinalUtil;

/**
 * 
 * @author wangshuaixin
 *
 */
public class HttpClientUtil {

	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String doGet(String url) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse httpResponse = HttpClientHelper.httpClient.execute(get);
		try {
			HttpEntity entity = httpResponse.getEntity();
			return EntityUtils.toString(entity, CommonFinalUtil.CHARSET);
		} finally {
			httpResponse.close();
		}
	}
	
	public static String doPost(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		
		if (null != params && !params.isEmpty()) {
			Set<String> keys = params.keySet();
			List<NameValuePair> nvp = new ArrayList<NameValuePair>(keys.size());
			for (String key : keys) {
				nvp.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		
		CloseableHttpResponse httpResponse = HttpClientHelper.httpClient.execute(post);
		try {
			HttpEntity entity = httpResponse.getEntity();
			return EntityUtils.toString(entity, CommonFinalUtil.CHARSET);
		} finally {
			httpResponse.close();
		}
	}
}
