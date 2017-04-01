package com.allen.common.util;

import com.alibaba.fastjson.JSON;
import com.allen.common.constant.StringConstants;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class NoahTestUtil {
	
	
	//正式
	private static final String serverUrl = "http://10.10.130.136:9999";
//	private static final String serverUrl = "http://10.10.130.137:9999";
//	private static final String serverUrl = "http://noah.zjs.com.cn:9999";
	private static final String appkey = "5745229c";
	private static final String appSecret="7368f29ccca9ad34a285f6b3be48d078";
	public static void main( String[] args ) throws Exception
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put( "requestUrl", "http://10.10.130.136:9999" );
		map.put( "method", "/zjs-noah-rest/corpInfo/getCorpInfoListByTs" );
		map.put( "app_key", "5745229c" );
		map.put( "appSecret", "7368f29ccca9ad34a285f6b3be48d078" );
		map.put( "startCreated_param", "2016-04-04 10:01:16" );
		map.put( "endCreated_param", "2016-07-07 19:00:54" );
		map.put( "pageNo_param", "1" );
		map.put( "request_id_param", null );
		map.put( "client", "Y");
		Test( map );
	}


	public static String Test( Map<String, String> paramsMap ) throws IOException, Exception
	{
		String requestUrlStr = paramsMap.get( "requestUrl" );                           /* 环境 */

		String methodStr = paramsMap.get( "method" );                                   /* 方法 */

		String sign_methodStr = paramsMap.get( "sign_method" );                         /* 加密方式 */

		Map<String, Object>	reqParamMap	= new HashMap<String, Object>();        /* 请求参数map */
		Map<String, String>	signMap		= new HashMap<String, String>();        /* sign Map */

		Set<String> keys = paramsMap.keySet();                                          /* 遍历paramsMap */

		for ( String key : keys )
		{
			Matcher m = Pattern.compile( ".*?_param" ).matcher( key );
			/* 组装reqParamMap signMap */
			if ( m.matches() )
			{
				reqParamMap.put( key.replaceFirst( "_param", "" ), paramsMap.get( key ) );
				signMap.put( key.replaceFirst( "_param", "" ), paramsMap.get( key ) );
			}
		}

		reqParamMap.put( "requestUrl", requestUrlStr );

		Map<String, String> protocalMustParamsMap = new HashMap<String, String>(); /* protocalMustParamsmap */

		protocalMustParamsMap.put( "app_key", paramsMap.get( "app_key" ) );
		protocalMustParamsMap.put( "method", methodStr );
		protocalMustParamsMap.put( "appSecret", paramsMap.get( "appSecret" ) );
		protocalMustParamsMap.put( "version", "1.0" );


		/* String clientStr = paramsMap.get("client");//是否客户端 */

		SimpleDateFormat	sdf	= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date			date	= new Date();
		String			str	= sdf.format( date );

		protocalMustParamsMap.put( "timestamp", str ); /* 时间戳 */

		/* 获取签名 */

		if ( ("md5").equalsIgnoreCase( sign_methodStr ) )
		{
			protocalMustParamsMap.put( "sign", Encryption.signTopRequestNew( signMap, paramsMap.get( "appSecret" ), false ) );
		}else if ( ("hmac").equalsIgnoreCase( sign_methodStr ) )
		{
			protocalMustParamsMap.put( "sign", Encryption.signTopRequestNew( signMap, paramsMap.get( "appSecret" ), true ) );
		}


		Map<String, String> protocalOptParams = new HashMap<String, String>(); /* 格式参数map */

		protocalOptParams.put( "format", "json" );

		protocalOptParams.put( "sign_method", sign_methodStr );

		reqParamMap.put( "protocalMustParams", protocalMustParamsMap );
		reqParamMap.put( "protocalOptParams", protocalOptParams );
		
		String clientStr = paramsMap.get("client");//是否客户端
		

		
		//String res = httpPost( requestUrlStr + methodStr, JSON.toJSONString( reqParamMap ) );

		
		return("");
	}


	private static String httpPost( String postUrl, String xml ) throws Exception
	{
		URL			url	= new URL( postUrl );
		HttpURLConnection	uc	= (HttpURLConnection) url.openConnection();
		uc.setDoOutput( true );
		uc.setConnectTimeout( 3000 );
		uc.setRequestProperty( "Content-Type", "text/json" );

		OutputStreamWriter outPut = new OutputStreamWriter( uc.getOutputStream(), "UTF-8" );
		outPut.write( xml );
		outPut.flush();
		outPut.close();

		InputStream in = new DataInputStream( uc.getInputStream() );
		byte[] b = new byte[in.available()];
		in.read( b );

		String returnXml = new String( b, "UTF-8" );

		return(returnXml);
	}


	public static class Encryption {
		public static String signTopRequestNew( Map<String, String> params, String secret, boolean isHmac )
		throws IOException
		{
			String[] keys = (String[])params.keySet().toArray( new String[0] );
			Arrays.sort( keys );

			StringBuilder query = new StringBuilder();

			if ( !isHmac )
			{
				query.append( secret );
			}

			String[] bytes = keys;
			int len$ = keys.length;

			for ( int i$ = 0; i$ < len$; ++i$ )
			{
				String	key	= bytes[i$];
				String	value	= (String) params.get( key );

				if ( areNotEmpty( new String[] { key, value } ) )
				{
					query.append( key ).append( value );
				}
			}

			byte[] arg9;

			if ( isHmac )
			{
				arg9 = encryptHMAC( query.toString(), secret );
			} else {
				query.append( secret );
				arg9 = encryptMD5( query.toString() );
			}

			return(byte2hex( arg9 ) );
		}


		public static String signTopRequest( Map<String, String> params, String secret ) throws IOException
		{
			String[] keys = (String[])params.keySet().toArray( new String[0] );
			Arrays.sort( keys );

			StringBuilder query = new StringBuilder( secret );

			for ( String key : keys )
			{
				String value = (String) params.get( key );

				if ( areNotEmpty( new String[] { key, value } ) )
				{
					query.append( key ).append( value );
				}
			}

			byte[] bytes = encryptMD5( query.toString() );

			return(byte2hex( bytes ) );
		}


		private static byte[] encryptHMAC( String data, String secret ) throws IOException
		{
			byte[] bytes = null;

			try {
				SecretKey	secretKey	= new SecretKeySpec( secret.getBytes( "UTF-8" ), "HmacMD5" );
				Mac		mac		= Mac.getInstance( secretKey.getAlgorithm() );
				mac.init( secretKey );
				bytes = mac.doFinal( data.getBytes( "UTF-8" ) );
			} catch ( GeneralSecurityException gse ) {
				String msg = getStringFromException( gse );
				throw new IOException( msg );
			}

			return(bytes);
		}


		private static byte[] encryptMD5( String data ) throws IOException
		{
			byte[] bytes = null;

			try {
				MessageDigest md = MessageDigest.getInstance( "MD5" );
				bytes = md.digest( data.getBytes( "UTF-8" ) );
			} catch ( GeneralSecurityException gse ) {
				String msg = getStringFromException( gse );
				throw new IOException( msg );
			}

			return(bytes);
		}


		private static String getStringFromException( Throwable e )
		{
			String			result	= "";
			ByteArrayOutputStream	bos	= new ByteArrayOutputStream();
			PrintStream		ps	= new PrintStream( bos );
			e.printStackTrace( ps );

			try {
				result = bos.toString( "UTF-8" );
			} catch ( IOException ioe ) {
			}

			return(result);
		}


		private static String byte2hex( byte[] bytes )
		{
			StringBuilder sign = new StringBuilder();

			for ( int i = 0; i < bytes.length; ++i )
			{
				String hex = Integer.toHexString( bytes[i] & 0xFF );

				if ( hex.length() == 1 )
				{
					sign.append( "0" );
				}

				sign.append( hex.toUpperCase() );
			}

			return(sign.toString() );
		}


		public static boolean areNotEmpty( String[] values )
		{
			boolean result = true;

			if ( (values == null) || (values.length == 0) )
			{
				result = false;
			} else {
				for ( String value : values )
				{
					result &= !(isEmpty( value ) );
				}
			}

			return(result);
		}


		public static boolean isEmpty( String value )
		{
			int strLen;

			if ( (value == null) || ( (strLen = value.length() ) == 0) )
			{
				return(true);
			}

			for ( int i = 0; i < strLen; ++i )
			{
				if ( !(Character.isWhitespace( value.charAt( i ) ) ) )
				{
					return(false);
				}
			}

			return(true);
		}
	}

	public static String signTopRequestNew( Map<String, String> params,
						String secret, boolean isHmac ) throws IOException
	{
		String[] keys = (String[])params.keySet().toArray( new String[0] );
		Arrays.sort( keys );
		StringBuilder query = new StringBuilder();
		if ( !isHmac )
		{
			query.append( secret );
		}

		String[] bytes = keys;
		int len$ = keys.length;

		for ( int i$ = 0; i$ < len$; ++i$ )
		{
			String	key	= bytes[i$];
			String	value	= (String) params.get( key );
			if ( areNotEmpty( new String[] { key, value } ) )
			{
				query.append( key ).append( value );
			}
		}

		byte[] arg9;
		if ( isHmac )
		{
			arg9 = encryptHMAC( query.toString(), secret );
		} else {
			query.append( secret );
			arg9 = encryptMD5( query.toString() );
		}

		return(byte2hex( arg9 ) );
	}


	private static byte[] encryptHMAC( String data, String secret )
	throws IOException
	{
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec( secret.getBytes( StringConstants.CHARSET ),
								 "HmacMD5" );
			Mac mac = Mac.getInstance( secretKey.getAlgorithm() );
			mac.init( secretKey );
			bytes = mac.doFinal( data.getBytes( StringConstants.CHARSET ) );
		} catch ( GeneralSecurityException gse ) {
			String msg = getStringFromException( gse );
			throw new IOException( msg );
		}
		return(bytes);
	}


	private static byte[] encryptMD5( String data ) throws IOException
	{
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			bytes = md.digest( data.getBytes( StringConstants.CHARSET ) );
		} catch ( GeneralSecurityException gse ) {
			String msg = getStringFromException( gse );
			throw new IOException( msg );
		}
		return(bytes);
	}


	private static String byte2hex( byte[] bytes )
	{
		StringBuilder sign = new StringBuilder();
		for ( int i = 0; i < bytes.length; ++i )
		{
			String hex = Integer.toHexString( bytes[i] & 0xFF );
			if ( hex.length() == 1 )
			{
				sign.append( "0" );
			}
			sign.append( hex.toUpperCase() );
		}
		return(sign.toString() );
	}


	public static boolean areNotEmpty( String[] values )
	{
		boolean result = true;
		if ( (values == null) || (values.length == 0) )
			result = false;
		else {
			for ( String value : values )
			{
				result &= !(isEmpty( value ) );
			}
		}
		return(result);
	}


	public static boolean isEmpty( String value )
	{
		int strLen;
		if ( (value == null) || ( (strLen = value.length() ) == 0) )
			return(true);
		for ( int i = 0; i < strLen; ++i )
		{
			if ( !(Character.isWhitespace( value.charAt( i ) ) ) )
			{
				return(false);
			}
		}
		return(true);
	}


	private static String getStringFromException( Throwable e )
	{
		String			result	= "";
		ByteArrayOutputStream	bos	= new ByteArrayOutputStream();
		PrintStream		ps	= new PrintStream( bos );
		e.printStackTrace( ps );
		try {
			result = bos.toString( StringConstants.CHARSET );
		} catch ( IOException ioe ) {
		}
		return(result);
	}
}
