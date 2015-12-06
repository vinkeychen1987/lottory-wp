package com.unison.lottery.api.query.servlet;


import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.unison.lottery.api.framework.servlet.AbstractProcessServlet;
import com.unison.lottery.api.model.User;
import com.unison.lottery.api.protocol.common.MethodNames;
import com.unison.lottery.api.protocol.common.VONames;
import com.unison.lottery.api.protocol.response.model.HaveReturnStatusResponse;
import com.unison.lottery.api.protocol.response.model.QueryOnSaleLotteryResponse;
import com.unison.lottery.api.query.bo.QueryBO;





/**
 * Servlet implementation class RegisteWithSecurityCodeServlet
 */
@WebServlet("/xml/queryOnSaleLottery")
public class QueryOnSaleLotteryServlet extends AbstractProcessServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8981715718297792122L;
	
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public QueryOnSaleLotteryServlet() {
        super();
    }
	@Override
	protected String getMethodName() {
		return MethodNames.QUERY_ONSALE_LOTTERY;
	}
	@Override
	protected String getResponseVOName() {
		return VONames.QUERY_ONSALE_LOTTERY_RESPONSE_VO_NAME;
	}
	@Override
	protected
	HaveReturnStatusResponse process(HttpServletRequest request) {
		
		String banner = (String) request.getAttribute(VONames.BENNER_VERSION);
		User user=(User) request.getAttribute(VONames.USER_VO_NAME);
		QueryBO queryBO=(QueryBO) ctx.getBean("queryBO");
		QueryOnSaleLotteryResponse queryOnSaleLotteryResponse = queryBO.queryOnSaleLottery(banner,user);

		return	queryOnSaleLotteryResponse; 
		
	}

}
