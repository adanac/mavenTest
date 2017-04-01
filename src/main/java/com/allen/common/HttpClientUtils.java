package com.allen.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class HttpClientUtils {
	public static final String ENCODING_UTF_8 = "UTF-8";

	private static volatile HttpClient httpclient;

	public static String doGet(String url) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		String response = null;
		try {
			response = executeHttpRequest(httpGet, httpClient);
		} finally {
			httpClient.getConnectionManager().shutdown(); // shutdown pool
		}
		return response;
	}

	/**
	 * 这个接口适合需要频繁调用get的场景
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String doGetFast(String url) throws IOException {
		HttpClient client = getSharedHttpClient();
		HttpGet httpGet = new HttpGet(url);

		return executeHttpRequest(httpGet, client);
	}

	private static String executeHttpRequest(HttpRequestBase requestBase, HttpClient httpClient) throws IOException {
		try {
			HttpResponse httpResponse = httpClient.execute(requestBase);
			StatusLine stausLine = httpResponse.getStatusLine();
			if (HttpStatus.SC_OK != stausLine.getStatusCode()) {
				throw new IOException("StatusCode=" + stausLine.getStatusCode()); // 非200 全部当做IO Exception
			}
			return EntityUtils.toString(httpResponse.getEntity(), ENCODING_UTF_8);
		} finally {
			requestBase.releaseConnection();
		}
	}

	public static String doPost(Map<String, String> parameters, String url) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = createHttpPost(parameters, url);

		String response = null;
		try {
			response = executeHttpRequest(httpPost, httpClient);
		} finally {
			httpClient.getConnectionManager().shutdown(); // shut down pool
		}
		return response;
	}

	public static String doPostFast(Map<String, String> parameters, String url) throws IOException {
		HttpClient httpClient = getSharedHttpClient();
		HttpPost post = createHttpPost(parameters, url);

		return executeHttpRequest(post, httpClient);
	}

	private static HttpPost createHttpPost(Map<String, String> parameters, String url) throws UnsupportedEncodingException {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePairs.size() > 0) {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, ENCODING_UTF_8));
		}
		return httpPost;
	}

	private static HttpClient getSharedHttpClient() {
		if (httpclient == null) {
			synchronized (HttpClientUtils.class) {
				if (httpclient == null) {
					HttpParams httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
					HttpConnectionParams.setSoTimeout(httpParams, 60000);
					HttpConnectionParams.setTcpNoDelay(httpParams, true);

					final PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
					connectionManager.setMaxTotal(256 * 2); //创建socket的上线是256*2
					connectionManager.setDefaultMaxPerRoute(256); // 对每个指定连接的服务器（指定的IP）最多可以创建256 socket进行访问

					DefaultHttpClient httpclient = new DefaultHttpClient(connectionManager);
					httpclient.setParams(httpParams);
					httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

					// 设置为不重试
					DefaultHttpRequestRetryHandler NO_RETRY = new DefaultHttpRequestRetryHandler(0, false);
					httpclient.setHttpRequestRetryHandler(NO_RETRY);

					// 设置keep alive策略
					httpclient.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
						@Override
						public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
							HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
							while (it.hasNext()) {
								HeaderElement he = it.nextElement();
								String param = he.getName();
								String value = he.getValue();
								if (value != null && param.equalsIgnoreCase("timeout")) {
									return Long.parseLong(value) * 1000;
								}
							}
							// 找不到keep-alive header
							return 30 * 1000;
						}
					});

					// start check thread
					Thread checkThread = new Thread() {
						public void run() {
							while (true) {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
								connectionManager.closeExpiredConnections();
								connectionManager.closeIdleConnections(30, TimeUnit.SECONDS);
							}
						}
					};
					checkThread.setDaemon(true); // 必须set daemon
					checkThread.start();

					HttpClientUtilsV2.httpclient = httpclient; // 将构造完成的局部对象 --> shared object
				}
			}

		}
		return HttpClientUtilsV2.httpclient;
	}

}
