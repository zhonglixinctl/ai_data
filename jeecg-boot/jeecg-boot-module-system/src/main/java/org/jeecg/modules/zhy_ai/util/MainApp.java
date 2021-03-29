package org.jeecg.modules.zhy_ai.util;
/**
 * 版权申明: 企查查科技有限公司<br>
 * 项目描述: 企查查-开放平台<br>
 * 该接口调用demo仅供学习参考
 */

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.IOException;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpHead;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 描述 http请求处理模块<br>
 * 
 * @author szld<br>
 * @version 1.0 <br>
 *          日期：2019年7月31日 下午2:23:41
 */
@Slf4j
public class MainApp {
	// 请登录http://yjapi.com/DataCenter/MyData
	// 查看我的秘钥 我的Key
//	private static final String appkey = "dea0fb777ead436c99293ab476aed2c5";
//	private static final String seckey = "5D47F81308A393CD115013C38144A1B3";

	// 众立信企查查接口密钥和key
	private static final String appkey = "d80d373308fd41feb5578d49f57ce47a";
	private static final String seckey = "213D515A8A7105B60BEFB7971F7B7902";

	public static void main(String[] args) {
		out.println(findCompany("北京众立信科技有限公司"));
	}

	public static void main1(String[] args) {
		String reqInterNme = "http://api.qichacha.com/FuzzySearch/GetList";
		String paramStr = "searchKey=北京众立信有限责任公司";
		String status = "";
		try {
			// auth header setting
			HttpHead reqHeader = new HttpHead();
			String[] autherHeader = RandomAuthentHeader();
			reqHeader.setHeader("Token", autherHeader[0]);
			reqHeader.setHeader("Timespan", autherHeader[1]);
			final String reqUri = reqInterNme.concat("?key=").concat(appkey).concat("&").concat(paramStr);
			out.println(reqUri+"=====1111");
			String tokenJson = HttpHelper.httpGet(reqUri, reqHeader.getAllHeaders());
			out.println(String.format("==========================>this is response:{%s}", tokenJson)+"=====22222");

			// parse status from json
			status = FormartJson(tokenJson, "Status");
			out.println(String.format("==========================>Status:{%s}", status)+"=====33333");
			if (!HttpCodeRegex.isAbnornalRequest(status)) {
				// 结果
				PrettyPrintJson(tokenJson);
			}
		} catch (Exception e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}
	}

	public static String findCompany(String companyName){
		companyName = companyName.replace(" ","20");
		String reqInterNme = "http://api.qichacha.com/FuzzySearch/GetList";
//		String paramStr = "searchKey=北京众立信科技有限公司";
		String status = "";
		try {
			// auth header setting
			HttpHead reqHeader = new HttpHead();
			String[] autherHeader = RandomAuthentHeader();
			reqHeader.setHeader("Token", autherHeader[0]);
			reqHeader.setHeader("Timespan", autherHeader[1]);
			final String reqUri = reqInterNme.concat("?key=").concat(appkey).concat("&").concat("searchKey=").concat(companyName);
//			out.println(reqUri+"=====1111");
			String tokenJson = HttpHelper.httpGet(reqUri, reqHeader.getAllHeaders());
//			out.println(String.format("==========================>this is response:{%s}", tokenJson)+"=====22222");

			// parse status from json
			status = FormartJson(tokenJson, "Status");

//			out.println(String.format("==========================>Status:{%s}", status)+"=====33333");
			if (!HttpCodeRegex.isAbnornalRequest(status)) {
				// 结果
				ObjectMapper mapper = new ObjectMapper();
				Object obj = mapper.readValue(tokenJson, Object.class);
				String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
				return indented;
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		return null;
	}

	// 获取返回码 Res Code
	static class HttpCodeRegex {
		private static final String ABNORMAL_REGIX = "(101)|(102)";
		private static final Pattern pattern = Pattern.compile(ABNORMAL_REGIX);
		protected static boolean isAbnornalRequest(final String status) {
			return pattern.matcher(status).matches();
		}
	}

	// 获取Auth Code
	protected static final String[] RandomAuthentHeader() {
		String timeSpan = String.valueOf(System.currentTimeMillis() / 1000);
		String[] authentHeaders = new String[] { DigestUtils.md5Hex(appkey.concat(timeSpan).concat(seckey)).toUpperCase(), timeSpan };
		return authentHeaders;
	}

	// 解析JSON
	protected static String FormartJson(String jsonString, String key) throws JSONException {
		JSONObject jObject = new JSONObject(jsonString);
		return (String) jObject.get(key);
	}

	// pretty print 返回值
	protected static void PrettyPrintJson(String jsonString) throws JSONException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object obj = mapper.readValue(jsonString, Object.class);
			String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			out.println(indented);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
