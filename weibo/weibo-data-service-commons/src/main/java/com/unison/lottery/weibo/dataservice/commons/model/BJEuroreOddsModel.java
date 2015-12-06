package com.unison.lottery.weibo.dataservice.commons.model;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name="c")
public class BJEuroreOddsModel {
	
	@XmlElement(name="h")
	public List<BJEuroreOddsContentModel> bjEuroreOddsContentModelList ;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
