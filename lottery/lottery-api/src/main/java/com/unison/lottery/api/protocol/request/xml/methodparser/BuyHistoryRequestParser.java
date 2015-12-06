package com.unison.lottery.api.protocol.request.xml.methodparser;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.unison.lottery.api.protocol.common.VONames;
import com.unison.lottery.api.protocol.request.xml.model.MethodRequest;

public class BuyHistoryRequestParser extends
		AbstractRequestParserWithUserParser {

	@Override
	protected boolean parseParamList(MethodRequest methodRequest,
			HttpServletRequest httpRequest) {
		return false;
	}

	@Override
	protected boolean parseExtendRequestParams(MethodRequest methodRequest,
			HttpServletRequest httpRequest) {
		Map<String,String> paramMap = new HashMap<String, String>();
		
		paramMap.put(com.unison.lottery.api.protocol.common.Constants.BET_TYPE ,methodRequest.betType );
		paramMap.put(com.unison.lottery.api.protocol.common.Constants.FIRST_RESULT ,methodRequest.firstResult);
		
		httpRequest.setAttribute(VONames.BUY_HISTORY_VO_NAME, paramMap);
		return true;
	}

	@Override
	protected boolean decideShouldParseParamList() {
		
		return false;
	}

	@Override
	protected boolean decideShouldParseExtendRequestParams() {
		
		return true;
	}

	@Override
	protected boolean decideShouldParseUser() {
		
		return true;
	}

}
