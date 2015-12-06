package com.xhcms.lottery.commons.persist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xhcms.commons.lang.Paging;
import com.xhcms.lottery.commons.data.Win;
import com.xhcms.lottery.commons.persist.dao.WinDao;
import com.xhcms.lottery.commons.persist.dao.WinDaoWithBetScheme;
import com.xhcms.lottery.commons.persist.entity.WinPO;
import com.xhcms.lottery.commons.persist.entity.WinPOWithSchemePO;
import com.xhcms.lottery.commons.persist.service.WinService;

/**
 * <p>Title: 中奖记录 </p>
 * <p>Description: </p>
 * <p>Copyright：Copyright (c) 2011</p>
 * <p>Company：XingHui Spirit (Beijing) Technical Co.,Ltd.</p>
 * 
 * @author xulang
 * @version 1.0.0
 */
public class WinServiceImpl implements WinService {
    
	@Autowired
	private WinDao winDao;
	
	@Autowired
	private WinDaoWithBetScheme winDaoWithBetScheme;
	
	@Override
	@Transactional(readOnly = true)
	public void listWin(Paging paging, long userId, Date begin, Date end) {
		List<WinPO> list = winDao.find(paging, userId, begin, end);
		List<Win> rets = new ArrayList<Win>(list.size());
		Win win = null;
		for (WinPO po : list) {
			win = new Win();
			BeanUtils.copyProperties(po, win);
			rets.add(win);
		}
		paging.setResults(rets);
	}

	@Override
	@Transactional(readOnly = true)
	public void listWinByLotteryId(String lotteryId, Paging paging, long userId,
			Date begin, Date end) {
		List<WinPOWithSchemePO> list = winDaoWithBetScheme.findByLotteryId(lotteryId,paging, userId, begin, end);
		List<Win> rets = new ArrayList<Win>(list.size());
		Win win = null;
		for (WinPOWithSchemePO po : list) {
			win = new Win();
			BeanUtils.copyProperties(po, win);
			rets.add(win);
		}
		paging.setResults(rets);
	}

}
