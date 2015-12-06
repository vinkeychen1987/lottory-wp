package com.xhcms.lottery.admin.web.action.match;

import org.springframework.beans.factory.annotation.Autowired;
import com.xhcms.commons.lang.Data;
import com.xhcms.lottery.admin.web.action.BaseAction;
import com.xhcms.lottery.commons.persist.service.MatchColorService;

/**
 * 更新联赛背景色
 * @author Wang Lei
 *
 */
public class AjaxUpdateColorAction extends BaseAction {

    private static final long serialVersionUID = 6385058823032919922L;

    @Autowired
    private MatchColorService matchColorService;

    private Long id;
    
    private String color;

    private String shortLeagueName;
    
    private Data data;

    public String execute() {
    	int code = -3;
    	try {
    		code = matchColorService.updateColor(color, id, shortLeagueName);
		} catch (Exception e) {
			e.printStackTrace();
		}
        data = Data.success(code);
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShortLeagueName() {
		return shortLeagueName;
	}

	public void setShortLeagueName(String shortLeagueName) {
		this.shortLeagueName = shortLeagueName;
	}
}
