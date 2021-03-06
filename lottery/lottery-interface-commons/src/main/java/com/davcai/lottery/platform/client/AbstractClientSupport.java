package com.davcai.lottery.platform.client;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davcai.lottery.platform.client.anruizhiying.model.AnRuiZhiYingOrderTicketResponse;
import com.davcai.lottery.platform.client.anruizhiying.model.AnRuiZhiYingResponse;
import com.davcai.lottery.platform.client.anruizhiying.util.AnRuiZhiYingUrlUtil;
import com.xhcms.lottery.commons.client.HttpClient;

public abstract class AbstractClientSupport {
	
	private String interfaceUrl;//接口地址
	
	private String postEncoding="utf-8";//post请求使用的编码,默认utf-8
	private String postContentType="application/x-www-form-urlencoded";//post请求使用的ContentType,默认application/x-www-form-urlencoded
	private String responseEncoding="utf-8";//响应使用的编码
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	
	private List<String> paramNamesShouldSign;
	
	public LotteryPlatformBaseResponse doGetDirect(Map<String, Object> params) {
		HttpClient httpClient=new HttpClient();
		String responseStr=httpClient.get(getInterfaceUrl(), params);
		return parseResponse(responseStr);
	}
	public LotteryPlatformBaseResponse doPost(Map<String, Object> params) {
		System.out.println("params="+params);
			String finalParams=makeFinalParams(params);
			HttpClient httpClient=new HttpClient();
			String responseStr=httpClient.postStringEntityWithEncodingAndContentType(getInterfaceUrl(), finalParams,getPostEncoding(),getPostContentType(),getResponseEncoding());
			return parseResponse(responseStr);
	}

	protected abstract LotteryPlatformBaseResponse parseResponse(String responseStr);
	/**
	 * 根据提供的params生成最终的url，但params中的ChannelID和Sign不起作用
	 * @param params
	 * @return
	 */
	protected abstract String makeFinalParams(Map<String, Object> params) ;

	

	/**
	 * 由于参与校验的参数是要求顺序的，故这里的返回是LinkedHashSet，以保证使用插入时的顺序
	 * @return
	 */
	
	protected LinkedHashSet<String> getParamsShouldSign() {
		LinkedHashSet<String>  result=new LinkedHashSet<String>();
		if(null!=this.getParamNamesShouldSign()&&!getParamNamesShouldSign().isEmpty()){
			for(String paramShouldIgnore:getParamNamesShouldSign()){
				result.add(paramShouldIgnore);
			}
			
		}
		
		return result;
	}

	public  String getInterfaceUrl(){
		return this.interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}


	public String getPostEncoding() {
		return postEncoding;
	}

	public void setPostEncoding(String postEncoding) {
		this.postEncoding = postEncoding;
	}

	public String getPostContentType() {
		return postContentType;
	}

	public void setPostContentType(String postContentType) {
		this.postContentType = postContentType;
	}

	public String getResponseEncoding() {
		return responseEncoding;
	}

	public void setResponseEncoding(String responseEncoding) {
		this.responseEncoding = responseEncoding;
	}
	//带重试机制的post请求
	public LotteryPlatformBaseResponse doPostWithRetry(
			Map<String, Object> params) {
		LotteryPlatformBaseResponse result=null;
        for (int i = 0; i < getMaxRetryTime(); i++) {
        	logger.debug("发送第{}次",i);
        	result=doPost(params);
            if (!shouldRetry(result)) {
                return result;
            }
        }
		return result;
	}
	protected abstract boolean shouldRetry(LotteryPlatformBaseResponse result) ;
	protected abstract int getMaxRetryTime() ;
	public List<String> getParamNamesShouldSign() {
		return paramNamesShouldSign;
	}
	public void setParamNamesShouldSign(List<String> paramNamesShouldSign) {
		this.paramNamesShouldSign = paramNamesShouldSign;
	}
	
}
