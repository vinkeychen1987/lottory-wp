package com.xhcms.lottery.dc.task.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xhcms.commons.job.Job;
import com.xhcms.commons.mq.MessageSender;
import com.xhcms.lottery.commons.client.TranslateException;
import com.xhcms.lottery.commons.client.Translator;
import com.xhcms.lottery.commons.event.BuyTicketMessage;
import com.xhcms.lottery.commons.persist.entity.BetSchemePO;
import com.xhcms.lottery.dc.persist.service.TicketService;
import com.xhcms.lottery.lang.LotteryId;
import com.xhcms.lottery.lang.PlayType;

/**
 * <p>
 * Title: 检查福彩可出票方案，发送出票请求
 * </p>
 * <p>
 * Description: 检查合买方案是否符合出票条件，如果符合则出票。目前支持彩种：双色球
 * </p>
 * @author lei.li@unison.net.cn
 * @version 1.0.0
 */
public class BuyWFTicketTask extends Job {
	@Autowired
	private TicketService ticketService;
	@Autowired
	private MessageSender messageSender;

	@Override
	protected void execute() {
		Map<String, List<BetSchemePO>> playIdSchemePOsMap = ticketService
				.listWFAllowBuySchemesGroupByPlayId();

		for (Map.Entry<String, List<BetSchemePO>> playIdSchemePOs : playIdSchemePOsMap
				.entrySet()) {
			String playId = playIdSchemePOs.getKey();
			List<BetSchemePO> schemePOs = playIdSchemePOs.getValue();
			sendBuyTicketMessage(playId, schemePOs);
		}
	}

	private void sendBuyTicketMessage(String playId, List<BetSchemePO> schemePOs) {
		try {
			PlayType playType = PlayType.valueOfLcPlayId(playId);
			String lcLotteryId = playType.getLotteryId().name();
			log.debug("lcLotteryId from playId '{}' is: {}", playId,
					lcLotteryId);
			List<Long> schemeIds = getCanSendSchemeIds(lcLotteryId, schemePOs);
			if (null != schemeIds) {
				BuyTicketMessage tm = new BuyTicketMessage();
				tm.setLotteryId(Translator.lcPlayIdToZMLotteryId(playId));
				tm.setSchemes(schemeIds);
				log.debug("Sending WF BuyTicketMessage: {}", tm);
				messageSender.send(tm);
			}
		} catch (TranslateException te) {
			log.error(
					"can not send buy ticket message for playId: {}, schemes: {}",
					new Object[] { playId, schemePOs, te });
		}
	}

	/**
	 * 返回可以发送的福彩方案id列表
	 * @param lcLotteryId
	 * @param schemePOs
	 * @return
	 */
	private List<Long> getCanSendSchemeIds(String lcLotteryId,
			List<BetSchemePO> schemePOs) {
		List<Long> result = null;
		LotteryId lotteryId = LotteryId.valueOf(lcLotteryId);
		if (null != lotteryId && (lotteryId == LotteryId.SSQ || 
				lotteryId == LotteryId.FC3D)) {
			result = new ArrayList<Long>();
			for (BetSchemePO po : schemePOs) {
				result.add(po.getId());
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "BuyWFTicketTask";
	}
}