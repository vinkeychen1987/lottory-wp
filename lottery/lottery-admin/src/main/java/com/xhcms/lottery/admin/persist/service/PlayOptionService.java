package com.xhcms.lottery.admin.persist.service;

import java.util.List;

import com.xhcms.lottery.commons.data.PlayOption;

/**
 * <p>Title: 玩法选项管理</p>
 * <p>Description: </p>
 * <p>Copyright：Copyright (c) 2011</p>
 * <p>Company：XingHui Spirit (Beijing) Technical Co.,Ltd.</p>
 * 
 * @author jiajiancheng
 * @version 1.0.0
 */
public interface PlayOptionService {

    List<PlayOption> list(String playId);

    void add(PlayOption option);

    void remove(Long oid);

}
